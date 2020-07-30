//
//  HomeViewModel.swift
//  Hurb Alpha
//
//  Created by Thiago Augusto on 23/07/20.
//  Copyright © 2020 objectivesev. All rights reserved.
//

import Foundation

enum HomeViewModelAction {
    case reload, failure(error: Error, code: Int), empty
}

protocol HomeViewModelDelegate: class {
    func didSelectAction(_ action: HomeViewModelAction)
}

class HomeViewModel {
    weak var delegate: HomeViewModelDelegate?
    
    private let hotelsService: IHotelService
    private var endOfPage = false
    private var isLoading = false
    
    private var hotels = [(rating: Int?, results: [Result])]()
    private var searchQuery: String?
    private var page = 1
    
    init(hotelsService: IHotelService) {
        self.hotelsService = hotelsService
    }
    
    func getItems(reload: Bool) {
        loadItems(reload: reload)
    }
    
    func numberOfSections() -> Int {
        hotels.count
    }
    
    func numberOfRowsIn(_ section: Int) -> Int {
        hotels[section].results.count
    }
    
    func itemAt(_ indexPath: IndexPath) -> Result {
        hotels[indexPath.section].results[indexPath.row]
    }
    
    func ratingAt(_ section: Int) -> Int? {
        return hotels[section].rating
    }
    
    func handleDisplayItemAt(_ indexPath: IndexPath) {
        // O método de paginação não combina com a API fornecida e os requerimentos do desafio.
        // Existem maneiras melhores de se fazer isso como agrupar as celulas dentro de uma UICollectionView e fazer chamadas para a API filtrando por Rating por exemplo
        // Ou a API deveria retornar os dados já ordenados
        let item = hotels[indexPath.section]
        if indexPath.row >= item.results.count - 5 {
            getItems(reload: false)
        }
    }
    
    func updateQuery(text: String?) {
        searchQuery = text
    }
}

private extension HomeViewModel {
    func loadItems(reload: Bool) {
        if reload {
            endOfPage = false
            page = 1
            hotels = []
        } else {
            page = page + 1
        }
        guard let searchQuery = searchQuery, !endOfPage, !isLoading else {
            let error = HAError.invalidQuery
            delegate?.didSelectAction(.failure(error: error, code: error.code))
            return
        }
        
        isLoading = true
        hotelsService.getHotels(query: searchQuery, page: page) { result in
            self.isLoading = false
            switch result {
            case .failure(let error, let code):
                self.delegate?.didSelectAction(.failure(error: error, code: code))
            case .success(let result, _):
                if let hotels = result.results {
                    if hotels.isEmpty {
                        if reload {
                            self.delegate?.didSelectAction(.empty)
                        } else {
                            self.endOfPage = true
                        }
                    } else {
                        self.endOfPage = false
                        self.addHotels(hotels)
                        self.delegate?.didSelectAction(.reload)
                    }
                }
            }
        }
    }
    
    func addHotels(_ hotels: [Result]) {
        var packages = [Result]()
        var resultHotels = [Result]()
        
        hotels.forEach { result in
            if result.isHotel ?? false {
                resultHotels.append(result)
            } else if result.isPackage ?? false {
                packages.append(result)
            }
        }
        
        if !packages.isEmpty {
            appendToResults(rating: nil, results: packages)
        }
        
        let dictionary = Dictionary.init(grouping: resultHotels, by: { (element: Result) in
            return element.stars
        })
        
        dictionary.forEach { rating, results in
            if !results.isEmpty {
                appendToResults(rating: rating, results: results)
            }
        }
        
        self.hotels.sort { (first, second) -> Bool in
            let firstRating = first.rating ?? -1
            let secondRating = second.rating ?? -1
            return firstRating > secondRating
        }
    }
    
    func appendToResults(rating: Int?, results: [Result]) {
        if let index = hotels.firstIndex(where: { $0.rating == rating }) {
            hotels[index].results.append(contentsOf: results)
        } else {
            hotels.append((rating, results))
        }
    }
}

//
//  HomeViewModel.swift
//  Hurb Alpha
//
//  Created by Thiago Augusto on 23/07/20.
//  Copyright © 2020 objectivesev. All rights reserved.
//

import Foundation

enum HomeViewModelAction {
    case reload, failure(error: Error), empty
}

protocol HomeViewModelDelegate: class {
    func didSelectAction(_ action: HomeViewModelAction)
}

class HomeViewModel {
    weak var delegate: HomeViewModelDelegate?
    
    private var hotels = [Result]()
    private var searchQuery: String?
    private var page = 1
    
    func getItems(reload: Bool) {
        loadItems(reload: reload)
    }
    
    func numberOfRows() -> Int {
        hotels.count
    }
    
    func itemAt(_ indexPath: IndexPath) -> Result {
        hotels[indexPath.row]
    }
    
    func handleDisplayItemAt(_ indexPath: IndexPath) {
        
    }
    
    func updateQuery(text: String?) {
        searchQuery = text
    }
}

private extension HomeViewModel {
    func loadItems(reload: Bool) {
        if reload {
            page = 1
            hotels = []
        } else {
            page = page + 1
        }
        guard let searchQuery = searchQuery else { return }
        
        HotelsService.getHotels(query: searchQuery, page: page) { result in
            switch result {
            case .failure(let error, _):
                self.delegate?.didSelectAction(.failure(error: error))
            case .success(let result, _):
                if let hotels = result.results {
                    if reload, hotels.isEmpty {
                        self.delegate?.didSelectAction(.empty)
                    } else {
                        self.hotels += hotels
                        self.delegate?.didSelectAction(.reload)
                    }
                }
            }
        }
    }
}

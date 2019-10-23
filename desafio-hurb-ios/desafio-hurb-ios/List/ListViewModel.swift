//
//  ListViewModel.swift
//  desafio-hurb-ios
//
//  Created by Vinícius Barcelos on 21/10/19.
//  Copyright © 2019 Vinícius Barcelos. All rights reserved.
//

import Foundation

class ListViewModel {
    
    var hotels = [Hotel]()
    var sortedHotels: [[Hotel]]
    var sortedHotelsTitles: [String] = ["5 Estrelas", "4 Estrelas", "3 Estrelas", "2 Estrelas", "1 Estrela", "Pacotes"]
    let view: ListViewProtocol!
    var status: ViewStatus = .none {
        didSet {
            DispatchQueue.main.async {
                self.view.setupView()
            }
        }
    }
    
    init(view: ListViewProtocol) {
        self.view = view
        self.sortedHotels = HotelSorter.sortedHotels(from: hotels)
    }
    
}


extension ListViewModel {
    
    func getHotels(with query: String) {
        self.status = .loading
        NetworkingServices.getHotels(with: query) { (result) in
            switch result {
            case .success(let hotels):
                self.hotels = hotels
                self.sortedHotels = HotelSorter.sortedHotels(from: hotels)
                self.status = .loaded
            case .failure(let error):
                self.status = .error
                NSLog(error.localizedDescription)
            }
        }
    }
    
}

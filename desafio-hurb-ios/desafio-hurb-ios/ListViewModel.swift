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
    let view: ListViewProtocol!
    var status: ViewStatus = .none {
        didSet {
            self.view.setupView()
        }
    }
    
    init(view: ListViewProtocol) {
        self.view = view
        self.sortedHotels = HotelSorter.sortedHotels(from: hotels)
    }
    
}


extension ListViewModel {
    
    func getHotels(with query: String) {
        NetworkingServices.getHotels(with: query) { (result) in
            switch result {
            case .success(let hotels):
                self.hotels = hotels
                self.sortedHotels = HotelSorter.sortedHotels(from: hotels)
                self.status = .loaded
            case .failure(let error):
//                DispatchQueue.main.async {
//                    self.view.setupError()
//                }
                NSLog(error.localizedDescription)
            }
        }
    }
    
}

//
//  HotelListPresenter.swift
//  HotelUrbanoChallenge
//
//  Created by maciosdev on 26/07/2018.
//  Copyright © 2018 Ronilson. All rights reserved.
//

import Foundation

final class HotelListPresenter {
    
    fileprivate unowned let view: HotelListProtocol
    fileprivate let service: HotelUrbanoService
    fileprivate(set) var listParameters: HotelListSetup!
    fileprivate(set) var hotels: [Results] = []
    
    
    init(view: HotelListProtocol) {
        self.view = view
        self.service = HotelUrbanoService()
    }
}

// MARK: - Public methods

extension HotelListPresenter {
    
    func loadHotels() {
        self.view.startLoading()
        self.service.getHotels(success: { result  in
            
            self.hotels = result.results!
            self.handleDataHotels()
            self.view.reloadTableView()
            self.view.stopLoading()
        }) { error in
            self.requestError(errorDescription: error)
        }
    }
}

// MARK: - Private methods

extension HotelListPresenter {
    
    fileprivate func handleDataHotels() {
        
//        let filteredApplications = self.hotels.filter { $0.address?.state == "Rio de Janeiro" }
        var hotels: [Int: [Results]] = [:]
        hotels =  self.hotels.groupBy { ($0.stars)! }
    }
    
    fileprivate func requestError(errorDescription: String) {
        self.view.stopLoading()
        self.view.showAlertError(with: "Erro", message: errorDescription, buttonTitle: "OK")
    }
}

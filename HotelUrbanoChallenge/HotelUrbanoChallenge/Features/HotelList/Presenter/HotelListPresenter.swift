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
    fileprivate(set) var hotels: [Results] = []
    fileprivate(set) var hotelListViewModel: [HotelListViewModel] = []
    
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
        
        // checking which are hotels
        let isHotel = self.hotels.filter {
            $0.isHotel == true
        }
        
        // Empty dictionary
        var hotels: [Int: [Results]] = [:]
        
        //Grouping by stars
        hotels =  isHotel.groupBy { ($0.stars)! }
        
        for hotel in hotels {
            let hotelList = HotelListViewModel(groupDescription: hotel.key, hotelList: hotel.value)
            self.hotelListViewModel.append(hotelList)
        }
    
        self.sortedByOrder()
    }
    
    fileprivate func sortedByOrder() {
        
        //Sorts the grouped data based on number of stars
        let order =  self.hotelListViewModel.sorted(by: { $0.groupDescription > $1.groupDescription })
        self.hotelListViewModel = order
    }
    
    fileprivate func requestError(errorDescription: String) {
        self.view.stopLoading()
        self.view.showAlertError(with: "Erro", message: errorDescription, buttonTitle: "OK")
    }
}

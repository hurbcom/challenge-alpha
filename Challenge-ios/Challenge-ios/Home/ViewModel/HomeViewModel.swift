//
//  HomeViewModel.swift
//  Challenge-ios
//
//  Created by Andre Dias on 28/09/20.
//  Copyright © 2020 Andre Dias. All rights reserved.
//

import Foundation

protocol HotelsViewModelDelegate {
    func getCompletionHotels(_ hotels: [HotelsResults])
    func didFinishFetchingHotels()
}


class HomeViewModel {
    
    var hotelsArray = [HotelsResults]()
    var homeViewModelDelegate: HotelsViewModelDelegate?
    
    var numberOfCells: Int {
       return self.hotelsArray.count
    }
    
    func getBaseHotels(page: Int) {
        DispatchQueue.main.async {
            APIRequest.shared.getBaseHotels(atPage: page) { (hotelsResult) in
                
                if hotelsResult.count > 0 {
                    for hotel in hotelsResult {
                        self.hotelsArray.append(hotel)
                    }
                    
                    self.homeViewModelDelegate?.getCompletionHotels(self.hotelsArray)
                }
                self.homeViewModelDelegate?.didFinishFetchingHotels()

            }
        }

    }
    
    func returnHotels() -> [HotelsResults] {
        return self.hotelsArray
    }
    
    
}

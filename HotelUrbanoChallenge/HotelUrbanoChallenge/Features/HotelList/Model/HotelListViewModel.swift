//
//  HotelListViewModel.swift
//  HotelUrbanoChallenge
//
//  Created by maciosdev on 26/07/2018.
//  Copyright © 2018 Ronilson. All rights reserved.
//

import Foundation

final class HotelListViewModel: NSObject {
    
    let groupDescription: Int
    var hotelItems: [HotelItemViewModel] = []
    
    init(groupDescription: Int, hotelList: [Results]) {
        self.groupDescription = groupDescription
        
        
        // Setup hotel to view cell
        let hotels = hotelList.groupBy { $0.name ?? "" }
        
        for item in hotels {
            let hotel = item.value.first!
            let itemViewModel = HotelItemViewModel(hotelItem: hotel)
            self.hotelItems.append(itemViewModel)
        }
    }
    
    // MARK: - HotelItemViewModel
    
    internal struct HotelItemViewModel {

        let name: String?
        let price: Double?
        let state: String?
        let city: String?
        let image: String?

        init(hotelItem: Results) {
            
            self.name = hotelItem.name
            self.price = hotelItem.price?.amount
            self.state = hotelItem.address?.state
            self.city = hotelItem.address?.city
            self.image = hotelItem.image
        }
    }
    
}

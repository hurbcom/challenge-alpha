//
//  HotelViewModel.swift
//  HotelSearch
//
//  Created by Tulio Parreiras on 15/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import Foundation

final public class HotelViewModel {
    
    let hotel: Hotel
    
    public var name: String? {
        return self.hotel.name
    }
    public var location: String? {
        let address = self.hotel.address
        let addressFields = [address?.city, address?.state]
        return addressFields.compactMap({ $0 }).reduce(into: "", ({
            result, text in
            result += text == addressFields.first ? text : " - \(text)"
        }))
    }
    public var amenities: String? {
        let amenitiesArr = self.hotel.amenities
        return amenitiesArr?.compactMap({ $0.name }).prefix(upTo: 3).reduce(into: "", { (result, text) in
            result += text == amenitiesArr?.first?.name ? text : " | \(text)"
        })
    }
    public var price: String? {
        let formatter = NumberFormatter()
        formatter.numberStyle = .currency
        formatter.locale = Locale(identifier: "pt-BR")
        return formatter.string(from: NSNumber(value: self.hotel.price?.amountPerDay ?? 0))
    }
    
    public init(hotel: Hotel) {
        self.hotel = hotel
    }
    
}

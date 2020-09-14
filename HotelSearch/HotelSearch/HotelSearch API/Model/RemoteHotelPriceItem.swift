//
//  RemoteHotelPriceItem.swift
//  HotelSearch
//
//  Created by Tulio Parreiras on 14/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import Foundation

struct RemoteHotelPriceItem: Decodable {
    let amount: Double
    let amountPerDay: Double
    let currency: String
    
    var item: HotelPrice {
        return HotelPrice(amount: amount, amountPerDay: amountPerDay, currency: currency)
    }
}


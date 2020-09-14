//
//  HotelPrice.swift
//  HotelSearch
//
//  Created by Tulio Parreiras on 14/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import Foundation

public struct HotelPrice: Hashable {
    public let amount: Double
    public let amountPerDay: Double
    public let currency: String
    
    public init(amount: Double, amountPerDay: Double, currency: String) {
        self.amount = amount
        self.amountPerDay = amountPerDay
        self.currency = currency
    }
}


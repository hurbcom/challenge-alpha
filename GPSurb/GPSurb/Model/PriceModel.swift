//
//  PriceModel.swift
//  GPSurb
//
//  Created by Gilson Santos on 16/10/19.
//  Copyright © 2019 Gilson Santos. All rights reserved.
//

import Foundation

struct PriceModel: Codable {
    let currency: String?
    let currencyOriginal: String?
    let currentPrice: Double?
    let oldPrice: Double?
    let sku: String?
    let originalAmountPerDay: Double?
    let amountPerDay: Double?
    let amount: Double?
    
    enum CodingKeys: String, CodingKey {
        case currency = "currency"
        case currencyOriginal = "currency_original"
        case currentPrice = "current_price"
        case oldPrice = "old_price"
        case sku = "sku"
        case originalAmountPerDay = "originalAmountPerDay"
        case amountPerDay = "amountPerDay"
        case amount = "amount"
    }
}

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
    let currency_original: String?
    let current_price: Double?
    let old_price: Double?
    let sku: String?
    let originalAmountPerDay: Double?
    let amountPerDay: Double?
    let amount: Double?
}

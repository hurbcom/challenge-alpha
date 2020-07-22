//
//  Price.swift
//  HurbHotel_iOS
//
//  Created by Helio Junior on 22/07/20.
//  Copyright © 2020 HelioTecnologia. All rights reserved.
//

import Foundation

struct Price: Decodable {
    let amount: Double?
    let amountPerDay: Double?
    let currency: String?
    let currency_original: String?
    let current_price: Double?
    let fee_extra: Double?
    let fee_extra_original: Double?
    let gain: Double?
    let gain_original: Double?
    let old_price: Double?
    let originalAmountPerDay: Double?
    let sku: String?
    let total_price: Double?
}

//
//  Price.swift
//  DesafioHurb
//
//  Created by Edson Aparecido Guido on 13/09/20.
//  Copyright © 2020 Edson Aparecido Guido. All rights reserved.
//

import Foundation

struct Price: Codable, Equatable {
    let amount: Double
    let priceOldPrice: Double?
    let currency: String?
    let currencyOriginal: String?
    let gain: Int?
    let feeExtraOriginal: Int?
    let gainOriginal: Int?
    let tariffPolicies: [String]?
    let priceCurrentPrice: Double?
    let totalPrice: Double?
    let feeExtra: Int?
    let sku: String
    let taxes: [Tax]?
    let originalAmountPerDay: Double?
    let amountPerDay: Double
    let oldPrice: Int?
    let currentPrice: Int?
    let originalAmount: Int?

    enum CodingKeys: String, CodingKey {
        case amount
        case priceOldPrice = "old_price"
        case currency
        case currencyOriginal = "currency_original"
        case gain
        case feeExtraOriginal = "fee_extra_original"
        case gainOriginal = "gain_original"
        case tariffPolicies = "tariff_policies"
        case priceCurrentPrice = "current_price"
        case totalPrice = "total_price"
        case feeExtra = "fee_extra"
        case sku, taxes, originalAmountPerDay, amountPerDay, oldPrice, currentPrice, originalAmount
    }
}

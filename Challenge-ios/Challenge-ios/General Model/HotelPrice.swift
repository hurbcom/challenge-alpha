//
//  HotelPrice.swift
//  Challenge-ios
//
//  Created by Andre Dias on 28/09/20.
//  Copyright © 2020 Andre Dias. All rights reserved.
//

import Foundation

// MARK: - ResultPrice
struct ResultPrice: Codable {
    let amount: Double
    let priceOldPrice: Double?
    let gain, feeExtraOriginal, gainOriginal: Int?
    let tariffPolicies: [JSONAny]?
    let priceCurrentPrice, totalPrice: Double?
    let feeExtra: Int?
    let sku: String
    let taxes: [Tax]?
    let originalAmountPerDay: Double?
    let amountPerDay: Double
    let oldPrice, currentPrice, originalAmount: Int?

    enum CodingKeys: String, CodingKey {
        case amount
        case priceOldPrice
        case gain
        case feeExtraOriginal
        case gainOriginal
        case tariffPolicies
        case priceCurrentPrice
        case totalPrice
        case feeExtra
        case sku, taxes, originalAmountPerDay, amountPerDay, oldPrice, currentPrice, originalAmount
    }
}

// MARK: - Tax
struct Tax: Codable {
    let name: Name
    let amount, amountOriginal: Double
    
    enum CodingKeys: String, CodingKey {
        case name, amount
        case amountOriginal
    }
}

enum Name: String, Codable {
    case taxaDeReserva = "Taxa de Reserva"
}

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
    let totalPrice: Double?
    let feeExtra: Double?
    let sku: String
    let taxes: [Tax]?
    let originalAmountPerDay: Double?
    let amountPerDay: Double
    let oldPrice, currentPrice: Double?

    enum CodingKeys: String, CodingKey {
        case totalPrice = "total_price"
        case feeExtra = "fee_extra"
        case oldPrice = "old_price"
        case currentPrice = "current_price"
        case amount, sku, taxes, originalAmountPerDay, amountPerDay
    }
    
    init(from decoder: Decoder) throws {
        let codingValue = try decoder.container(keyedBy: CodingKeys.self)
        amount = try codingValue.decode(Double.self, forKey: .amount)
        totalPrice = try codingValue.decode(Double.self, forKey: .totalPrice)
        feeExtra = try codingValue.decode(Double.self, forKey: .feeExtra)
        sku = try codingValue.decode(String.self, forKey: .sku)
        taxes = try codingValue.decode([Tax].self, forKey: .taxes)
        originalAmountPerDay = try codingValue.decode(Double.self, forKey: .originalAmountPerDay)
        amountPerDay = try codingValue.decode(Double.self, forKey: .amountPerDay)
        oldPrice = try codingValue.decode(Double.self, forKey: .oldPrice)
        currentPrice = try codingValue.decode(Double.self, forKey: .currentPrice)
    }
}

// MARK: - Tax
struct Tax: Codable {
    let name: Name
    let amount, amountOriginal: Double
    
    enum CodingKeys: String, CodingKey {
        case name, amount
        case amountOriginal = "amount_original"
    }
    
    init(from decoder: Decoder) throws {
        let codingValue = try decoder.container(keyedBy: CodingKeys.self)
        name = try codingValue.decode(Name.self, forKey: .name)
        amount = try codingValue.decode(Double.self, forKey: .amount)
        amountOriginal = try codingValue.decode(Double.self, forKey: .amountOriginal)
    }
}

enum Name: String, Codable {
    case taxaDeReserva = "Taxa de Reserva"
}

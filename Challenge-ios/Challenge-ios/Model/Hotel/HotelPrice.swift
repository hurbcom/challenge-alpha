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
    var amount: Double = 0.0
    var totalPrice: Double = 0.0
    var feeExtra: Double = 0.0
    var sku: String = ""
    var taxes: [Tax]?
    var originalAmountPerDay: Double = 0.0
    var amountPerDay: Double = 0.0
    var oldPrice: Double = 0.0
    var currentPrice: Double = 0.0

    enum CodingKeys: String, CodingKey {
        case totalPrice = "total_price"
        case feeExtra = "fee_extra"
        case oldPrice = "old_price"
        case currentPrice = "current_price"
        case amount, sku, taxes, originalAmountPerDay, amountPerDay
    }
    
    init(from decoder: Decoder) throws {
        if let codingValue = try? decoder.container(keyedBy: CodingKeys.self) {
            if let amountDecoded = try? codingValue.decode(Double.self, forKey: .amount) {
                self.amount = amountDecoded
            }
            if let totalPriceDecoded = try? codingValue.decode(Double.self, forKey: .totalPrice) {
                self.totalPrice = totalPriceDecoded
            }
            if let feeExtraDecoded = try? codingValue.decode(Double.self, forKey: .feeExtra) {
                self.feeExtra = feeExtraDecoded
            }
            if let skuDecoded = try? codingValue.decode(String.self, forKey: .sku) {
                self.sku = skuDecoded
            }
            if let taxesDecoded = try? codingValue.decode([Tax].self, forKey: .taxes) {
                self.taxes = taxesDecoded
            }
            if let originalAmountPerDayDecoded = try? codingValue.decode(Double.self, forKey: .originalAmountPerDay) {
                self.originalAmountPerDay = originalAmountPerDayDecoded
            }
            if let amountPerDayDecoded = try? codingValue.decode(Double.self, forKey: .amountPerDay) {
                self.amountPerDay = amountPerDayDecoded
            }
            if let oldPriceDecoded = try? codingValue.decode(Double.self, forKey: .oldPrice) {
                self.oldPrice = oldPriceDecoded
            }
            if let currentPriceDecoded = try? codingValue.decode(Double.self, forKey: .currentPrice) {
                self.currentPrice = currentPriceDecoded
            }
        }
    }
}

// MARK: - Tax
struct Tax: Codable {
    var name: Name?
    var amount: Double = 0.0
    var amountOriginal: Double = 0.0
    
    enum CodingKeys: String, CodingKey {
        case name, amount
        case amountOriginal = "amount_original"
    }
    
    init(from decoder: Decoder) throws {
        if let codingValue = try? decoder.container(keyedBy: CodingKeys.self) {
            if let nameDecoded = try? codingValue.decode(Name.self, forKey: .name) {
                self.name = nameDecoded
            }
            if let amountDecoded = try? codingValue.decode(Double.self, forKey: .amount) {
                self.amount = amountDecoded
            }
            if let amountOriginalDecoded = try? codingValue.decode(Double.self, forKey: .amountOriginal) {
                self.amountOriginal = amountOriginalDecoded
            }
        }
    }
}

enum Name: String, Codable {
    case taxaDeReserva = "Taxa de Reserva"
}

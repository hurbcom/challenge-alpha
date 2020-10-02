//
//  PackagePrice.swift
//  Challenge-ios
//
//  Created by Andre Dias on 02/10/20.
//  Copyright © 2020 Andre Dias. All rights reserved.
//

import Foundation


// MARK: - Price
struct PackagePrice: Codable {
    var oldPrice: Int = 0
    var currentPrice: Int = 0
    var sku: String = ""
    var originalAmount: Int = 0
    var amountPerDay: Int = 0
    var amount: Int = 0
    
    enum CodingKeys: String, CodingKey {
        case oldPrice, currentPrice
        case sku
        case originalAmount, amountPerDay, amount
    }
    
    init(from decoder: Decoder) throws {
        if let codingValue = try? decoder.container(keyedBy: CodingKeys.self) {
            if let skuDecoded = try? codingValue.decode(String.self, forKey: .sku) {
                self.sku = skuDecoded
            }
            if let oldPriceDecoded = try? codingValue.decode(Int.self, forKey: .oldPrice) {
                self.oldPrice = oldPriceDecoded
            }
            if let currentPriceDecoded = try? codingValue.decode(Int.self, forKey: .currentPrice) {
                self.currentPrice = currentPriceDecoded
            }
            if let originalAmountDecoded = try? codingValue.decode(Int.self, forKey: .originalAmount) {
                self.originalAmount = originalAmountDecoded
            }
            if let amountPerDayDecoded = try? codingValue.decode(Int.self, forKey: .amountPerDay) {
                self.amountPerDay = amountPerDayDecoded
            }
            if let amountDecoded = try? codingValue.decode(Int.self, forKey: .amount) {
                self.amount = amountDecoded
            }
        }
    }
}

// MARK: - QuantityDescriptors
struct PackageQuantityDescriptors: Codable {
    var nights: Int = 0
    var maxPeople: Int = 0
    
    enum CodingKeys: String, CodingKey {
        case nights, maxPeople
    }
    
    init(from decoder: Decoder) throws {
        if let codingValue = try? decoder.container(keyedBy: CodingKeys.self) {
            if let nightsDecoded = try? codingValue.decode(Int.self, forKey: .nights) {
                self.nights = nightsDecoded
            }
            if let maxPeopleDecoded = try? codingValue.decode(Int.self, forKey: .maxPeople) {
                self.maxPeople = maxPeopleDecoded
            }
        }
    }
}


//
//  Price.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 15/01/23.
//

import Foundation

// MARK: - Price
struct Price: Codable {
    var amount: Double?
    var originalAmount: Double?
    var currency: String?
    var taxes: [Tax]?
}

// MARK: - Tax
struct Tax: Codable {
    var originalAmount: Double?
    var originalCurrency: String?
}

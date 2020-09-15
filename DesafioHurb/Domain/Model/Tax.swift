//
//  Tax.swift
//  DesafioHurb
//
//  Created by Edson Aparecido Guido on 13/09/20.
//  Copyright © 2020 Edson Aparecido Guido. All rights reserved.
//

import Foundation

struct Tax: Codable {
    let type: String
    let name: String
    let amount: Double
    let amountOriginal: Double
    let currency: String
    let currencyOriginal: String

    enum CodingKeys: String, CodingKey {
        case type, name, amount
        case amountOriginal = "amount_original"
        case currency
        case currencyOriginal = "currency_original"
    }
}

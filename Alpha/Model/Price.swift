//
//  Price.swift
//  Alpha
//
//  Created by Theo Mendes on 15/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

import Foundation

struct Price: Codable {
    var currency: String?
    var amount: Float
    var symbol: String {
        switch self.currency {
        case "BRL": return "R$"
        case "USD": return "$"
        default: return "R$"
        }
    }

    enum CodingKeys: String, CodingKey {
        case currency
        case amount
    }
}

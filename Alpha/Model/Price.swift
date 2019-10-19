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
    var currentPricePkg: Float?
    var oldPricePkg: Float?
    var currentPrice: Float?
    var oldPrice: Float?
    var amount: Float
    var symbol: String {
        switch self.currency {
        case "BRL": return "R$"
        case "USD": return "$"
        default: return "R$"
        }
    }
    var discount: Int {
        if let currentPrice = self.currentPrice,
            let oldPrice = self.oldPrice {
            return (oldPrice != 0) ? Int(((currentPrice - oldPrice) / currentPrice) * 100) : 0

        } else if let currentPrice = self.currentPricePkg,
            let oldPrice = self.oldPricePkg {

            return (oldPrice != 0) ? Int(((currentPrice - oldPrice) / currentPrice) * 100) : 0
        }
        return 0
    }

    enum CodingKeys: String, CodingKey {
        case currentPricePkg = "currentPrice"
        case oldPricePkg = "oldPrice"
        case currentPrice = "current_price"
        case oldPrice = "old_price"
        case currency
        case amount
    }
}

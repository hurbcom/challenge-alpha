//
//  Price.swift
//  Hurb-iOS-Challenge
//
//  Created by Rômulo Monteiro on 08/01/23.
//

import UIKit

struct Price: Codable {
    
    private enum CodingKeys: String, CodingKey {
        
        case amount
        case currency
    }
    
    let amount: Double
    let currency: String
}

// Decode methods
extension Price {
    
    init(from decoder: Decoder) throws {
        
        let container = try decoder.container(keyedBy: CodingKeys.self)

        self.amount = try container.decode(Double.self, forKey: .amount)
        self.currency = try container.decode(String.self, forKey: .currency)
    }
}

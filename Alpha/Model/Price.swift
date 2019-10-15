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

    enum CodingKeys: String, CodingKey {
        case currency
        case amount
    }
}

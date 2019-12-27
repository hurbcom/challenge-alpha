//
//  Price.swift
//  hurb-challenge-alpha
//
//  Created by Hannah  on 26/12/2019.
//  Copyright © 2019 Hannah . All rights reserved.
//

import Foundation

struct Price: Decodable {
    let amount: Double?
    let oldPrice: Double?
    let currency: String?
    
    //protocol rename property keys
    private enum CodingKeys: String, CodingKey {
        case amount
        case oldPrice = "old_price"
        case currency
     }
}

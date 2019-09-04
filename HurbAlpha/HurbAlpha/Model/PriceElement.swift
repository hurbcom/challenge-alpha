//
//  PriceElement.swift
//  HurbAlpha
//
//  Created by Julia Rocha on 04/09/19.
//  Copyright © 2019 Julia Rocha. All rights reserved.
//

import Foundation

// MARK: - PriceElement
class PriceElement: Codable {
    let min, maxExclusive: Int
    let filter: String
    let count: Int
    
    init(min: Int, maxExclusive: Int, filter: String, count: Int) {
        self.min = min
        self.maxExclusive = maxExclusive
        self.filter = filter
        self.count = count
    }
}

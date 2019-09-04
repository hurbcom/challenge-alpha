//
//  PriceInterval.swift
//  HurbAlpha
//
//  Created by Julia Rocha on 04/09/19.
//  Copyright © 2019 Julia Rocha. All rights reserved.
//

import Foundation

// MARK: - PriceInterval
class PriceInterval: Codable {
    let min, max: Int
    let filterPattern: String
    
    init(min: Int, max: Int, filterPattern: String) {
        self.min = min
        self.max = max
        self.filterPattern = filterPattern
    }
}

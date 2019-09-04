//
//  PurpleAmenity.swift
//  HurbAlpha
//
//  Created by Julia Rocha on 04/09/19.
//  Copyright © 2019 Julia Rocha. All rights reserved.
//

import Foundation

// MARK: - PurpleAmenity
class PurpleAmenity: Codable {
    let term, filter: String
    let count: Int
    
    init(term: String, filter: String, count: Int) {
        self.term = term
        self.filter = filter
        self.count = count
    }
}

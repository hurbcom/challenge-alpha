//
//  Filters.swift
//  HurbHotel_iOS
//
//  Created by Helio Junior on 22/07/20.
//  Copyright © 2020 HelioTecnologia. All rights reserved.
//

import Foundation

class Filters: Decodable {
    let amenities: [Filter]?
    let prices: [Price]?
    
    struct Filter: Decodable {
        let count: Int
        let filter: String
        let term: String
    }
    
    struct Price: Decodable {
        let min: Int?
        let maxExclusive: Int?
        let filter: String?
        let count: Int?
    }
}

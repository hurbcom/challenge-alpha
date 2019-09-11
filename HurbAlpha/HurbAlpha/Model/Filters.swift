//
//  Filters.swift
//  HurbAlpha
//
//  Created by Julia Rocha on 04/09/19.
//  Copyright © 2019 Julia Rocha. All rights reserved.
//

import Foundation

// MARK: - Declaration

struct Filters: Codable {
    let amenities, attributes, countries, cities: [PurpleAmenity]
    let departureCities, duration, food, people: [PurpleAmenity]
    let prices: [PriceElement]
    let priceInterval: PriceInterval
    let productType, regulation, rooms, stars: [PurpleAmenity]
    let states: [PurpleAmenity]
}

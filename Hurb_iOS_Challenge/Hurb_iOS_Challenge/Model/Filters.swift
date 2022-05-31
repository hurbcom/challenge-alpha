//
//  Filters.swift
//  Hurb_iOS_Challenge
//
//  Created by Gáudio Ney on 22/05/22.
//

import Foundation

// MARK: - Filters
struct Filters: Codable {
    let amenities, countries, cities: [CityElement]?
    let prices: [PriceElement]?
    let priceInterval: PriceInterval?
    let productType, rooms, stars, states: [CityElement]?
}

//
//  Filters.swift
//  HurbChallenge
//
//  Created by Felipe Alves on 05/07/19.
//  Copyright © 2019 Bolzaniapps. All rights reserved.
//

import Foundation

struct Filters: Codable {
    let amenities: [FilterElement]
    let attributes: [FilterElement]
    let countries: [FilterElement]
    let cities: [FilterElement]
    let departureCities: [FilterElement]
    let district: [FilterElement]
    let neighborhood: [FilterElement]
    let duration: [FilterElement]
    let food: [FilterElement]
    let people: [FilterElement]
    let prices: [PriceElement]
    let priceInterval: PriceInterval
    let productType: [FilterElement]
    let regulation: [FilterElement]
    let rooms: [FilterElement]
    let stars: [FilterElement]
    let states: [FilterElement]
}

// MARK: -
struct FilterElement: Codable {
    let term: String
    let filter: String
    let count: Int
}

// MARK: -
struct PriceInterval: Codable {
    let min: Int
    let max: Int
    let filterPattern: String
}

// MARK: -
struct PriceElement: Codable {
    let min: Int
    let maxExclusive: Int
    let filter: String
    let count: Int
}

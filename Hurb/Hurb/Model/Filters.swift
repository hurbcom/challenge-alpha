//
//  Filters.swift
//  Hurb
//
//  Created by Alexandre Papanis on 08/08/19.
//  Copyright © 2019 Papanis. All rights reserved.
//

import Foundation

struct Filters: Codable {
    let amenities: [FilterItem]?
    let attributes: [FilterItem]?
    let countries: [FilterItem]?
    let cities: [FilterItem]?
    let departureCities: [FilterItem]?
    let district: [FilterItem]?
    let neighborhood: [FilterItem]?
    let duration: [FilterItem]?
    let food: [FilterItem]?
    let people: [FilterItem]?
    let prices: [PriceItem]?
    let priceInterval: PriceInterval?
    let productType: [FilterItem]?
    let regulation: [FilterItem]?
    let rooms: [FilterItem]?
    let stars: [FilterItem]?
    let states: [FilterItem]?
}

struct FilterItem: Codable {
    let term: String?
    let filter: String?
    let count: Int?
}

struct PriceInterval: Codable {
    let min: Int?
    let max: Int?
    let filterPattern: String?
}

struct PriceItem: Codable {
    let min: Int?
    let maxExclusive: Int?
    let filter: String?
    let count: Int?
}

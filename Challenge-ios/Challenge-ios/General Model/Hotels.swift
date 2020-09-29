//
//  Hotels.swift
//  Challenge-ios
//
//  Created by Andre Dias on 28/09/20.
//  Copyright © 2020 Andre Dias. All rights reserved.
//

import Foundation

// MARK: - Hotels
struct Hotels: Codable {
    let meta: Meta
    let filters: Filters
    let results: [Result]
    let pagination: Pagination
}

// MARK: - Filters
struct Filters: Codable {
    let amenities, attributes, countries, cities: [AttributeElement]
    let departureCities, duration, food, people: [AttributeElement]
    let prices: [PriceElement]
    let priceInterval: PriceInterval
    let productType, regulation, rooms, stars: [AttributeElement]
    let states: [AttributeElement]
}

// MARK: - AttributeElement
struct AttributeElement: Codable {
    let term, filter: String
    let count: Int
}

// MARK: - PriceInterval
struct PriceInterval: Codable {
    let min, max: Int
    let filterPattern: String
}

// MARK: - PriceElement
struct PriceElement: Codable {
    let min, maxExclusive: Int
    let filter: String
    let count: Int
}

// MARK: - Meta
struct Meta: Codable {
    let count, offset: Int
    let query, warning: String
    let countWithAvailability, countWithAvailabilityInPage, countHotel, countPackage: Int
    let countTicket, countBustrip, countDisney: Int
}

// MARK: - Pagination
struct Pagination: Codable {
    let count: Int
    let firstPage, nextPage: String
    let lastPage: String
}

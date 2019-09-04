//
//  Filters.swift
//  HurbAlpha
//
//  Created by Julia Rocha on 04/09/19.
//  Copyright © 2019 Julia Rocha. All rights reserved.
//

import Foundation

// MARK: - Filters
class Filters: Codable {
    let amenities, attributes, countries, cities: [PurpleAmenity]
    let departureCities, duration, food, people: [PurpleAmenity]
    let prices: [PriceElement]
    let priceInterval: PriceInterval
    let productType, regulation, rooms, stars: [PurpleAmenity]
    let states: [PurpleAmenity]
    
    init(amenities: [PurpleAmenity], attributes: [PurpleAmenity], countries: [PurpleAmenity], cities: [PurpleAmenity], departureCities: [PurpleAmenity], duration: [PurpleAmenity], food: [PurpleAmenity], people: [PurpleAmenity], prices: [PriceElement], priceInterval: PriceInterval, productType: [PurpleAmenity], regulation: [PurpleAmenity], rooms: [PurpleAmenity], stars: [PurpleAmenity], states: [PurpleAmenity]) {
        self.amenities = amenities
        self.attributes = attributes
        self.countries = countries
        self.cities = cities
        self.departureCities = departureCities
        self.duration = duration
        self.food = food
        self.people = people
        self.prices = prices
        self.priceInterval = priceInterval
        self.productType = productType
        self.regulation = regulation
        self.rooms = rooms
        self.stars = stars
        self.states = states
    }
}

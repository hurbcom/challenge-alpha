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
    let attributes: [Filter]?
    let cities: [Filter]?
    let countries: [Filter]?
    let departureCities: [Filter]?
    let duration: [Filter]?
    let food: [Filter]?
    let people: [Filter]?
    let priceInterval: [Filter]?
    let prices: [Filter]?
    let productType: [Filter]?
    let regulation: [Filter]?
    let rooms: [Filter]?
    let stars: [Filter]?
    let states: [Filter]?
}

//
//  Filters.swift
//  DesafioHurb
//
//  Created by Edson Aparecido Guido on 13/09/20.
//  Copyright © 2020 Edson Aparecido Guido. All rights reserved.
//

import Foundation

struct Filters: Codable, Equatable {
    let amenities: [Filter]
    let attributes: [Filter]
    let countries: [Filter]
    let cities: [Filter]
    let departureCities: [Filter]
    let duration: [Filter]
    let food: [Filter]
    let people: [Filter]
    let prices: [PriceElement]
    let priceInterval: PriceInterval
    let productType: [Filter]
    let regulation: [Filter]
    let rooms: [Filter]
    let stars: [Filter]
    let states: [Filter]
}

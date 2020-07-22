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
    
    //Poderia fazer os sub-objetos de Filters, para o teste não há necessidade.
//    let attributes: [Filter]?
//    let cities: [Filter]?
//    let countries: [Filter]?
//    let departureCities: [Filter]?
//    let duration: [Filter]?
//    let food: [Filter]?
//    let people: [Filter]?
//    let priceInterval: [Filter]?
//    let productType: [Filter]?
//    let regulation: [Filter]?
//    let rooms: [Filter]?
//    let stars: [Filter]?
//    let states: [Filter]?
    
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

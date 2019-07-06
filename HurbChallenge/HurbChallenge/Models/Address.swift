//
//  Address.swift
//  HurbChallenge
//
//  Created by Felipe Alves on 05/07/19.
//  Copyright © 2019 Bolzaniapps. All rights reserved.
//

import Foundation

struct Address: Codable {
    let city: String
    let country: String
    let idAtlasCity: Int?
    let idAtlasCountry: Int?
    let idAtlasNeighborhood: Int?
    let idAtlasState: Int?
    let idCity: Int
    let idCountry: Int
    let idState: Int
    let state: String
    let street: String
    let zipcode: String
    let geoLocation: GeoLocation
    
    enum CodingKeys: String, CodingKey {
        case city
        case country
        case idAtlasCity = "id_atlas_city"
        case idAtlasCountry = "id_atlas_country"
        case idAtlasNeighborhood = "id_atlas_neighborhood"
        case idAtlasState = "id_atlas_state"
        case idCity = "id_city"
        case idCountry = "id_country"
        case idState = "id_state"
        case state
        case street
        case zipcode
        case geoLocation
    }
}

// MARK: -
struct GeoLocation: Codable {
    let lat: Double
    let lon: Double
}

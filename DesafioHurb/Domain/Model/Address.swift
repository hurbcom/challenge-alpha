//
//  Address.swift
//  DesafioHurb
//
//  Created by Edson Aparecido Guido on 13/09/20.
//  Copyright © 2020 Edson Aparecido Guido. All rights reserved.
//

import Foundation

struct Address: Codable {
    let zipcode: String?
    let addressFullAddress: String?
    let street: String?
    let addressStreetName: String?
    let streetName: String?
    let address: String?
    let fullAddress: String?
    let neighborhood: String?
    let idAtlasNeighborhood: String?
    let idNeighborhood: Int?
    let city: String
    let idAtlasCity: Int?
    let idCity: Int?
    let state: String
    let idAtlasState: Int?
    let idState: Int?
    let country: String
    let idAtlasCountry: Int?
    let idCountry: Int?
    let geoLocation: GeoLocation

    enum CodingKeys: String, CodingKey {
        case zipcode
        case addressFullAddress = "full_address"
        case street
        case addressStreetName = "street_name"
        case streetName, address, fullAddress, neighborhood
        case idAtlasNeighborhood = "id_atlas_neighborhood"
        case idNeighborhood = "id_neighborhood"
        case city
        case idAtlasCity = "id_atlas_city"
        case idCity = "id_city"
        case state
        case idAtlasState = "id_atlas_state"
        case idState = "id_state"
        case country
        case idAtlasCountry = "id_atlas_country"
        case idCountry = "id_country"
        case geoLocation
    }
}

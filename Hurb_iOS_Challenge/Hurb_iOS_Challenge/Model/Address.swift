//
//  Address.swift
//  Hurb_iOS_Challenge
//
//  Created by Gáudio Ney on 22/05/22.
//

import Foundation

// MARK: - Address
struct Address: Codable {
    let city: City?
    let country: Country?
    let idAtlasCity, idAtlasCountry, idAtlasNeighborhood, idAtlasState: Int?
    let idCity, idCountry, idState: Int?
    let state: State?
    let street, zipcode: String?
    let geoLocation: GeoLocation?

    enum CodingKeys: String, CodingKey {
        case city, country
        case idAtlasCity = "id_atlas_city"
        case idAtlasCountry = "id_atlas_country"
        case idAtlasNeighborhood = "id_atlas_neighborhood"
        case idAtlasState = "id_atlas_state"
        case idCity = "id_city"
        case idCountry = "id_country"
        case idState = "id_state"
        case state, street, zipcode, geoLocation
    }
}

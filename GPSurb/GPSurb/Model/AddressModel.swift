//
//  AddressModel.swift
//  GPSurb
//
//  Created by Gilson Santos on 16/10/19.
//  Copyright © 2019 Gilson Santos. All rights reserved.
//

import Foundation

struct AddressModel: Codable {
    let city: String?
    let country: String?
    let idAtlasCity: String?
    let idAtlasCountry: String?
    let idAtlasNeighborhood: String?
    let idAtlasState: String?
    let idCity: Int?
    let idCountry: Int?
    let idState: Int?
    let state: String?
    let street: String?
    let zipcode: String?
    let geoLocation: GeoLocationModel?
    
    enum CodingKeys: String, CodingKey {
        case city = "city"
        case country = "country"
        case idAtlasCity = "id_atlas_city"
        case idAtlasCountry = "id_atlas_country"
        case idAtlasNeighborhood = "id_atlas_neighborhood"
        case idAtlasState = "id_atlas_state"
        case idCity = "id_city"
        case idCountry = "id_country"
        case idState = "id_state"
        case state = "state"
        case street = "street"
        case zipcode = "zipcode"
        case geoLocation = "geoLocation"
    }
}

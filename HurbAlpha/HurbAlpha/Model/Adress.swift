//
//  Adress.swift
//  HurbAlpha
//
//  Created by Julia Rocha on 04/09/19.
//  Copyright © 2019 Julia Rocha. All rights reserved.
//

import Foundation

// MARK: - Address
class Address: Codable {
    let city: String
    let country: String
    let idAtlasCity, idAtlasCountry, idAtlasNeighborhood, idAtlasState: String?
    let idCity, idCountry, idState: Int?
    let state: String
    let street, zipcode: String?
    let geoLocation: GeoLocation
    
    enum CodingKeys: String, CodingKey {
        case city, country
        case idAtlasCity
        case idAtlasCountry
        case idAtlasNeighborhood
        case idAtlasState
        case idCity
        case idCountry
        case idState
        case state, street, zipcode, geoLocation
    }
    
    init(city: String, country: String, idAtlasCity: String?, idAtlasCountry: String?, idAtlasNeighborhood: String?, idAtlasState: String?, idCity: Int?, idCountry: Int?, idState: Int?, state: String, street: String?, zipcode: String?, geoLocation: GeoLocation) {
        self.city = city
        self.country = country
        self.idAtlasCity = idAtlasCity
        self.idAtlasCountry = idAtlasCountry
        self.idAtlasNeighborhood = idAtlasNeighborhood
        self.idAtlasState = idAtlasState
        self.idCity = idCity
        self.idCountry = idCountry
        self.idState = idState
        self.state = state
        self.street = street
        self.zipcode = zipcode
        self.geoLocation = geoLocation
    }

}

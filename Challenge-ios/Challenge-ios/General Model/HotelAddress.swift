//
//  HotelAddress.swift
//  Challenge-ios
//
//  Created by Andre Dias on 28/09/20.
//  Copyright © 2020 Andre Dias. All rights reserved.
//

import Foundation

// MARK: - Address
struct Address: Codable {
    let zipcode, addressFullAddress, street, addressStreetName: String?
    let streetName, address, fullAddress, neighborhood: String?
    let city: City
    let idCity: Int?
    let geoLocation: GeoLocation

    enum CodingKeys: String, CodingKey {
        case zipcode
        case addressFullAddress
        case street
        case addressStreetName
        case streetName, address, fullAddress, neighborhood
        case city
        case idCity
        case geoLocation
    }
}

enum City: String, Codable {
    case armaçãodosBúzios = "Armação dos Búzios"
    case búzios = "Búzios"
}

// MARK: - GeoLocation
struct GeoLocation: Codable {
    let lat, lon: Double
}

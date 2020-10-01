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
    let zipcode, street: String?
//    var neighborhood: String?
    let streetName, address, fullAddress: String?
    let state: String?
    let city: String
    let geoLocation: GeoLocation

    enum CodingKeys: String, CodingKey {
        case zipcode
        case street
        case streetName = "street_name"
        case address, fullAddress
        case state
//        case neighborhood = "neighborhood"
        case city
        case geoLocation
    }
    
    init(from decoder: Decoder) throws {
        let codingValue = try decoder.container(keyedBy: CodingKeys.self)
        zipcode = try codingValue.decode(String.self, forKey: .zipcode)
        street = try codingValue.decode(String.self, forKey: .street)
        streetName = try codingValue.decode(String.self, forKey: .streetName)
        address = try codingValue.decode(String.self, forKey: .address)
        fullAddress = try codingValue.decode(String.self, forKey: .fullAddress)
//        neighborhood = try codingValue.decode(String.self, forKey: .neighborhood)
        city = try codingValue.decode(String.self, forKey: .city)
        state = try codingValue.decode(String.self, forKey: .state)
        geoLocation = try codingValue.decode(GeoLocation.self, forKey: .geoLocation)
      }
}

// MARK: - GeoLocation
struct GeoLocation: Codable {
    let lat, lon: Double
    
    enum CodingKeys: String, CodingKey {
        case lat
        case lon
    }
    
    init(from decoder: Decoder) throws {
        let codingValue = try decoder.container(keyedBy: CodingKeys.self)
        lat = try codingValue.decode(Double.self, forKey: .lat)
        lon = try codingValue.decode(Double.self, forKey: .lon)
    }
}

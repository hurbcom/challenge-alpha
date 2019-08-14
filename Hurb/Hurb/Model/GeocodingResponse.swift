//
//  GeocodingResponse.swift
//  Hurb
//
//  Created by Alexandre Papanis on 13/08/19.
//  Copyright © 2019 Papanis. All rights reserved.
//

import Foundation

struct GeocondingResponse: Codable {
    var results: [ResultGeocoding]?
    var status: String?
}

struct ResultGeocoding: Codable {
    var formattedAddress: String?
    var geometry: Geometry
    
    enum CodingKeys: String, CodingKey {
        case formattedAddress = "formatted_address"
        case geometry
    }
}

struct Geometry: Codable {
    var location: Location?
    var bounds: Bounds?
}

struct Bounds: Codable {
    var southwest: Location?
    var northeast: Location?
}

struct Location: Codable {
    var lat: Double
    var lng: Double
}


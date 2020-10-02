//
//  PackageAddress.swift
//  Challenge-ios
//
//  Created by Andre Dias on 02/10/20.
//  Copyright © 2020 Andre Dias. All rights reserved.
//

import Foundation


// MARK: - Address
struct PackageAddress: Codable {
    var state: String = ""
    var city: String = ""
    var geoLocation: PackageGeoLocation?
    
    enum CodingKeys: String, CodingKey {
        case state, city
        case geoLocation
    }
       
    init(from decoder: Decoder) throws {
        if let codingValue = try? decoder.container(keyedBy: CodingKeys.self) {
            if let stateDecoded = try? codingValue.decode(String.self, forKey: .state) {
                self.state = stateDecoded
            }
            if let cityDecoded = try? codingValue.decode(String.self, forKey: .city) {
                self.city = cityDecoded
            }
            if let geoLocationDecoded = try? codingValue.decode(PackageGeoLocation.self, forKey: .geoLocation) {
                self.geoLocation = geoLocationDecoded
            }
        }
    }
}

// MARK: - GeoLocation
struct PackageGeoLocation: Codable {
    var lat: Double = 0.0
    var lon: Double = 0.0
    
    enum CodingKeys: String, CodingKey {
        case lat, lon
    }
    
    init(from decoder: Decoder) throws {
        if let codingValue = try? decoder.container(keyedBy: CodingKeys.self) {
            if let latDecoded = try? codingValue.decode(Double.self, forKey: .lat) {
                self.lat = latDecoded
            }
            if let lonDecoded = try? codingValue.decode(Double.self, forKey: .lon) {
                self.lon = lonDecoded
            }
        }
    }
}


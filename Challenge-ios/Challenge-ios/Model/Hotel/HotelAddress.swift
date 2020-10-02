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
    var zipcode: String = ""
    var street: String = ""
    var neighborhood: String = ""
    var streetName: String   = ""
    var address: String  = ""
    var fullAddress: String = ""
    var state: String = ""
    var city: String  = ""
    var geoLocation: GeoLocation?

    enum CodingKeys: String, CodingKey {
        case zipcode
        case street
        case streetName = "street_name"
        case address
        case fullAddress
        case state
        case neighborhood
        case city
        case geoLocation
    }
    
    init(from decoder: Decoder) throws {
        if let codingValue = try? decoder.container(keyedBy: CodingKeys.self) {
            if let zipcodeDecoded = try? codingValue.decode(String.self, forKey: .zipcode) {
                self.zipcode = zipcodeDecoded
            }
            if let streetDecoded = try? codingValue.decode(String.self, forKey: .street) {
                self.street = streetDecoded
            }
            if let streetNameDecoded = try? codingValue.decode(String.self, forKey: .streetName) {
                self.streetName = streetNameDecoded
            }
            if let addressDecoded = try? codingValue.decode(String.self, forKey: .address) {
                self.address = addressDecoded
            }
            if let fullAddressDecoded = try? codingValue.decode(String.self, forKey: .fullAddress) {
                self.fullAddress = fullAddressDecoded
            }
            if let neighborhoodDecoded = try? codingValue.decode(String.self, forKey: .neighborhood) {
                self.neighborhood = neighborhoodDecoded
            }
            if let cityDecoded = try? codingValue.decode(String.self, forKey: .city) {
                self.city = cityDecoded
            }
            if let stateDecoded = try? codingValue.decode(String.self, forKey: .state) {
                self.state = stateDecoded
            }
            if let geoLocationDecoded = try? codingValue.decode(GeoLocation.self, forKey: .geoLocation) {
                self.geoLocation = geoLocationDecoded
            }
        }
    }
}

// MARK: - GeoLocation
struct GeoLocation: Codable {
    var lat: Double = 0.0
    var lon: Double = 0.0
    
    enum CodingKeys: String, CodingKey {
        case lat
        case lon
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

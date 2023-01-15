//
//  Address.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 15/01/23.
//

import Foundation

// MARK: - Address
struct Address: Codable {
    var state: String?
    var city: String?
    var country: String?
    var geolocation: Geolocation?
}

// MARK: - Geolocation
struct Geolocation: Codable {
    var latitude: Double?
    var longitude: Double?
    
    enum CodingKeys: String, CodingKey {
        case latitude = "lat"
        case longitude = "lon"
    }
}

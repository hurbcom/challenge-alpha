//
//  Location.swift
//  Hurb-iOS-Challenge
//
//  Created by Rômulo Monteiro on 08/01/23.
//

import UIKit

struct Location: Codable {
    
    private enum CodingKeys: String, CodingKey {
        
        case state
        case country
        case city
        case geoLocation
    }
    
    let state: String
    let country: String
    let city: String
    let geoLocation: Geolocation
}

// Decode methods
extension Location {
    
    init(from decoder: Decoder) throws {
        
        let container = try decoder.container(keyedBy: CodingKeys.self)

        self.state = try container.decode(String.self, forKey: .state)
        self.country = try container.decode(String.self, forKey: .country)
        self.city = try container.decode(String.self, forKey: .city)
        self.geoLocation = try container.decode(Geolocation.self, forKey: .geoLocation)
    }
}

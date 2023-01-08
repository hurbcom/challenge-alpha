//
//  Geolocation.swift
//  Hurb-iOS-Challenge
//
//  Created by Rômulo Monteiro on 08/01/23.
//

import UIKit

struct Geolocation: Codable {
    
    private enum CodingKeys: String, CodingKey {
        
        case lat
        case lon
    }
    
    let lat: Double
    let lon: Double
}

// Decode methods
extension Geolocation {
    
    init(from decoder: Decoder) throws {
        
        let container = try decoder.container(keyedBy: CodingKeys.self)

        self.lat = try container.decode(Double.self, forKey: .lat)
        self.lon = try container.decode(Double.self, forKey: .lon)
    }
}

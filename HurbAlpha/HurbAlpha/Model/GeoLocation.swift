//
//  GeoLocation.swift
//  HurbAlpha
//
//  Created by Julia Rocha on 04/09/19.
//  Copyright © 2019 Julia Rocha. All rights reserved.
//

import Foundation

// MARK: - GeoLocation
class GeoLocation: Codable {
    let lat, lon: Double
    
    init(lat: Double, lon: Double) {
        self.lat = lat
        self.lon = lon
    }
}

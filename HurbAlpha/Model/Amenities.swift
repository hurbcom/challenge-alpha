//
//  Amenities.swift
//  HurbAlpha
//
//  Created by Rodrigo Bukowitz on 06/11/19.
//  Copyright © 2019 Rodrigo Bukowitz. All rights reserved.
//

import Foundation

/// Amenities from the package or Hotel
struct Amenities: Codable {
    var name: String
    var category: String
}
/// extension to represent all coding keys
extension Amenities {
    enum CodingKeys: String, CodingKey {
        case name
        case category
    }
}

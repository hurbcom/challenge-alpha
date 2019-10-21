//
//  Amenities.swift
//  Alpha
//
//  Created by Theo Mendes on 15/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

/// Amenities is something considered to benefit a property
struct Amenities: Codable {
    var name: String
    var category: String
    var planeIncluded: Bool {
        if (name == "Passagem Aérea") {
            return true
        } else {
            return false
        }
    }

    enum CodingKeys: String, CodingKey {
        case name, category
    }
}

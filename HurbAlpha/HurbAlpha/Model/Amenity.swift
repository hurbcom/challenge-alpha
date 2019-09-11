//
//  Amenity.swift
//  HurbAlpha
//
//  Created by Julia Rocha on 04/09/19.
//  Copyright © 2019 Julia Rocha. All rights reserved.
//

import Foundation

// MARK: - Declaration

struct Amenity: Codable {
    let name: String?
    let category: String?
}

// MARK: - Amenity Element Declaration

struct AmenityElement: Codable {
    let name: String
    let category: String
}

// MARK: - Purple Amenity Declaration

struct PurpleAmenity: Codable {
    let term, filter: String
    let count: Int
}

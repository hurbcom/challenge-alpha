//
//  FeaturedItem.swift
//  HurbAlpha
//
//  Created by Julia Rocha on 04/09/19.
//  Copyright © 2019 Julia Rocha. All rights reserved.
//

import Foundation

// MARK: - Declaration

class FeaturedItem: Codable {
    let amenities: [String]?
    let name: String?
    let image: String?
    let featuredItemDescription: String?
    let hasInternet, hasParking: Bool?
    
    // MARK: - Enum Coding Keys Declaration

    enum CodingKeys: String, CodingKey {
        case amenities, name, image
        case featuredItemDescription
        case hasInternet, hasParking
    }
    
    // MARK: - Initialization

    init(amenities: [String]?, name: String?, image: String?, featuredItemDescription: String?, hasInternet: Bool?, hasParking: Bool?) {
        self.amenities = amenities
        self.name = name
        self.image = image
        self.featuredItemDescription = featuredItemDescription
        self.hasInternet = hasInternet
        self.hasParking = hasParking
    }
  
}

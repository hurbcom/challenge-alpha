//
//  FeaturedItem.swift
//  DesafioHurb
//
//  Created by Edson Aparecido Guido on 13/09/20.
//  Copyright © 2020 Edson Aparecido Guido. All rights reserved.
//

import Foundation

struct FeaturedItem: Codable {
    let amenities: [String]?
    let name: String?
    let image: String?
    let featuredItemDescription: String
    let hasInternet, hasParking: Bool?

    enum CodingKeys: String, CodingKey {
        case amenities, name, image
        case featuredItemDescription = "description"
        case hasInternet, hasParking
    }
}

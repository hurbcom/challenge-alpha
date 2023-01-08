//
//  Hotel.swift
//  Hurb-iOS-Challenge
//
//  Created by Rômulo Monteiro on 06/01/23.
//

import UIKit

struct Hotel: Codable {
    
    private enum CodingKeys: String, CodingKey {
        
        case id
        case category
        case description
        case medias = "gallery"
        case isAvailable
        case huFreeCancellation
        case price
        case location = "address"
        case stars
        case name
        case amenities
    }
    
    let id: String?
    let category: Category
    let description: String
    let medias: [Media]
    let isAvailable: Bool
    let huFreeCancellation: Bool
    let price: Price
    let location: Location
    let stars: Int
    let name: String
    let amenities: [Amenity]
}

// Decode methods
extension Hotel {
    
    init(from decoder: Decoder) throws {
        
        let container = try decoder.container(keyedBy: CodingKeys.self)

        self.id = try? container.decodeIfPresent(String.self, forKey: .id)
        self.category = try container.decode(Category.self, forKey: .category)
        self.description = try container.decode(String.self, forKey: .description)
        self.medias = try container.decode([Media].self, forKey: .medias)
        self.isAvailable = try container.decode(Bool.self, forKey: .isAvailable)
        self.huFreeCancellation = try container.decode(Bool.self, forKey: .huFreeCancellation)
        self.price = try container.decode(Price.self, forKey: .price)
        self.location = try container.decode(Location.self, forKey: .location)
        self.stars = try container.decode(Int.self, forKey: .stars)
        self.name = try container.decode(String.self, forKey: .name)
        self.amenities = try container.decode([Amenity].self, forKey: .amenities)
    }
}

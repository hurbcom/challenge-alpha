//
//  FeaturedItemModel.swift
//  Hurb_iOS_Challenge
//
//  Created by Gáudio Ney on 22/05/22.
//

import Foundation

// MARK: - FeaturedItem
struct FeaturedItem: Codable {
    let amenities: [String]?
    let name: String?
    let image: String?
    let featuredItemDescription: String?
    
    enum CodingKeys: String, CodingKey {
        case amenities, name, image
        case featuredItemDescription = "description"
    }
}

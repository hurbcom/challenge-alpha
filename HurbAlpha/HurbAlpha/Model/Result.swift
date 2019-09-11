//
//  Result.swift
//  HurbAlpha
//
//  Created by Julia Rocha on 04/09/19.
//  Copyright © 2019 Julia Rocha. All rights reserved.
//

import Foundation

// MARK: - Declaration

struct Result: Codable {
    let sku: String
    let isPackage: Bool?
    let isHotel:Bool?
    let name: String
    let stars:Int?
    let url: String
    let smallDescription, resultDescription: String?
    let gallery: [Gallery]
    let address: Address
    let tags: [String]?
    let price: ResultPrice
    let featuredItem: FeaturedItem
    let category: String
    let quantityDescriptors: QuantityDescriptors
    let id: String
    let amenities: [Amenity]
    
    // MARK: - Enum Coding Keys Declaration
    
    enum CodingKeys: String, CodingKey {
        case sku, isHotel, name, url, smallDescription, isPackage, stars
        case resultDescription
        case gallery, address, tags, price, featuredItem, category, quantityDescriptors, id, amenities
    }
}

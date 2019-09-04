//
//  Result.swift
//  HurbAlpha
//
//  Created by Julia Rocha on 04/09/19.
//  Copyright © 2019 Julia Rocha. All rights reserved.
//

import Foundation

// MARK: - Result
class Result: Codable {
    let sku: String
    let isPackage: Bool?
    let isHotel:Bool?
    let name: String
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
    
    enum CodingKeys: String, CodingKey {
        case sku, isHotel, name, url, smallDescription, isPackage
        case resultDescription
        case gallery, address, tags, price, featuredItem, category, quantityDescriptors, id, amenities
    }
    
    init(sku: String, isPackage: Bool?, isHotel: Bool?, name: String, url: String, smallDescription: String?, resultDescription: String?, gallery: [Gallery], address: Address, tags: [String]?, price: ResultPrice, featuredItem: FeaturedItem, category: String, quantityDescriptors: QuantityDescriptors, id: String, amenities: [Amenity]) {
        self.sku = sku
        self.isPackage = isPackage
        self.isHotel = isHotel
        self.name = name
        self.url = url
        self.smallDescription = smallDescription
        self.resultDescription = resultDescription
        self.gallery = gallery
        self.address = address
        self.tags = tags
        self.price = price
        self.featuredItem = featuredItem
        self.category = category
        self.quantityDescriptors = quantityDescriptors
        self.id = id
        self.amenities = amenities
    }
}

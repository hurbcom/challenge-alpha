//
//  Hotel.swift
//  HurbChallenge
//
//  Created by Felipe Alves on 05/07/19.
//  Copyright © 2019 Bolzaniapps. All rights reserved.
//

import Foundation

struct Hotel: Codable {
    let sku: String
    let isHotel: Bool
    let category: String
    let smallDescription: String
    let amenities: [AmenityElement]
    let id: String
    let price: Price
    let huFreeCancellation: Bool
    let image: String
    let name: String
    let url: String
    let hotelDescription: String
    let stars: Int
    let gallery: [ImageElement]
    let address: Address
    let tags: [String]
    let quantityDescriptors: QuantityDescriptors
    let featuredItem: FeaturedItem
    
    enum CodingKeys: String, CodingKey {
        case sku
        case isHotel
        case category
        case smallDescription
        case amenities
        case id
        case price
        case huFreeCancellation = "hu_free_cancellation"
        case image, name, url
        case hotelDescription = "description"
        case stars
        case gallery
        case address
        case tags
        case quantityDescriptors
        case featuredItem
    }
}

// MARK: -
struct QuantityDescriptors: Codable {
    let maxChildren: Int
    let maxAdults: Int
    let maxFreeChildrenAge: Int
}

// MARK: -
struct AmenityElement: Codable {
    let name: String
    let category: String
}

// MARK: -
struct FeaturedItem: Codable {
    let amenities: [String]
    let name: String
    let image: String
    let featuredItemDescription: String
    
    enum CodingKeys: String, CodingKey {
        case amenities
        case name
        case image
        case featuredItemDescription = "description"
    }
}

// MARK: -
struct ImageElement: Codable {
    let imageDescription: String
    let url: String
    
    enum CodingKeys: String, CodingKey {
        case imageDescription = "description"
        case url
    }
}

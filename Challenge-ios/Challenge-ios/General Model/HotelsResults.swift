//
//  HotelsResults.swift
//  Challenge-ios
//
//  Created by Andre Dias on 28/09/20.
//  Copyright © 2020 Andre Dias. All rights reserved.
//

import Foundation

// MARK: - Result
struct Result: Codable {
    let sku: String
    let isHotel: Bool?
    let category: Category
    let smallDescription: String
    let amenities: [ResultAmenity]
    let id: String
    let price: ResultPrice
    let huFreeCancellation: Bool?
    let image: String?
    let name: String
    let url: String
    let resultDescription: String
    let stars: Int?
    let gallery: [Gallery]
    let address: Address
    let tags: [String]
    let quantityDescriptors: QuantityDescriptors
    let featuredItem: FeaturedItem
    let isPackage: Bool?
    let startDate, endDate: String?
    let hasAvailability: Bool?

    enum CodingKeys: String, CodingKey {
        case sku, isHotel, category, smallDescription, amenities, id, price
        case huFreeCancellation
        case image, name, url
        case resultDescription
        case stars, gallery, address, tags, quantityDescriptors, featuredItem, isPackage, startDate, endDate, hasAvailability
    }
}

// MARK: - ResultAmenity
struct ResultAmenity: Codable {
    let name, category: String
}

enum Category: String, Codable {
    case hospedagem = "hospedagem"
    case hotel = "hotel"
}

// MARK: - FeaturedItem
struct FeaturedItem: Codable {
    let amenities: [String]?
    let name: String?
    let image: String?
    let featuredItemDescription: String
    let hasInternet, hasParking: Bool?

    enum CodingKeys: String, CodingKey {
        case amenities, name, image
        case featuredItemDescription
        case hasInternet, hasParking
    }
}

// MARK: - Gallery
struct Gallery: Codable {
    let galleryDescription: String?
    let url: String

    enum CodingKeys: String, CodingKey {
        case galleryDescription
        case url
    }
}

// MARK: - QuantityDescriptors
struct QuantityDescriptors: Codable {
    let maxChildren, maxAdults, maxFreeChildrenAge, nights: Int?
    let maxPeople: Int?
}

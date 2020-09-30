//
//  HotelsResults.swift
//  Challenge-ios
//
//  Created by Andre Dias on 28/09/20.
//  Copyright © 2020 Andre Dias. All rights reserved.
//

import Foundation

// MARK: - Result
struct HotelsResults: Codable {
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
    let featuredItem: FeaturedItem

    enum CodingKeys: String, CodingKey {
        case sku, isHotel, category, smallDescription, amenities, id, price
        case huFreeCancellation = "hu_free_cancellation"
        case image, name, url
        case resultDescription = "description"
        case stars, gallery, address, tags, featuredItem
    }
    
    init(from decoder: Decoder) throws {
        let codingValue = try decoder.container(keyedBy: CodingKeys.self)
        sku = try codingValue.decode(String.self, forKey: .sku)
        isHotel = try codingValue.decode(Bool.self, forKey: .isHotel)
        category = try codingValue.decode(Category.self, forKey: .category)
        smallDescription = try codingValue.decode(String.self, forKey: .smallDescription)
        amenities = try codingValue.decode([ResultAmenity].self, forKey: .amenities)
        id = try codingValue.decode(String.self, forKey: .id)
        price = try codingValue.decode(ResultPrice.self, forKey: .price)
        huFreeCancellation = try codingValue.decode(Bool.self, forKey: .huFreeCancellation)
        image = try codingValue.decode(String.self, forKey: .image)
        name = try codingValue.decode(String.self, forKey: .name)
        url = try codingValue.decode(String.self, forKey: .url)
        resultDescription = try codingValue.decode(String.self, forKey: .resultDescription)
        stars = try codingValue.decode(Int.self, forKey: .stars)
        gallery = try codingValue.decode([Gallery].self, forKey: .gallery)
        address = try codingValue.decode(Address.self, forKey: .address)
        tags = try codingValue.decode([String].self, forKey: .tags)
        featuredItem = try codingValue.decode(FeaturedItem.self, forKey: .featuredItem)
    
    }
}

// MARK: - ResultAmenity
struct ResultAmenity: Codable {
    let name, category: String
    
    init(from decoder: Decoder) throws {
        let codingValue = try decoder.container(keyedBy: CodingKeys.self)
        name = try codingValue.decode(String.self, forKey: .name)
        category = try codingValue.decode(String.self, forKey: .category)
    }
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

    enum CodingKeys: String, CodingKey {
        case amenities, name, image
        case featuredItemDescription = "description"
    }
    
    init(from decoder: Decoder) throws {
        let codingValue = try decoder.container(keyedBy: CodingKeys.self)
        amenities = try codingValue.decode([String].self, forKey: .amenities)
        name = try codingValue.decode(String.self, forKey: .name)
        image = try codingValue.decode(String.self, forKey: .image)
        featuredItemDescription = try codingValue.decode(String.self, forKey: .featuredItemDescription)
    }
}

// MARK: - Gallery
struct Gallery: Codable {
    let galleryDescription: String?
    let url: String

    enum CodingKeys: String, CodingKey {
        case galleryDescription = "description"
        case url
    }
    
    init(from decoder: Decoder) throws {
        let codingValue = try decoder.container(keyedBy: CodingKeys.self)
        galleryDescription = try codingValue.decode(String.self, forKey: .galleryDescription)
        url = try codingValue.decode(String.self, forKey: .url)
    }
}

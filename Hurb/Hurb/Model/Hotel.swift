//
//  Result.swift
//  Hurb
//
//  Created by Alexandre Papanis on 08/08/19.
//  Copyright © 2019 Papanis. All rights reserved.
//

import Foundation

struct Hotel: Codable {
    let sku: String?
    let isHotel: Bool?
    let category: String?
    let smallDescription: String?
    let description: String?
    let amenities: [Amenity]?
    let id: String?
    let price: Price?
    let huFreeCancellation: Bool?
    let image: String?
    let name: String?
    let url: String?
    let packageDescription: String?
    let stars: Int?
    let gallery: [Gallery]?
    let address: Address?
    let tags: [String]?
    let quantityDescriptors: QuantityDescriptors?
    let featuredItem: FeaturedItem?
    let isPackage: Bool?
    let isTicket: Bool?
    
    enum CodingKeys: String, CodingKey {
        case sku
        case isHotel
        case category
        case smallDescription
        case description
        case amenities
        case id
        case price
        case huFreeCancellation = "hu_free_cancellation"
        case image
        case name
        case url
        case packageDescription
        case stars
        case gallery
        case address
        case tags
        case quantityDescriptors
        case featuredItem
        case isPackage
        case isTicket
    }
}

// MARK: - Amenity
struct Amenity: Codable {
    let name: String?
    let category: String?
}

// MARK: - Price
struct Price: Codable {
    let currency: String?
    let currencyOriginal: String?
    let sku: String?
    let originalAmountPerDay: Double?
    let amountPerDay: Double?
    let amount: Double?
    let oldPrice: Double?
    let currentPrice: Double?
    let originalAmount: Double?
    
    enum CodingKeys: String, CodingKey {
        case currency
        case currencyOriginal = "currency_original"
        case sku
        case originalAmountPerDay
        case amountPerDay
        case amount
        case oldPrice = "old_price"
        case currentPrice = "current_price"
        case originalAmount
    }
}

// MARK: - Gallery
struct Gallery: Codable {
    let description: String?
    let url: String?
}

// MARK: - Address
struct Address: Codable {
    let city: String?
    let country: String?
    let idAtlasCity: Int?
    let idAtlasCountry: Int?
    let idAtlasNeighborhood: Int?
    let idAtlasState: Int?
    let idCity: Int?
    let idCountry: Int?
    let idState: Int?
    let state: String?
    let street: String?
    let zipcode: String?
    let geoLocation: GeoLocation?
    
    enum CodingKeys: String, CodingKey {
        case city, country
        case idAtlasCity = "id_atlas_city"
        case idAtlasCountry = "id_atlas_country"
        case idAtlasNeighborhood = "id_atlas_neighborhood"
        case idAtlasState = "id_atlas_state"
        case idCity = "id_city"
        case idCountry = "id_country"
        case idState = "id_state"
        case state
        case street
        case zipcode
        case geoLocation
    }
}

// MARK: - GeoLocation
struct GeoLocation: Codable {
    let lat: Double?
    let lon: Double?
}

// MARK: - QuantityDescriptors
struct QuantityDescriptors: Codable {
    let maxChildren: Int?
    let maxAdults: Int?
    let maxFreeChildrenAge: Int?
    let nights: Int?
    let maxPeople: Int?
}

// MARK: - FeaturedItem
struct FeaturedItem: Codable {
    let amenities: [String]?
    let name: String?
    let image: String?
    let description: String
    let hasInternet: Bool?
    let hasParking: Bool?
}

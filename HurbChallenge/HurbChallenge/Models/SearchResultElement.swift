//
//  SearchResultElement.swift
//  HurbChallenge
//
//  Created by Felipe Alves on 05/07/19.
//  Copyright © 2019 Bolzaniapps. All rights reserved.
//

import Foundation

struct SearchResultElement: Codable {
    let sku: String
    let isHotel: Bool?
    let category: String
    let smallDescription: String
    let amenities: [Amenity]
    let id: String
    let price: Price
    let huFreeCancellation: Bool?
    let image: String?
    let name: String
    let url: String
    let packageDescription: String
    let stars: Int?
    let gallery: [ImageElement]
    let address: Address
    let tags: [String]?
    let quantityDescriptors: QuantityDescriptors
    let featuredItem: FeaturedItem
    let isPackage: Bool?
    let isTicket: Bool?
    
    enum CodingKeys: String, CodingKey {
        case sku
        case isHotel
        case category
        case smallDescription
        case amenities
        case id
        case price
        case huFreeCancellation = "hu_free_cancellation"
        case image
        case name
        case url
        case packageDescription = "description"
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

// MARK: - Address
struct Address: Codable {
    let city: String?
    let country: String
    let idAtlasCity: Int?
    let idAtlasCountry: Int?
    let idAtlasNeighborhood: Int?
    let idAtlasState: Int?
    let idCity: Int?
    let idCountry: Int?
    let idState: Int?
    let state: String
    let street: String?
    let zipcode: String?
    let geoLocation: GeoLocation
    
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

struct GeoLocation: Codable {
    let lat: Double?
    let lon: Double?
}

// MARK: - Amenity
struct Amenity: Codable {
    let name: String
    let category: String
}

// MARK: - FeaturedItem
struct FeaturedItem: Codable {
    let amenities: [String]?
    let name: String?
    let image: String?
    let featuredItemDescription: String
    let hasInternet: Bool?
    let hasParking: Bool?
    
    enum CodingKeys: String, CodingKey {
        case amenities
        case name
        case image
        case featuredItemDescription = "description"
        case hasInternet
        case hasParking
    }
}

// MARK: - Gallery
struct ImageElement: Codable {
    let imageDescription: String?
    let url: String
    
    enum CodingKeys: String, CodingKey {
        case imageDescription = "description"
        case url
    }
}

// MARK: - Price
struct Price: Codable {
    let currency: String?
    let currencyOriginal: String?
    let priceCurrentPrice: Double?
    let priceOldPrice: Double?
    let sku: String
    let originalAmountPerDay: Double?
    let amountPerDay: Double
    let amount: Double
    let oldPrice: Int?
    let currentPrice: Int?
    let originalAmount: Int?
    
    enum CodingKeys: String, CodingKey {
        case currency
        case currencyOriginal = "currency_original"
        case priceCurrentPrice = "current_price"
        case priceOldPrice = "old_price"
        case sku
        case originalAmountPerDay
        case amountPerDay
        case amount
        case oldPrice
        case currentPrice
        case originalAmount
    }
}

// MARK: - QuantityDescriptors
struct QuantityDescriptors: Codable {
    let maxChildren: Int?
    let maxAdults: Int?
    let maxFreeChildrenAge: Int?
    let nights: Int?
    let maxPeople: Int?
}

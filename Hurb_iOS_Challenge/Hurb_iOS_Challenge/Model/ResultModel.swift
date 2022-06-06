//
//  ResultModel.swift
//  Hurb_iOS_Challenge
//
//  Created by Gáudio Ney on 22/05/22.
//

import Foundation

// MARK: - Result
struct HotelResult: Codable {
    let sku: String?
    let isHotel: Bool?
    let category: ResultCategoryEnum?
    let smallDescription: String?
    let amenities: [ResultAmenity]?
    let id: String?
    let price: ResultPrice?
    let huFreeCancellation: Bool?
    let image: String?
    let name: String?
    let url: String?
    let resultDescription: String?
    let stars: Int?
    let gallery: [Gallery]?
    let address: Address?
    let tags: [String]?
    let quantityDescriptors: QuantityDescriptors?
    let featuredItem: FeaturedItem?
    
    enum CodingKeys: String, CodingKey {
        case sku, isHotel, category, smallDescription, amenities, id, price
        case huFreeCancellation = "hu_free_cancellation"
        case image, name, url
        case resultDescription = "description"
        case stars, gallery, address, tags, quantityDescriptors, featuredItem
    }
}

// MARK: - ResultAmenity
struct ResultAmenity: Codable {
    let name: String?
    let category: AmenityCategoryEnum?
}

// MARK: - ResultPrice
struct ResultPrice: Codable {
    let currency, currencyOriginal: CurrencyEnum?
    let currentPrice, oldPrice: Double?
    let sku: String?
    let originalAmountPerDay, amountPerDay, amount: Double?
    
    enum CodingKeys: String, CodingKey {
        case currency
        case currencyOriginal = "currency_original"
        case currentPrice = "current_price"
        case oldPrice = "old_price"
        case sku, originalAmountPerDay, amountPerDay, amount
    }
}

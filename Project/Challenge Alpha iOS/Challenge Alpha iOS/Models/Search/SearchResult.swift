//
//  SearchResults.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 15/01/23.
//

import Foundation

// MARK: - SearchResult
struct SearchResult: Codable {
    var id: String?
    var sku: String?
    var name: String?
    var url: String?
    var category: Category?
    var description: String?
    var smallDescription: String?
    var isAvailable: Bool?
    var price: Price?
    var address: Address?
    var tags: [Tag]?
    var gallery: [GalleryItem]?
    var amenities: [Amenity]?
    
    func toHotel() -> HotelResult {
        return HotelResult(
            id: id,
            sku: sku,
            name: name,
            url: url,
            stars: nil,
            isAvailable: isAvailable,
            huFreeCancellation: nil,
            description: description,
            price: price,
            address: address,
            tags: tags,
            gallery: gallery,
            amenities: amenities
        )
    }
    
    func toPackage() -> PackageResult {
        return PackageResult(
            id: id,
            sku: sku,
            name: name,
            description: description,
            category: category?.rawValue,
            url: url,
            startDate: nil,
            endDate: nil,
            isAvailable: isAvailable,
            quantityDescriptors: nil,
            price: price,
            address: address,
            gallery: gallery,
            amenities: amenities,
            tags: tags
        )
    }
}

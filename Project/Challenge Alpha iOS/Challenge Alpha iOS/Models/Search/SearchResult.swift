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
}

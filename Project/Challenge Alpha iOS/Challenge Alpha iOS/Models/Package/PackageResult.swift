//
//  PackageResult.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 16/01/23.
//

import Foundation

// MARK: - Package Result
struct PackageResult: Codable {
    var sku: String?
    var name: String?
    var startDate: String?
    var endDate: String?
    var isAvailable: Bool?
    var quantityDescriptors: QuantityDescriptors?
    var price: Price?
    var address: Address?
    var gallery: [GalleryItem]?
    var amenities: [Amenity]?
}

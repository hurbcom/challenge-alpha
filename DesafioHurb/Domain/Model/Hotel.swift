//
//  Hotel.swift
//  DesafioHurb
//
//  Created by Edson Aparecido Guido on 13/09/20.
//  Copyright © 2020 Edson Aparecido Guido. All rights reserved.
//

import Foundation

struct Hotel: Codable, Equatable {
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
    let resultDescription: String
    let stars: Int?
    let gallery: [Gallery]
    let address: Address
    let quantityDescriptors: QuantityDescriptors
    let featuredItem: FeaturedItem
    let isPackage: Bool?
    let startDate, endDate: String?
    let hasAvailability: Bool?

    enum CodingKeys: String, CodingKey {
        case sku, isHotel, category, smallDescription, amenities, id, price
        case huFreeCancellation = "hu_free_cancellation"
        case image, name, url
        case resultDescription = "description"
        case stars, gallery, address, quantityDescriptors, featuredItem, isPackage, startDate, endDate, hasAvailability
    }
}

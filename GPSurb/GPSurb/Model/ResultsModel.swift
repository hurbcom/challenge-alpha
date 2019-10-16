//
//  ResultsModel.swift
//  GPSurb
//
//  Created by Gilson Santos on 16/10/19.
//  Copyright © 2019 Gilson Santos. All rights reserved.
//

import Foundation

struct ResultsModel: Codable {
    let sku: String?
    let isHotel: Bool?
    let category: String?
    let smallDescription: String?
    let amenities: [AmenitiesModel]?
    let idResult: String?
    let price: PriceModel?
    let huFreeCancellation: Bool?
    let image: String?
    let name: String?
    let url: String?
    let description: String?
    let stars: Int?
    let gallery: [GalleryModel]?
    let address: AddressModel?
    let tags: [String]?
    let quantityDescriptors: QuantityDescriptorsModel?
    let featuredItem: FeaturedItemModel?
    
    enum CodingKeys: String, CodingKey {
        case sku = "sku"
        case isHotel = "isHotel"
        case category = "category"
        case smallDescription = "smallDescription"
        case amenities = "amenities"
        case idResult = "id"
        case price = "price"
        case huFreeCancellation = "hu_free_cancellation"
        case image = "image"
        case name = "name"
        case url = "url"
        case description = "description"
        case stars = "stars"
        case gallery = "gallery"
        case address = "address"
        case tags = "tags"
        case quantityDescriptors = "quantityDescriptors"
        case featuredItem = "featuredItem"
    }
}

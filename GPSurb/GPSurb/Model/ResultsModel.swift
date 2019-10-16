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
    let id: String?
    let price: PriceModel?
    let hu_free_cancellation: Bool?
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
}

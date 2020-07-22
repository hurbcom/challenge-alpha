//
//  Product.swift
//  HurbHotel_iOS
//
//  Created by Helio Junior on 22/07/20.
//  Copyright © 2020 HelioTecnologia. All rights reserved.
//

import Foundation

struct Product: Decodable {
    let id: String
    let sku: String
    let address: String?
    let amenities: String?
    let category: String?
    let description: String?
    let featuredItem: String?
    let gallery: String?
    let hu_free_cancellation: Bool
    let image: String?
    let isHotel: Bool
    let name: String?
    let price: String?
    let quantityDescriptors: String?
    let smallDescription: String?
    let stars: Int?
    let tags: String?
    let url: String?
}

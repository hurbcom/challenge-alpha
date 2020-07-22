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
    let url: String?
    let image: String?
    let description: String?
    let smallDescription: String?
    let name: String?
    let amenities: [Amenities]?
    let price: Price?
    let category: String?
    let featuredItem: FeatureItem?
    let stars: Int?
    let gallery: [Gallery]?
    let hu_free_cancellation: Bool
    let isHotel: Bool
    //let address: String?
    //let quantityDescriptors: String?
    //let tags: String?
}

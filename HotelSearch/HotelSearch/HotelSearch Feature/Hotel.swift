//
//  Hotel.swift
//  HotelSearch
//
//  Created by Tulio Parreiras on 13/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import Foundation

struct Hotel {
    let amenities: [Amenity]
    let category: String
    let description: String
    let gallery: [HotelImage]
    let id: Int
    let image: URL
    let isHotel: Bool
    let name: String
    let price: HotelPrice
    let quantityDescriptors: QuantityDescriptor
    let smallDescription: String
    let star: Int
    let tags: [String]
    let url: URL
}

struct Amenity {
    let count: Int
    let filter: String
    let term: String
}

struct HotelImage {
    let description: String
    let roomId: String?
    let url: URL
}

struct HotelPrice {
    let amount: Double
    let amountPerDay: Double
    let currency: String
}

struct QuantityDescriptor {
    let maxAdults: Int
    let maxChildren: Int
    let maxFreeChildrenAge: Int
}

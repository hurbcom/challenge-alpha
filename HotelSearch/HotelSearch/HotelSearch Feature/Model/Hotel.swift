//
//  Hotel.swift
//  HotelSearch
//
//  Created by Tulio Parreiras on 13/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import Foundation

public struct Hotel: Hashable {
    public let amenities: [Amenity]
    public let category: String
    public let description: String
    public let gallery: [HotelImage]
    public let id: Int
    public let image: URL
    public let isHotel: Bool
    public let name: String
    public let price: HotelPrice
    public let quantityDescriptors: QuantityDescriptor
    public let smallDescription: String
    public let star: Int
    public let tags: [String]
    public let url: URL
    
    public init(amenities: [Amenity], category: String, description: String, gallery: [HotelImage], id: Int, image: URL, isHotel: Bool, name: String, price: HotelPrice, quantityDescriptors: QuantityDescriptor, smallDescription: String, star: Int, tags: [String], url: URL) {
        self.amenities = amenities
        self.category = category
        self.description = description
        self.gallery = gallery
        self.id = id
        self.image = image
        self.isHotel = isHotel
        self.name = name
        self.price = price
        self.quantityDescriptors = quantityDescriptors
        self.smallDescription = smallDescription
        self.star = star
        self.tags = tags
        self.url = url
    }
}

public struct Amenity: Hashable {
    public let category: String
    public let name: String
    
    public init(category: String, name: String) {
        self.category = category
        self.name = name
    }
}

public struct HotelImage: Hashable {
    public let description: String
    public let url: URL
    
    public init(description: String, url: URL) {
        self.description = description
        self.url = url
    }
}

public struct HotelPrice: Hashable {
    public let amount: Double
    public let amountPerDay: Double
    public let currency: String
    
    public init(amount: Double, amountPerDay: Double, currency: String) {
        self.amount = amount
        self.amountPerDay = amountPerDay
        self.currency = currency
    }
}

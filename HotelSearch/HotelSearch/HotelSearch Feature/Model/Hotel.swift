//
//  Hotel.swift
//  HotelSearch
//
//  Created by Tulio Parreiras on 13/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import Foundation

public struct Hotel: Hashable {
    public let address: Address?
    public let amenities: [Amenity]?
    public let category: String?
    public let description: String?
    public let gallery: [HotelImage]?
    public let id: String?
    public let image: URL?
    public let isHotel: Bool?
    public let name: String?
    public let price: HotelPrice?
    public let quantityDescriptors: QuantityDescriptor?
    public let smallDescription: String?
    public let stars: Int?
    public let tags: [String]?
    public let url: URL?
    
    public init(
        address: Address?,
        amenities: [Amenity]?,
        category: String?,
        description: String?,
        gallery: [HotelImage]?,
        id: String?,
        image: URL?,
        isHotel: Bool?,
        name: String?,
        price: HotelPrice?,
        quantityDescriptors: QuantityDescriptor?,
        smallDescription: String?,
        stars: Int?,
        tags: [String]?,
        url: URL?) {
        
        self.address = address
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
        self.stars = stars
        self.tags = tags
        self.url = url
    }
}

//
//  Hotel.swift
//  Alpha
//
//  Created by Theo Mendes on 15/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

import Foundation

struct Deal: Codable {
    var sku: String
    var name: String
    var smallDescription: String
    var description: String
    var gallery: [Image]
    var category: String
    var url: URL
    var amenities: [Amenities]
    var price: Price
    var address: Address
    // Package attributes
    var isPackage: Bool?
    // Hotel attributes
    var isHotel: Bool?
    var image: URL?
    var stars: Int?
    var huFreeCancellation: Bool?

    enum CodingKeys: String, CodingKey {
        case sku
        case name
        case smallDescription
        case description
        case gallery
        case category
        case url
        case amenities
        case price
        case address
        // Package keys
        case isPackage
        // Hotel keys
        case isHotel
        case huFreeCancellation = "hu_free_cancellation"
        case image
        case stars
    }
}

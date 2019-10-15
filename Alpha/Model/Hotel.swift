//
//  Hotel.swift
//  Alpha
//
//  Created by Theo Mendes on 15/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

import Foundation

struct Hotel: Codable {
    var sku: String
    var name: String
    var image: URL
    var category: String
    var smallDescription: String
    var amenities: [Amenities]
    var cancellation: Bool
    var url: URL
    var stars: Int
    var gallery: [Image]
    
    enum CodingKeys: String, CodingKey {
        case sku, name, image, category, smallDescription, amenities
        case cancellation = "hu_free_cancellation"
        case url, stars, gallery
    }
}

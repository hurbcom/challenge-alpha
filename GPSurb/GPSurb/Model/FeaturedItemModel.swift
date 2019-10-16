//
//  FeaturedItemModel.swift
//  GPSurb
//
//  Created by Gilson Santos on 16/10/19.
//  Copyright © 2019 Gilson Santos. All rights reserved.
//

import Foundation

struct FeaturedItemModel: Codable {
    let amenities: [String]?
    let name: String?
    let image: String?
    let description: String?
}

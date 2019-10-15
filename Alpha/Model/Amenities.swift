//
//  Amenities.swift
//  Alpha
//
//  Created by Theo Mendes on 15/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

import Foundation

struct Amenities: Codable {
    var name: String
    var category: String
    
    enum CodingKeys: String, CodingKey {
        case name, category
    }
}

//
//  Amenities.swift
//  HotelUrbanoChallenge
//
//  Created by maciosdev on 25/07/2018.
//  Copyright © 2018 Ronilson. All rights reserved.
//

import Foundation

struct Amenities: Codable {
    let name: String?
    let category : String?
    
    enum CodingKeys: String, CodingKey {
        
        case name
        case category
    }
    
    init(from decoder: Decoder) throws {
        let values = try decoder.container(keyedBy: CodingKeys.self)
        name = try values.decodeIfPresent(String.self, forKey: .name)
        category = try values.decodeIfPresent(String.self, forKey: .category)
    }
    
}

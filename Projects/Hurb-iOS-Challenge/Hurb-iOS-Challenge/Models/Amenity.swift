//
//  Amenity.swift
//  Hurb-iOS-Challenge
//
//  Created by Rômulo Monteiro on 08/01/23.
//

import UIKit

struct Amenity: Codable {
    
    private enum CodingKeys: String, CodingKey {
        
        case name
        case category
    }
    
    let name: String
    let category: String
}

// Decode methods
extension Amenity {
    
    init(from decoder: Decoder) throws {
        
        let container = try decoder.container(keyedBy: CodingKeys.self)

        self.name = try container.decode(String.self, forKey: .name)
        self.category = try container.decode(String.self, forKey: .category)
    }
}

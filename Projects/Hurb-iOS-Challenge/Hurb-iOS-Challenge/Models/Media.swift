//
//  Media.swift
//  Hurb-iOS-Challenge
//
//  Created by Rômulo Monteiro on 08/01/23.
//

import UIKit

struct Media: Codable {
    
    private enum CodingKeys: String, CodingKey {
        
        case url
    }
    
    let url: String
}

// Decode methods
extension Media {
    
    init(from decoder: Decoder) throws {
        
        let container = try decoder.container(keyedBy: CodingKeys.self)

        self.url = try container.decode(String.self, forKey: .url)
    }
}

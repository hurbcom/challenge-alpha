//
//  Image.swift
//  Challange HURB - Alpha
//
//  Created by Luiz Fernando Cunha Duarte on 28/10/19.
//  Copyright © 2019 Luiz Fernando Cunha Duarte. All rights reserved.
//

import Foundation

/// Represents the experiences images
struct Image: Codable {
    // MARK: - Properties
    
    var url: URL
    var description: String?
    
    // MARK: - Coding Keys
    
    enum CodingKeys: String, CodingKey {
        case url
        case description
    }
}

//
//  Amenity.swift
//  Challange HURB - Alpha
//
//  Created by Luiz Fernando Cunha Duarte on 11/11/19.
//  Copyright © 2019 Luiz Fernando Cunha Duarte. All rights reserved.
//

import Foundation

/// Benefits that the experirnce provides
struct Amenity: Codable {
    // MARK: - Properties
    var name: String
    var category: String
    
    // MARK: - Coding Keys
    enum CodingKeys: String, CodingKey {
        case name, category
    }
}

//
//  AmenityElement.swift
//  HurbAlpha
//
//  Created by Julia Rocha on 04/09/19.
//  Copyright © 2019 Julia Rocha. All rights reserved.
//

import Foundation

// MARK: - AmenityElement
class AmenityElement: Codable {
    let name: String
    let category: String
    
    init(name: String, category: String) {
        self.name = name
        self.category = category
    }
}

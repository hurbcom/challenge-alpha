//
//  Amenity.swift
//  HotelSearch
//
//  Created by Tulio Parreiras on 14/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import Foundation

public struct Amenity: Hashable {
    public let category: String
    public let name: String
    
    public init(category: String, name: String) {
        self.category = category
        self.name = name
    }
}


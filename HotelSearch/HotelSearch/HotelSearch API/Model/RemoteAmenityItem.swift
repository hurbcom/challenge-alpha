//
//  RemoteAmenityItem.swift
//  HotelSearch
//
//  Created by Tulio Parreiras on 14/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import Foundation

struct RemoteAmenityItem: Decodable {
    let category: String?
    let name: String?
    
    var item: Amenity {
        return Amenity(category: category, name: name)
    }
}

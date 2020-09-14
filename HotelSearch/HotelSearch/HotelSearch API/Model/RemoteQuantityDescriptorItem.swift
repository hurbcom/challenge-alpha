//
//  RemoteQuantityDescriptorItem.swift
//  HotelSearch
//
//  Created by Tulio Parreiras on 14/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import Foundation

struct RemoteQuantityDescriptorItem: Decodable {
    let maxAdults: Int
    let maxChildren: Int
    let maxFreeChildrenAge: Int
    
    var item: QuantityDescriptor {
        return QuantityDescriptor(maxAdults: maxAdults, maxChildren: maxChildren, maxFreeChildrenAge: maxFreeChildrenAge)
    }
}

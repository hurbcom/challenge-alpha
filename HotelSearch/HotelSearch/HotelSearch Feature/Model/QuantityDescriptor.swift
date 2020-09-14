//
//  QuantityDescriptor.swift
//  HotelSearch
//
//  Created by Tulio Parreiras on 14/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import Foundation

public struct QuantityDescriptor: Hashable {
    public let maxAdults: Int
    public let maxChildren: Int
    public let maxFreeChildrenAge: Int
    
    public init(maxAdults: Int, maxChildren: Int, maxFreeChildrenAge: Int) {
        self.maxAdults = maxAdults
        self.maxChildren = maxChildren
        self.maxFreeChildrenAge = maxFreeChildrenAge
    }
}


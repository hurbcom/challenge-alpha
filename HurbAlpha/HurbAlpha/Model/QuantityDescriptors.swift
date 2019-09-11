//
//  QuantityDescriptors.swift
//  HurbAlpha
//
//  Created by Julia Rocha on 04/09/19.
//  Copyright © 2019 Julia Rocha. All rights reserved.
//

import Foundation

// MARK: - Declaration

class QuantityDescriptors: Codable {
    let maxChildren, maxAdults, maxFreeChildrenAge, nights: Int?
    let maxPeople: Int?
    
    // MARK: - Initialization

    init(maxChildren: Int?, maxAdults: Int?, maxFreeChildrenAge: Int?, nights: Int?, maxPeople: Int?) {
        self.maxChildren = maxChildren
        self.maxAdults = maxAdults
        self.maxFreeChildrenAge = maxFreeChildrenAge
        self.nights = nights
        self.maxPeople = maxPeople
    }
}

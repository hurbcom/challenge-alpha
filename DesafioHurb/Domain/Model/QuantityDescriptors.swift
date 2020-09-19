//
//  QuantityDescriptors.swift
//  DesafioHurb
//
//  Created by Edson Aparecido Guido on 13/09/20.
//  Copyright © 2020 Edson Aparecido Guido. All rights reserved.
//

import Foundation

struct QuantityDescriptors: Codable, Equatable {
    let maxChildren: Int?
    let maxAdults: Int?
    let maxFreeChildrenAge: Int?
    let nights: Int?
    let maxPeople: Int?
}

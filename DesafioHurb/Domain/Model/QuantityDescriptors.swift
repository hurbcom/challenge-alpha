//
//  QuantityDescriptors.swift
//  DesafioHurb
//
//  Created by Edson Aparecido Guido on 13/09/20.
//  Copyright © 2020 Edson Aparecido Guido. All rights reserved.
//

import Foundation

struct QuantityDescriptors: Codable {
    let maxChildren, maxAdults, maxFreeChildrenAge, nights: Int?
    let maxPeople: Int?
}

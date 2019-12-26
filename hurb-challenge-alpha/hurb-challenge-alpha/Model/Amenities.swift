//
//  Amenities.swift
//  hurb-challenge-alpha
//
//  Created by Hannah  on 26/12/2019.
//  Copyright © 2019 Hannah . All rights reserved.
//

import Foundation

struct Amenities: Decodable, Identifiable {
    let name: String?
    let category: String?
    let id = UUID()
}

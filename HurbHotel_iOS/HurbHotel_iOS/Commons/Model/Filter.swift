//
//  Filter.swift
//  HurbHotel_iOS
//
//  Created by Helio Junior on 22/07/20.
//  Copyright © 2020 HelioTecnologia. All rights reserved.
//

import Foundation

struct Filter: Decodable {
    let count: Int
    let filter: String
    let term: String
}

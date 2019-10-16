//
//  PricesModel.swift
//  GPSurb
//
//  Created by Gilson Santos on 16/10/19.
//  Copyright © 2019 Gilson Santos. All rights reserved.
//

import Foundation

struct PricesModel: Codable {
    let min: Int?
    let maxExclusive: Int?
    let filter: String?
    let count: Int?
}

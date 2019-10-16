//
//  PriceIntervalModel.swift
//  GPSurb
//
//  Created by Gilson Santos on 16/10/19.
//  Copyright © 2019 Gilson Santos. All rights reserved.
//

import Foundation

struct PriceIntervalModel: Codable {
    let min: Int?
    let max: Int?
    let filterPattern: String?
}

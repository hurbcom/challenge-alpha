//
//  FeatureItem.swift
//  HurbHotel_iOS
//
//  Created by Helio Junior on 22/07/20.
//  Copyright © 2020 HelioTecnologia. All rights reserved.
//

import Foundation

struct FeatureItem: Decodable {
    let name: String?
    let image: String?
    let description: String?
    let amenities: [String]?
}

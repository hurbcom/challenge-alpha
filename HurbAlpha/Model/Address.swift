//
//  Address.swift
//  HurbAlpha
//
//  Created by Rodrigo Bukowitz on 06/11/19.
//  Copyright © 2019 Rodrigo Bukowitz. All rights reserved.
//

import Foundation

/// Address from the package or Hotel
struct HurbAddress: Codable {
    var state: String
    var city: String
}
/// extension to represent all coding keys
extension HurbAddress {
    enum CodingKeys: String, CodingKey {
        case state
        case city
    }
}

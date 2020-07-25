//
//  Product.swift
//  HurbHotel_iOS
//
//  Created by Helio Junior on 22/07/20.
//  Copyright © 2020 HelioTecnologia. All rights reserved.
//

import Foundation

struct Product: Decodable {
    let image: String?
    let description: String?
    let smallDescription: String?
    let name: String?
    let price: Price?
}

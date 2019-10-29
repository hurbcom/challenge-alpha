//
//  Package.swift
//  Challange HURB - Alpha
//
//  Created by Luiz Fernando Cunha Duarte on 26/10/19.
//  Copyright © 2019 Luiz Fernando Cunha Duarte. All rights reserved.
//

import Foundation

struct Package: Codable {
    let atributtes: [String]
    let country: String
    let city: String
    let departureCity: String
    let duration: String
    let foodIncluded: String
    let numberOfPeople: Int
    let price: Float
    let regulation: String
    let state: String
}

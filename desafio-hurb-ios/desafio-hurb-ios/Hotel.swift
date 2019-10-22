//
//  Hotel.swift
//  desafio-hurb-ios
//
//  Created by Vinícius Barcelos on 21/10/19.
//  Copyright © 2019 Vinícius Barcelos. All rights reserved.
//

import Foundation

class Hotel: Codable {
    var name: String
    var image: String?
    var price: Price
    var address: Address
}

class Price: Codable {
    var amount: Double
}

class Address: Codable {
    var city: String
    var state: String
}

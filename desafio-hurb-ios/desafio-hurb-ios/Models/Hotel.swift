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
    var stars: Int?
    var amenities: [Amenity]
    
    func first3Amenities() -> String {
        var initialString: String = ""
        for amenity in amenities {
            initialString += "\(amenity.name)\n"
        }
        return initialString
    }
}

class Price: Codable {
    var amount: Double
    
    func inCurrency() -> String{
        let formatter = NumberFormatter()
        formatter.numberStyle = .currency
        formatter.locale = Locale(identifier: "pt_BR")
        return formatter.string(from: NSNumber(value: amount))!
    }
}

class Address: Codable {
    var city: String
    var state: String
}

class Amenity: Codable {
    var name: String
    var category: String
}

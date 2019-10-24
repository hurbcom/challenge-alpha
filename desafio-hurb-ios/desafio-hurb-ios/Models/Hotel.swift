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
    var smallDescription: String
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
    
    func fullAdress() -> String {
        return self.address.street == nil ? "\(self.address.city) - \(self.address.state)" : "\(self.address.street ?? "") - \(self.address.city) - \(self.address.state)"
    }
    
    func hotelStars() -> String {
         guard let stars = self.stars else { return "PACOTE" }
         var text: String = ""
         for _ in 1...stars {
             text.append("⭐")
         }
         return text
     }
    
    // Init for Unittest
    init(stars: Int?) {
        self.name = ""
        self.image = nil
        self.price = Price(amount: 0)
        self.smallDescription = ""
        self.address = Address(city: "", state: "", street: "")
        self.stars = stars
        self.amenities = [Amenity(name: "", category: "")]
    }
    
    init(address: Address) {
        self.name = ""
        self.image = nil
        self.price = Price(amount: 0)
        self.smallDescription = ""
        self.address = Address(city: address.city, state: address.state, street: address.street)
        self.stars = 1
        self.amenities = [Amenity(name: "", category: "")]
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
    
    init(amount: Double) {
        self.amount = amount
    }
}

class Address: Codable {
    var city: String
    var state: String
    var street: String?
    
    // Init for Unittest
    init(city: String, state: String, street: String?) {
        self.city = city
        self.state = state
        self.street = street
    }
}

class Amenity: Codable {
    var name: String
    var category: String
    
    // Init for Unittest
    init(name: String, category: String) {
        self.name = name
        self.category = category
    }
}

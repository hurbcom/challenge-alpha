//
//  Hotels.swift
//  Hurb Alpha
//
//  Created by Thiago Augusto on 24/07/20.
//  Copyright © 2020 objectivesev. All rights reserved.
//

import Foundation
import ObjectMapper

class QueryResult: Mappable {
    var results: [Result]?
    
    required init?(map: Map) {}
    
    func mapping(map: Map) {
        self.results <- map["results"]
    }
}

class Result: Mappable {
    var isHotel: Bool?
    var isPackage: Bool?
    var name: String?
    var price: Price?
    var image: String?
    var stars: Int?
    var address: Address?
    var amenities: [Amenities]?
    
    required init?(map: Map) {}
    
    func mapping(map: Map) {
        self.isHotel <- map["isHotel"]
        self.isPackage <- map["isPackage"]
        self.name <- map["name"]
        self.price <- map["price"]
        self.image <- map["image"]
        self.stars <- map["stars"]
        self.address <- map["address"]
        self.amenities <- map["amenities"]
    }
}

class Price: Mappable {
    var amount: Double?
    
    required init?(map: Map) {}
    
    func mapping(map: Map) {
        self.amount <- map["amount"]
    }
}

class Address: Mappable {
    var city: String?
    var state: String?
    
    required init?(map: Map) {}
    
    func mapping(map: Map) {
        self.city <- map["city"]
        self.state <- map["state"]
    }
}

class Amenities: Mappable {
    var name: String?
    
    required init?(map: Map) {}
    
    func mapping(map: Map) {
        self.name <- map["name"]
    }
}

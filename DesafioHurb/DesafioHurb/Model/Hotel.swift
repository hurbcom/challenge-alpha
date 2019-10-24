//
//  Hotel.swift
//  DesafioHurb
//
//  Created by Filipo Negrao on 22/10/19.
//  Copyright © 2019 Filipo Negrao. All rights reserved.
//

import Foundation

class Hotel {
    public var name : String = ""
    public var description : String = ""
    public var imageUrl : String = ""
    public var url: String = ""
    public var stars: Int = 0
    public var images: [String] = [String]()
    public var price: Float64 = Float64(0)
    public var city: String = ""
    public var state: String = ""
    public var amenities: [String] = [String]()
    public var isPackage : Bool = false
    public var isHotel: Bool = false
    
    class func fromDict(dict: [String: Any]) -> Hotel {
        let hotel = Hotel()
        if let name = dict["name"] as? String {
            hotel.name = name
        }
        if let description = dict["description"] as? String {
            hotel.description = description
        }
        if let imageUrl = dict["image"] as? String {
            hotel.imageUrl = imageUrl
        }
        if let stars = dict["stars"] as? Int {
            hotel.stars = stars
        }
        if let images = dict["gallery"] as? [[String: Any]] {
            for image in images {
                if let url = image["url"] as? String {
                    if hotel.imageUrl == "" {
                        hotel.imageUrl = url
                    }
                    hotel.images.append(url)
                }
            }
        }
        if let priceJson = dict["price"] as? [String: Any] {
            if let price = priceJson["current_price"] as? Float64 {
                hotel.price = price
            } else if let price = priceJson["currentPrice"] as? Float64 {
                  hotel.price = price
              }
        }
        if let addressJson = dict["address"] as? [String: Any] {
            if let state = addressJson["state"] as? String {
                hotel.state = state
            }
            if let city = addressJson["city"] as? String {
                hotel.city = city
            }
        }
        if let amenitiesJson = dict["amenities"] as? [[String: Any]] {
            for amenetyJson in amenitiesJson {
                if let amenity = amenetyJson["name"] as? String {
                    hotel.amenities.append(amenity)
                }
            }
        }
        if let isPackage = dict["isPackage"] as? Bool {
            hotel.isPackage = isPackage
        }
        if let isHotel = dict["isHotel"] as? Bool {
            hotel.isHotel = isHotel
        }
        return hotel
    }
    
    func getThreeAmenitiesString() -> String {
        var result = ""
        for i in 0...2 {
            if self.amenities.count > i {
                let amenity = self.amenities[i]
                if i != 0 {
                    result += ", "
                }
                result += amenity
            }
        }
        return result
    }
    
    func getAmenitiesString() -> String {
        var result = ""
        var index = 0
        for amenity in self.amenities {
            if index != 0 {
                result += ", "
            }
            index += 1
            result += amenity
        }
        return result
    }
}

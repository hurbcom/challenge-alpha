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
    
    class func fromDict(dict: [String: Any]) -> Hotel {
        let hotel = Hotel()
        if let name = dict["name"] as? String {
            hotel.name = name
        }
        if let description = dict["smallDescription"] as? String {
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
                    hotel.images.append(url)
                }
            }
        }
        if let priceJson = dict["price"] as? [String: Any] {
            if let price = priceJson["current_price"] as? Float64 {
                hotel.price = price
            }
        }
        return hotel
    }
}

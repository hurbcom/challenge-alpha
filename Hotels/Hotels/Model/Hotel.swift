//
//  Hotel.swift
//  Hotels
//
//  Created by Adolfho Athyla on 24/08/2018.
//  Copyright © 2018 a7hyla. All rights reserved.
//

import UIKit
import EVReflection

class Hotel: EVObject {
    var id: String?
    var isHotel = false
    var isPackage = false
    var name: String?
    var image: String?
    var stars: NSNumber?
    var address: Address?
    var price: Price?
    var amenities = [Amenity]()
    var gallery = [Photo]()
    
    var adType: AdType {
        get {
            return isHotel ? .Hotel : (isPackage ? .Package : .Hotel)
        }
    }
    
    static func getHotels(hotels: [Hotel]) -> [Hotel] {
        let hotels = hotels.filter { (hotel) -> Bool in
            return hotel.adType == .Hotel
        }
        return hotels
    }
    
    static func getPackages(hotels: [Hotel]) -> [Hotel] {
        let packages = hotels.filter { (hotel) -> Bool in
            return hotel.adType == .Package
        }
        return packages
    }
    
    static func getHotelsWith(stars: Int, hotels: [Hotel]) -> [Hotel] {
        let hotels = hotels.filter { (hotel) -> Bool in
            return hotel.adType == .Hotel && hotel.stars?.intValue == stars
        }
        return hotels
    }
    
    static func existsPackage(hotels: [Hotel]) -> Bool {
        return !Hotel.getPackages(hotels: hotels).isEmpty
    }
    
    static func getStarsThatContainHotels(hotels: [Hotel]) -> [Int] {
        var stars = [Int]()
        if !Hotel.getHotelsWith(stars: 5, hotels: hotels).isEmpty { stars.append(5) }
        if !Hotel.getHotelsWith(stars: 4, hotels: hotels).isEmpty { stars.append(4) }
        if !Hotel.getHotelsWith(stars: 3, hotels: hotels).isEmpty { stars.append(3) }
        if !Hotel.getHotelsWith(stars: 2, hotels: hotels).isEmpty { stars.append(2) }
        if !Hotel.getHotelsWith(stars: 1, hotels: hotels).isEmpty { stars.append(1) }
        return stars
    }
}

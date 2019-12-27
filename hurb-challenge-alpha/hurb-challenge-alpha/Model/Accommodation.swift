//
//  Accommodation.swift
//  hurb-challenge-alpha
//
//  Created by Hannah  on 26/12/2019.
//  Copyright © 2019 Hannah . All rights reserved.
//

import Foundation

struct Accommodations: Decodable {
  
    let results: [Accommodation]
}

struct AccommodationGroup: Identifiable, Decodable {
    let id = UUID()
    let stars: Int
    let value: [Accommodation]
}

struct Accommodation: Identifiable, Decodable {
    let id: String
    let isHotel: Bool?
    let isPackage: Bool?
    let smallDescription: String?
    let description: String?
    let image: String?
    let name: String?
    let stars: Int?
    let amenities: [Amenities]?
    let huFreeCancellation: Bool?
    let price: Price?
    let address: Addres?
    let gallery: [Gallery]?
    let url: String?
    
    //protocol rename property keys
    private enum CodingKeys: String, CodingKey {
        case id
        case isHotel
        case isPackage
        case smallDescription
        case description
        case image
        case name
        case stars
        case amenities
        case huFreeCancellation = "hu_free_cancellation"
        case price
        case address
        case gallery
        case url
    }
}

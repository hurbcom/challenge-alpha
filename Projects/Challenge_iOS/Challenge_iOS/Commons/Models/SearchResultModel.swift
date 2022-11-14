//
//  SearchResultModel.swift
//  Challenge_iOS
//
//  Created by Helio Junior on 12/11/22.
//

import Foundation

struct SearchResultModel: Decodable {
    let id: String?
    let name: String
    let description: String
    let smallDescription: String
    let category: String
    let isAvailable: Bool
    let huFreeCancellation: Bool?
    let gallery: [Gallery]
    let sku: String
    let price: Price?
    let address: Address?
    let amenities: [Amenities]
    
    struct Gallery: Decodable {
        let url: String
    }
    
    struct Price: Decodable {
        let amount: Double
        let currency: String
        let taxes: [Taxe]?
    }
    
    struct Taxe: Decodable {
        let originalAmount: Double?
        let originalCurrency: String?
    }
    
    struct Address: Decodable {
        let state: String
        let country: String
        let city: String
        let geoLocation: Geolocation?
    }
    
    struct Geolocation: Decodable {
        let lat: Double
        let lon: Double
    }
    
      struct Amenities: Decodable {
          let name: String
          let category: String
      }
}

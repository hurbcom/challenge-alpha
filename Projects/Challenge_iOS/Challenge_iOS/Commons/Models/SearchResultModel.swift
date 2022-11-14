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
    let category: Category
    let isAvailable: Bool
    let huFreeCancellation: Bool?
    let gallery: [Gallery]
    let sku: String
    let price: Price?
    let address: Address?
    let amenities: [Amenities]
    
    enum Category: String, Codable {
        case hotel
        case pacote
        case comboDiarias = "combo_de_diarias"
        case `default`
        
        init(from decoder: Decoder) throws {
            self = try Category(rawValue: decoder.singleValueContainer().decode(RawValue.self)) ?? .default
        }
    }
    
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

extension SearchResultModel {
    func getAddressFormatted() -> String {
        if let city = address?.city,
           let state = address?.state,
           let country = address?.country {
            return "\(city), \(state), \(country)"
        } else {
            return "Endereço não informado"
        }
    }
    
    func getAmount() -> String {
        switch category {
        case .hotel:
            let amountValue = price?.amount ?? 0
            return amountValue.formatCurrency(from: price?.currency).description
            
        default:
            let amountFixValue = (price?.amount ?? 0) / 100
            return amountFixValue.formatCurrency(from: price?.currency).description
        }
    }
}

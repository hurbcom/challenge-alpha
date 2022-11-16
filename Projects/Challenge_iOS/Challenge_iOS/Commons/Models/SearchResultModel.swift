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
    let quantityDescriptors: QuantityDescriptors?
    let startDate: String?
    let endDate: String?
    let stars: Int?
    
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
        let category: String?
    }
    
    struct QuantityDescriptors: Decodable {
        let maxPeople: Int
        let duration: Int
    }
}

extension SearchResultModel {
    func getStarsDescription() -> String {
        if let stars = stars {
            return "Hotel \(stars) estrelas"
        }
        return "não informado"
    }
    
    func getAddressFormatted() -> String? {
        if let city = address?.city,
           let country = address?.country {
            return "\(city), \(country)"
        } else {
            return "endereço não informado"
        }
    }
    
    func getAddressShortFormatted() -> String? {
        if let city = address?.city,
           let country = address?.country {
            return "\(city)"
        } else {
            return "endereço não informado"
        }
    }
    
    func getAmount() -> String? {
        switch category {
        case .hotel:
            let amountValue = price?.amount ?? 0
            return amountValue.formatCurrency(from: price?.currency).description
            
        default:
            let amountFixValue = (price?.amount ?? 0) / 100
            return amountFixValue.formatCurrency(from: price?.currency).description
        }
    }
    
    func getAmountShort() -> String? {
        switch category {
        case .hotel:
            let amountValue = price?.amount ?? 0
            return Int(round(amountValue)).description
            
        default:
            let amountFixValue = (price?.amount ?? 0) / 100
            return Int(round(amountFixValue)).description
        }
    }
    
    func getDailysLabel() -> String? {
        if let duration = quantityDescriptors?.duration {
            return duration == 1 ? "1 diária" : "\(duration) diárias"
        }
        return nil
    }
    
    func getPersonsLabel() -> String? {
        if let maxPeople = quantityDescriptors?.maxPeople {
            return maxPeople == 1 ? "1 pessoa" : "\(maxPeople) pessoas"
        }
        return nil
    }
    
    func getSecondAmenitie() -> String? {
        amenities.first?.name
    }
}

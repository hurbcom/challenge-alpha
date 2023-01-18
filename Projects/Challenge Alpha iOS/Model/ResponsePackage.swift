//
//  ResponsePackage.swift
//  Challenge Alpha iOS (iOS)
//
//  Created by Kleiton Mendes on 17/01/23.
//

import Foundation

// MARK: - Welcome
struct ResponsePackage: Codable {
    let filters: Filters
    let results: [Result]
}

// MARK: - Filters
//struct Filters: Decodable {
//    let attributes, countries, cities, departureCities: [Attribute]
//    let duration, food, people: [Attribute]
//    let prices: [PriceElement]
//    let priceInterval: PriceInterval
//    let productType, regulation, states: [Attribute]
//}

// MARK: - Attribute
struct Attribute: Codable {
    let term, filter: String
    let count: Int
}

// MARK: - PriceInterval
//struct PriceInterval: Codable {
//    let min, max: Int
//    let filterPattern: String
//}

// MARK: - PriceElement
//struct PriceElement: Codable {
//    let min, maxExclusive: Int
//    let filter: String
//    let count: Int
//}

// MARK: - Result
//struct Result: Codable {
//    let sku: String
//    let isPackage: Bool
//    let name: String
//    let url: String
//    let smallDescription, description: String
//    let gallery: [Gallery]
//    let address: Address
//    let tags: [String]?
//    let price: ResultPrice
//    let featuredItem: FeaturedItem
//    let category: ResultCategory
//    let quantityDescriptors: QuantityDescriptors
//    let id: String
//    let amenities: [Amenity]
//}

// MARK: - Address
//struct Address: Codable {
//    let country: Country
//    let state: State
//    let city: City
//    let geoLocation: GeoLocation
//}

//enum City: String, Codable {
//    case gramado = "Gramado"
//}
//
//enum Country: String, Codable {
//    case brasil = "Brasil"
//}

// MARK: - GeoLocation
//struct GeoLocation: Codable {
//    let lat, lon: Double
//}

//enum State: String, Codable {
//    case rioGrandeDoSul = "Rio Grande do Sul"
//}

// MARK: - Amenity
struct Amenity: Codable {
    let name: Name
    let category: AmenityCategory
}

//enum AmenityCategory: String, Codable {
//    case acomodações = "Acomodações"
//    case alimentação = "Alimentação"
//    case ingresso = "Ingresso"
//    case passagemAérea = "Passagem aérea"
//    case passeio = "Passeio"
//    case traslado = "Traslado"
//}

enum Name: String, Codable {
    case apartamento = "Apartamento"
    case caféDaManhã = "Café da manhã"
    case caféDaManhãJantar = "Café da manhã, jantar"
    case ingresso = "Ingresso"
    case passagemAérea = "Passagem Aérea"
    case passeio = "Passeio"
    case traslado = "Traslado"
}

//enum ResultCategory: String, Codable {
//    case hospedagem = "hospedagem"
//}

// MARK: - FeaturedItem
//struct FeaturedItem: Codable {
//    let description: Description
//    let hasInternet, hasParking: Bool
//}

enum Description: String, Codable {
    case apartamentoDuplo = "Apartamento duplo."
    case descriptionApartamentoDuplo = "Apartamento duplo"
    case purpleApartamentoDuplo = "Apartamento duplo "
}

// MARK: - Gallery
//struct Gallery: Codable {
//    let url: String
//}

// MARK: - ResultPrice
//struct ResultPrice: Codable {
//    let oldPrice, currentPrice: Int
//    let sku: String
//    let originalAmount: Int
//    let amountPerDay: Double
//    let amount: Int
//}

//// MARK: - QuantityDescriptors
//struct QuantityDescriptors: Codable {
//    let nights, maxPeople: Int
//}

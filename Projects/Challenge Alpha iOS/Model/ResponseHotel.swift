//
//  ResponseHotel.swift
//  Challenge Alpha iOS (iOS)
//
//  Created by Kleiton Mendes on 17/01/23.
//

import UIKit

// MARK: - Welcome
struct ResponseHotel: Codable {
    let filters: Filters
    let results: [Result]
}

// MARK: - Filters
struct Filters: Codable {
    let amenities, countries, cities: [CityElement]
    let prices: [PriceElement]
    let priceInterval: PriceInterval
    let productType, rooms, stars, states: [CityElement]
}

// MARK: - CityElement
struct CityElement: Codable {
    let term, filter: String
    let count: Int
}

// MARK: - PriceInterval
struct PriceInterval: Codable {
    let min, max: Int
    let filterPattern: String
}

// MARK: - PriceElement
struct PriceElement: Codable {
    let min, maxExclusive: Int
    let filter: String
    let count: Int
}

// MARK: - Result
struct Result: Codable {
    let sku: String
    let isHotel: Bool
    let category: ResultCategory
    let smallDescription: String
    let amenities: [ResultAmenity]
    let id: String
    let price: ResultPrice
    let huFreeCancellation: Bool
    let image: String
    let name: String
    let url: String
    let description: String
    let stars: Int
    let gallery: [Gallery]
    let address: Address
    let tags: [String]
    let quantityDescriptors: QuantityDescriptors
    let featuredItem: FeaturedItem

    enum CodingKeys: String, CodingKey {
        case sku, isHotel, category, smallDescription, amenities, id, price
        case huFreeCancellation = "hu_free_cancellation"
        case image, name, url, description, stars, gallery, address, tags, quantityDescriptors, featuredItem
    }
}

// MARK: - Address
struct Address: Codable {
    let city: City
    let country: Country
    let idAtlasCity, idAtlasCountry: JSONNull?
    let idAtlasNeighborhood: JSONNull?
    let idAtlasState: JSONNull?
    let idCity, idCountry, idState: Int
    let state: State
    let street, zipcode: String
    let geoLocation: GeoLocation

    enum CodingKeys: String, CodingKey {
        case city, country
        case idAtlasCity = "id_atlas_city"
        case idAtlasCountry = "id_atlas_country"
        case idAtlasNeighborhood = "id_atlas_neighborhood"
        case idAtlasState = "id_atlas_state"
        case idCity = "id_city"
        case idCountry = "id_country"
        case idState = "id_state"
        case state, street, zipcode, geoLocation
    }
}

enum City: String, Codable {
    case gramado = "Gramado"
}

enum Country: String, Codable {
    case brasil = "Brasil"
}

// MARK: - GeoLocation
struct GeoLocation: Codable {
    let lat, lon: Double
}

enum State: String, Codable {
    case rioGrandeDoSul = "Rio Grande do Sul"
}

// MARK: - ResultAmenity
struct ResultAmenity: Codable {
    let name: String
    let category: AmenityCategory
}

enum AmenityCategory: String, Codable {
    case acomodação = "Acomodação"
    case atividades = "Atividades"
    case comidaBebida = "Comida / Bebida"
    case comodidadesInstalaçõesParaNegócios = "Comodidades / Instalações para negócios"
    case diversos = "Diversos"
    case entretenimentoEServiçosParaFamílias = "Entretenimento e serviços para famílias"
    case lojas = "Lojas"
    case opçõesDeTransporte = "Opções de transporte"
    case piscinaEComodidadesDeBemEstar = "Piscina e comodidades de bem-estar"
    case serviçosDeLimpezaLavanderia = "Serviços de limpeza / Lavanderia"
    case serviçosDeRecepção = "Serviços de recepção"
    case áreasComuns = "Áreas comuns"
}

enum ResultCategory: String, Codable {
    case hotel = "hotel"
}

// MARK: - FeaturedItem
struct FeaturedItem: Codable {
    let amenities: [String]
    let name: String
    let image: String
    let description: String
}

// MARK: - Gallery
struct Gallery: Codable {
    let description: String
    let url: String
}

// MARK: - ResultPrice
struct ResultPrice: Codable {
    let currency, currencyOriginal: Currency
    let currentPrice, oldPrice: Double
    let sku: String
    let originalAmountPerDay, amountPerDay, amount: Double

    enum CodingKeys: String, CodingKey {
        case currency
        case currencyOriginal = "currency_original"
        case currentPrice = "current_price"
        case oldPrice = "old_price"
        case sku, originalAmountPerDay, amountPerDay, amount
    }
}

enum Currency: String, Codable {
    case brl = "BRL"
}

// MARK: - QuantityDescriptors
struct QuantityDescriptors: Codable {
    let maxChildren, maxAdults, maxFreeChildrenAge: Int
}

// MARK: - Encode/decode helpers

class JSONNull: Codable, Hashable {

    public static func == (lhs: JSONNull, rhs: JSONNull) -> Bool {
        return true
    }

    public var hashValue: Int {
        return 0
    }

    public init() {}

    public required init(from decoder: Decoder) throws {
        let container = try decoder.singleValueContainer()
        if !container.decodeNil() {
            throw DecodingError.typeMismatch(JSONNull.self, DecodingError.Context(codingPath: decoder.codingPath, debugDescription: "Wrong type for JSONNull"))
        }
    }

    public func encode(to encoder: Encoder) throws {
        var container = encoder.singleValueContainer()
        try container.encodeNil()
    }
}

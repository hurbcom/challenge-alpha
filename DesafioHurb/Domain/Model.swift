//
//  Model.swift
//  DesafioHurb
//
//  Created by Edson Aparecido Guido on 04/09/20.
//  Copyright © 2020 Edson Aparecido Guido. All rights reserved.
//

import Foundation

// MARK: - FetchHotelsResponse
struct FetchHotelsResponse: Codable {
    let meta: Meta
    let filters: Filters
    let results: [Hotel]
    let pagination: Pagination
}

// MARK: - Filters
struct Filters: Codable {
    let amenities, attributes, countries, cities: [AttributeElement]
    let departureCities, duration, food, people: [AttributeElement]
    let prices: [PriceElement]
    let priceInterval: PriceInterval
    let productType, regulation, rooms, stars: [AttributeElement]
    let states: [AttributeElement]
}

// MARK: - AttributeElement
struct AttributeElement: Codable {
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

// MARK: - Meta
struct Meta: Codable {
    let count, offset: Int
    let query, warning: String
    let countWithAvailability, countWithAvailabilityInPage, countHotel, countPackage: Int
    let countTicket, countBustrip, countDisney: Int
}

// MARK: - Pagination
struct Pagination: Codable {
    let count: Int
    let firstPage, nextPage: String
    let previousPage: String?
    let lastPage: String
}

// MARK: - Result
struct Hotel: Codable {
    let sku: String
    let isHotel: Bool?
    let category: Category
    let smallDescription: String
    let amenities: [ResultAmenity]
    let id: String
    let price: ResultPrice
    let huFreeCancellation: Bool?
    let image: String?
    let name: String
    let url: String
    let resultDescription: String
    let stars: Int?
    let gallery: [Gallery]
    let address: Address
    let tags: [String]
    let quantityDescriptors: QuantityDescriptors
    let featuredItem: FeaturedItem
    let isPackage: Bool?
    let startDate, endDate: String?
    let hasAvailability: Bool?

    enum CodingKeys: String, CodingKey {
        case sku, isHotel, category, smallDescription, amenities, id, price
        case huFreeCancellation = "hu_free_cancellation"
        case image, name, url
        case resultDescription = "description"
        case stars, gallery, address, tags, quantityDescriptors, featuredItem, isPackage, startDate, endDate, hasAvailability
    }
}

// MARK: - Address
struct Address: Codable {
    let zipcode, addressFullAddress, street, addressStreetName: String?
    let streetName, address, fullAddress, neighborhood: String?
    let idAtlasNeighborhood, idNeighborhood: String?
    let city: String
    let idAtlasCity: Int?
    let idCity: Int?
    let state: State
    let idAtlasState: Int?
    let idState: Int?
    let country: Country
    let idAtlasCountry: Int?
    let idCountry: Int?
    let countryAlfa2, addressCountryAlfa2: CountryAlfa2?
    let geoLocation: GeoLocation

    enum CodingKeys: String, CodingKey {
        case zipcode
        case addressFullAddress = "full_address"
        case street
        case addressStreetName = "street_name"
        case streetName, address, fullAddress, neighborhood
        case idAtlasNeighborhood = "id_atlas_neighborhood"
        case idNeighborhood = "id_neighborhood"
        case city
        case idAtlasCity = "id_atlas_city"
        case idCity = "id_city"
        case state
        case idAtlasState = "id_atlas_state"
        case idState = "id_state"
        case country
        case idAtlasCountry = "id_atlas_country"
        case idCountry = "id_country"
        case countryAlfa2
        case addressCountryAlfa2 = "country_alfa2"
        case geoLocation
    }
}

enum CountryAlfa2: String, Codable {
    case br = "BR"
}

enum Country: String, Codable {
    case brasil = "Brasil"
}

// MARK: - GeoLocation
struct GeoLocation: Codable {
    let lat, lon: Double
}

enum State: String, Codable {
    case rioDeJaneiro = "Rio de Janeiro"
}

// MARK: - ResultAmenity
struct ResultAmenity: Codable {
    let name, category: String
}

enum Category: String, Codable {
    case hospedagem
    case hotel
}

// MARK: - FeaturedItem
struct FeaturedItem: Codable {
    let amenities: [String]?
    let name: String?
    let image: String?
    let featuredItemDescription: String
    let hasInternet, hasParking: Bool?

    enum CodingKeys: String, CodingKey {
        case amenities, name, image
        case featuredItemDescription = "description"
        case hasInternet, hasParking
    }
}

// MARK: - Gallery
struct Gallery: Codable {
    let galleryDescription: String?
    let url: String
    let roomID: String?

    enum CodingKeys: String, CodingKey {
        case galleryDescription = "description"
        case url
        case roomID = "room_id"
    }
}

// MARK: - ResultPrice
struct ResultPrice: Codable {
    let amount: Double
    let priceOldPrice: Double?
    let currency, currencyOriginal: Currency?
    let gain, feeExtraOriginal, gainOriginal: Int?
    let tariffPolicies: [String]?
    let priceCurrentPrice, totalPrice: Double?
    let feeExtra: Int?
    let sku: String
    let taxes: [Tax]?
    let originalAmountPerDay: Double?
    let amountPerDay: Double
    let oldPrice, currentPrice, originalAmount: Int?

    enum CodingKeys: String, CodingKey {
        case amount
        case priceOldPrice = "old_price"
        case currency
        case currencyOriginal = "currency_original"
        case gain
        case feeExtraOriginal = "fee_extra_original"
        case gainOriginal = "gain_original"
        case tariffPolicies = "tariff_policies"
        case priceCurrentPrice = "current_price"
        case totalPrice = "total_price"
        case feeExtra = "fee_extra"
        case sku, taxes, originalAmountPerDay, amountPerDay, oldPrice, currentPrice, originalAmount
    }
}

enum Currency: String, Codable {
    case brl = "BRL"
}

// MARK: - Tax
struct Tax: Codable {
    let type: TypeEnum
    let name: Name
    let amount, amountOriginal: Double
    let currency, currencyOriginal: Currency

    enum CodingKeys: String, CodingKey {
        case type, name, amount
        case amountOriginal = "amount_original"
        case currency
        case currencyOriginal = "currency_original"
    }
}

enum Name: String, Codable {
    case taxaDeReserva = "Taxa de Reserva"
}

enum TypeEnum: String, Codable {
    case perItemQuantity = "PER_ITEM_QUANTITY"
}

// MARK: - QuantityDescriptors
struct QuantityDescriptors: Codable {
    let maxChildren, maxAdults, maxFreeChildrenAge, nights: Int?
    let maxPeople: Int?
}

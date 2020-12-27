// This file was generated from JSON Schema using quicktype, do not modify it directly.
// To parse the JSON, add this file to your project and do:
//
//   let productsPagination = try? newJSONDecoder().decode(ProductsPagination.self, from: jsonData)

import Foundation

// MARK: - HotelsPaginationModel
struct HotelsPaginationModel: Codable {
    let meta: Meta
    let filters: Filters
    let results: [Hotels]
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
    let previousPage: JSONNull?
    let lastPage: String
}

// MARK: - Result
struct Hotels: Codable {
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
    let tags: [String]?
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
    let idAtlasNeighborhood, idNeighborhood: JSONNull?
    let city: City
    let idAtlasCity: JSONNull?
    let idCity: Int?
    let state: State
    let idAtlasState: JSONNull?
    let idState: Int?
    let country: Country
    let idAtlasCountry: JSONNull?
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

enum City: String, Codable {
    case armaçãoDOSBúzios = "Armação dos Búzios"
    case búzios = "Búzios"
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
    case hospedagem = "hospedagem"
    case hotel = "hotel"
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
    let galleryDescription: Description?
    let url: String
    let roomID: JSONNull?

    enum CodingKeys: String, CodingKey {
        case galleryDescription = "description"
        case url
        case roomID = "room_id"
    }
}

enum Description: String, Codable {
    case aPousadaContaCOM8BlocosSuitesPRBloco = "A pousada conta com 8 Blocos, Suites pr Bloco"
    case confortoEQualidade = "Conforto e qualidade"
    case curtaDeNossaPiscinaJuntoABar = "Curta de nossa Piscina, junto a Bar"
    case empty = ""
}

// MARK: - ResultPrice
struct ResultPrice: Codable {
    let amount: Double
    let priceOldPrice: Double?
    let currency, currencyOriginal: Currency?
    let gain, feeExtraOriginal, gainOriginal: Int?
    let tariffPolicies: [JSONAny]?
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

// MARK: - Encode/decode helpers

class JSONNull: Codable, Hashable {

    public static func == (lhs: JSONNull, rhs: JSONNull) -> Bool {
        return true
    }

    public var hashValue: Int {
        return 0
    }

    public func hash(into hasher: inout Hasher) {
        // No-op
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

class JSONCodingKey: CodingKey {
    let key: String

    required init?(intValue: Int) {
        return nil
    }

    required init?(stringValue: String) {
        key = stringValue
    }

    var intValue: Int? {
        return nil
    }

    var stringValue: String {
        return key
    }
}

class JSONAny: Codable {

    let value: Any

    static func decodingError(forCodingPath codingPath: [CodingKey]) -> DecodingError {
        let context = DecodingError.Context(codingPath: codingPath, debugDescription: "Cannot decode JSONAny")
        return DecodingError.typeMismatch(JSONAny.self, context)
    }

    static func encodingError(forValue value: Any, codingPath: [CodingKey]) -> EncodingError {
        let context = EncodingError.Context(codingPath: codingPath, debugDescription: "Cannot encode JSONAny")
        return EncodingError.invalidValue(value, context)
    }

    static func decode(from container: SingleValueDecodingContainer) throws -> Any {
        if let value = try? container.decode(Bool.self) {
            return value
        }
        if let value = try? container.decode(Int64.self) {
            return value
        }
        if let value = try? container.decode(Double.self) {
            return value
        }
        if let value = try? container.decode(String.self) {
            return value
        }
        if container.decodeNil() {
            return JSONNull()
        }
        throw decodingError(forCodingPath: container.codingPath)
    }

    static func decode(from container: inout UnkeyedDecodingContainer) throws -> Any {
        if let value = try? container.decode(Bool.self) {
            return value
        }
        if let value = try? container.decode(Int64.self) {
            return value
        }
        if let value = try? container.decode(Double.self) {
            return value
        }
        if let value = try? container.decode(String.self) {
            return value
        }
        if let value = try? container.decodeNil() {
            if value {
                return JSONNull()
            }
        }
        if var container = try? container.nestedUnkeyedContainer() {
            return try decodeArray(from: &container)
        }
        if var container = try? container.nestedContainer(keyedBy: JSONCodingKey.self) {
            return try decodeDictionary(from: &container)
        }
        throw decodingError(forCodingPath: container.codingPath)
    }

    static func decode(from container: inout KeyedDecodingContainer<JSONCodingKey>, forKey key: JSONCodingKey) throws -> Any {
        if let value = try? container.decode(Bool.self, forKey: key) {
            return value
        }
        if let value = try? container.decode(Int64.self, forKey: key) {
            return value
        }
        if let value = try? container.decode(Double.self, forKey: key) {
            return value
        }
        if let value = try? container.decode(String.self, forKey: key) {
            return value
        }
        if let value = try? container.decodeNil(forKey: key) {
            if value {
                return JSONNull()
            }
        }
        if var container = try? container.nestedUnkeyedContainer(forKey: key) {
            return try decodeArray(from: &container)
        }
        if var container = try? container.nestedContainer(keyedBy: JSONCodingKey.self, forKey: key) {
            return try decodeDictionary(from: &container)
        }
        throw decodingError(forCodingPath: container.codingPath)
    }

    static func decodeArray(from container: inout UnkeyedDecodingContainer) throws -> [Any] {
        var arr: [Any] = []
        while !container.isAtEnd {
            let value = try decode(from: &container)
            arr.append(value)
        }
        return arr
    }

    static func decodeDictionary(from container: inout KeyedDecodingContainer<JSONCodingKey>) throws -> [String: Any] {
        var dict = [String: Any]()
        for key in container.allKeys {
            let value = try decode(from: &container, forKey: key)
            dict[key.stringValue] = value
        }
        return dict
    }

    static func encode(to container: inout UnkeyedEncodingContainer, array: [Any]) throws {
        for value in array {
            if let value = value as? Bool {
                try container.encode(value)
            } else if let value = value as? Int64 {
                try container.encode(value)
            } else if let value = value as? Double {
                try container.encode(value)
            } else if let value = value as? String {
                try container.encode(value)
            } else if value is JSONNull {
                try container.encodeNil()
            } else if let value = value as? [Any] {
                var container = container.nestedUnkeyedContainer()
                try encode(to: &container, array: value)
            } else if let value = value as? [String: Any] {
                var container = container.nestedContainer(keyedBy: JSONCodingKey.self)
                try encode(to: &container, dictionary: value)
            } else {
                throw encodingError(forValue: value, codingPath: container.codingPath)
            }
        }
    }

    static func encode(to container: inout KeyedEncodingContainer<JSONCodingKey>, dictionary: [String: Any]) throws {
        for (key, value) in dictionary {
            let key = JSONCodingKey(stringValue: key)!
            if let value = value as? Bool {
                try container.encode(value, forKey: key)
            } else if let value = value as? Int64 {
                try container.encode(value, forKey: key)
            } else if let value = value as? Double {
                try container.encode(value, forKey: key)
            } else if let value = value as? String {
                try container.encode(value, forKey: key)
            } else if value is JSONNull {
                try container.encodeNil(forKey: key)
            } else if let value = value as? [Any] {
                var container = container.nestedUnkeyedContainer(forKey: key)
                try encode(to: &container, array: value)
            } else if let value = value as? [String: Any] {
                var container = container.nestedContainer(keyedBy: JSONCodingKey.self, forKey: key)
                try encode(to: &container, dictionary: value)
            } else {
                throw encodingError(forValue: value, codingPath: container.codingPath)
            }
        }
    }

    static func encode(to container: inout SingleValueEncodingContainer, value: Any) throws {
        if let value = value as? Bool {
            try container.encode(value)
        } else if let value = value as? Int64 {
            try container.encode(value)
        } else if let value = value as? Double {
            try container.encode(value)
        } else if let value = value as? String {
            try container.encode(value)
        } else if value is JSONNull {
            try container.encodeNil()
        } else {
            throw encodingError(forValue: value, codingPath: container.codingPath)
        }
    }

    public required init(from decoder: Decoder) throws {
        if var arrayContainer = try? decoder.unkeyedContainer() {
            self.value = try JSONAny.decodeArray(from: &arrayContainer)
        } else if var container = try? decoder.container(keyedBy: JSONCodingKey.self) {
            self.value = try JSONAny.decodeDictionary(from: &container)
        } else {
            let container = try decoder.singleValueContainer()
            self.value = try JSONAny.decode(from: container)
        }
    }

    public func encode(to encoder: Encoder) throws {
        if let arr = self.value as? [Any] {
            var container = encoder.unkeyedContainer()
            try JSONAny.encode(to: &container, array: arr)
        } else if let dict = self.value as? [String: Any] {
            var container = encoder.container(keyedBy: JSONCodingKey.self)
            try JSONAny.encode(to: &container, dictionary: dict)
        } else {
            var container = encoder.singleValueContainer()
            try JSONAny.encode(to: &container, value: self.value)
        }
    }
}

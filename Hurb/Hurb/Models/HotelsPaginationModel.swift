// This file was generated from JSON Schema using quicktype, do not modify it directly.
// To parse the JSON, add this file to your project and do:
//
//   let hotelsPaginationModel = try? newJSONDecoder().decode(HotelsPaginationModel.self, from: jsonData)

import Foundation

// MARK: - HotelsPaginationModel
struct HotelsPaginationModel: Codable {
    var meta: Meta?
    var filters: Filters?
    var results: [Hotel]?
    var pagination: Pagination?
}

struct GroupedHotels {
    let section: Int?
    let hotels: [Hotel]?
}

// MARK: - Filters
struct Filters: Codable {
    var amenities, attributes, countries, cities: [AttributeElement]?
    var departureCities, duration, food, people: [AttributeElement]?
    var prices: [PriceElement]?
    var priceInterval: PriceInterval?
    var productType, regulation, rooms, stars: [AttributeElement]?
    var states: [AttributeElement]?
}

// MARK: - AttributeElement
struct AttributeElement: Codable {
    var term, filter: String?
    var count: Int?
}

// MARK: - PriceInterval
struct PriceInterval: Codable {
    var min, max: Int?
    var filterPattern: String?
}

// MARK: - PriceElement
struct PriceElement: Codable {
    var min, maxExclusive: Int?
    var filter: String?
    var count: Int?
}

// MARK: - Meta
struct Meta: Codable {
    var count, offset: Int?
    var warning: String?
    var countWithAvailability, countWithAvailabilityInPage, countHotel, countPackage: Int?
    var countTicket, countBustrip, countDisney: Int?
}

// MARK: - Pagination
struct Pagination: Codable {
    var count: Int?
    var firstPage, nextPage: String?
    var previousPage: Int?
    var lastPage: String?
}

// MARK: - Result
struct Hotel: Codable {
    
    var sku: String?
    var isHotel: Bool?
    var category: String?
    var smallDescription: String?
    var amenities: [ResultAmenity]?
    var id: String?
    var price: ResultPrice?
    var huFreeCancellation: Bool?
    var image: String?
    var name: String?
    var url: String?
    var resultDescription: String?
    var stars: Int?
    var gallery: [Gallery]?
    var address: Address?
    var tags: [String]?
    var quantityDescriptors: QuantityDescriptors?
    var featuredItem: FeaturedItem?
    var isPackage: Bool?
    var startDate, endDate: String?
    var hasAvailability: Bool?
    
    init(name: String,
         price: ResultPrice?,
         address: Address?,
         image: String?,
         amenities: [ResultAmenity]
    ) {
        self.name = name
        self.price = price
        self.address = address
        self.image = image
        self.amenities = amenities
    }
    
    var formattedAmenities: [String] {
        get {
            
            var returnedAmenities: [String] = []
            
            if let amenities = self.amenities {
                for amenity in amenities.enumerated() {
                    if amenity.offset > 2 {
                        break
                    }
                    returnedAmenities.append(amenity.element.name ?? "")
                }
            }
            
            return returnedAmenities
        }
    }

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
    var zipcode, addressFullAddress, street, addressStreetName: String?
    var streetName, address, fullAddress, city: String?
    var idAtlasCity: Int?
    var idCity: Int?
    var state: String?
    var idAtlasState: Int?
    var idState: Int?
    var country: String?
    var idAtlasCountry: Int?
    var idCountry: Int?
    var countryAlfa2, addressCountryAlfa2: String?
    var geoLocation: GeoLocation?
    var neighborhood: String?
    var idAtlasNeighborhood, idNeighborhood: Int?

    enum CodingKeys: String, CodingKey {
        case zipcode
        case addressFullAddress = "full_address"
        case street
        case addressStreetName = "street_name"
        case streetName, address, fullAddress, city
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
        case geoLocation, neighborhood
        case idAtlasNeighborhood = "id_atlas_neighborhood"
        case idNeighborhood = "id_neighborhood"
    }
}

// MARK: - GeoLocation
struct GeoLocation: Codable {
    var lat, lon: Double?
}

// MARK: - ResultAmenity
struct ResultAmenity: Codable {
    var name, category: String?
    
    init(name: String?, category: String?) {
        self.name = name
        self.category = category
    }
}

// MARK: - FeaturedItem
struct FeaturedItem: Codable {
    var amenities: [String]?
    var name: String?
    var image: String?
    var featuredItemDescription: String?
    var hasInternet, hasParking: Bool?

    enum CodingKeys: String, CodingKey {
        case amenities, name, image
        case featuredItemDescription = "description"
        case hasInternet, hasParking
    }
}

// MARK: - Gallery
struct Gallery: Codable {
    var galleryDescription: String?
    var url: String?
    var roomID: Int?

    enum CodingKeys: String, CodingKey {
        case galleryDescription = "description"
        case url
        case roomID = "room_id"
    }
}

// MARK: - ResultPrice
struct ResultPrice: Codable {
    var amount, priceOldPrice: Double?
    var currency, currencyOriginal: String?
    var gain, feeExtraOriginal, gainOriginal: Int?
    var tariffPolicies: [JSONAny]?
    var priceCurrentPrice, totalPrice: Double?
    var feeExtra: Int?
    var sku: String?
    var taxes: [Tax]?
    var originalAmountPerDay, amountPerDay: Double?
    var oldPrice, currentPrice, originalAmount: Int?
    
    init(amount: Double) {
        self.amount = amount
    }
    
    var formattedAmount: String? {
        get {
            let formatter = NumberFormatter()
            formatter.locale = Locale(identifier: "pt_BR")
            formatter.numberStyle = .currency
            
            return formatter.string(from: NSNumber(value: self.amount ?? 0.0))
        }
    }

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

// MARK: - Tax
struct Tax: Codable {
    var type, name: String?
    var amount, amountOriginal: Double?
    var currency, currencyOriginal: String?

    enum CodingKeys: String, CodingKey {
        case type, name, amount
        case amountOriginal = "amount_original"
        case currency
        case currencyOriginal = "currency_original"
    }
}

// MARK: - QuantityDescriptors
struct QuantityDescriptors: Codable {
    var maxChildren, maxAdults, maxFreeChildrenAge, nights: Int?
    var maxPeople: Int?
}

// MARK: - Encode/decode helpers
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

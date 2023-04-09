// This file was generated from JSON Schema using quicktype, do not modify it directly.
// To parse the JSON, add this file to your project and do:
//
//   let packageItem = try? JSONDecoder().decode(PackageItem.self, from: jsonData)

import Foundation

// MARK: - PackageItem
struct PackageItem: Codable {
    let searchPackage: SearchPackage
}

// MARK: - SearchPackage
struct SearchPackage: Codable {
    let pagination: Pagination
    let typename: String
    let filters: Filters
    let meta: Meta
    let results: [PackageResult]

    enum CodingKeys: String, CodingKey {
        case pagination
        case typename = "__typename"
        case filters, meta, results
    }
}

// MARK: - Filters
struct Filters: Codable {
    let cities, departureCities, states: [City]
    let prices: [PriceElement]
    let people, countries: [City]
    let priceInterval: PriceInterval
    let duration: [City]
    let typename: String
    let food: [City]

    enum CodingKeys: String, CodingKey {
        case cities, departureCities, states, prices, people, countries, priceInterval, duration
        case typename = "__typename"
        case food
    }
}

// MARK: - City
struct City: Codable {
    let label, filter: String
    let typename: Typename
    let count: Int

    enum CodingKeys: String, CodingKey {
        case label, filter
        case typename = "__typename"
        case count
    }
}

enum Typename: String, Codable {
    case searchTermFilter = "SearchTermFilter"
}

// MARK: - PriceInterval
struct PriceInterval: Codable {
    let max: Int
    let min: Double
    let typename: String

    enum CodingKeys: String, CodingKey {
        case max, min
        case typename = "__typename"
    }
}

// MARK: - PriceElement
struct PriceElement: Codable {
    let typename: String
    let maxExclusive, count, min: Int
    let filter: String

    enum CodingKeys: String, CodingKey {
        case typename = "__typename"
        case maxExclusive, count, min, filter
    }
}

// MARK: - Meta
struct Meta: Codable {
    let countPackage: JSONNull?
    let typename: String
    let countWithAvailability: JSONNull?
    let count: Int
    let countHotel, countTicket: JSONNull?

    enum CodingKeys: String, CodingKey {
        case countPackage
        case typename = "__typename"
        case countWithAvailability, count, countHotel, countTicket
    }
}

// MARK: - Pagination
struct Pagination: Codable {
    let typename: String
    let hasPrevious: Bool
    let count, current: Int
    let hasNext: Bool

    enum CodingKeys: String, CodingKey {
        case typename = "__typename"
        case hasPrevious, count, current, hasNext
    }
}

// MARK: - Result
struct PackageResult: Codable {
    let description, category, id: String
    let isAvailable: Bool
    let gallery: [Gallery]
    let url: String
    let tags: [Tag]
    let sku, endDate: String
    let price: ResultPrice
    let quantityDescriptors: QuantityDescriptors
    let address: Address
    let startDate, typename: String
    let amenities: [Amenity]
    let name, smallDescription: String

    enum CodingKeys: String, CodingKey {
        case description, category, id, isAvailable, gallery, url, tags, sku, endDate, price, quantityDescriptors, address, startDate
        case typename = "__typename"
        case amenities, name, smallDescription
    }
}

// MARK: - Address
struct Address: Codable {
    let typename, state: String
    let geoLocation: GeoLocation
    let city, country: String

    enum CodingKeys: String, CodingKey {
        case typename = "__typename"
        case state, geoLocation, city, country
    }
}

// MARK: - GeoLocation
struct GeoLocation: Codable {
    let lon, lat: Double
    let typename: String

    enum CodingKeys: String, CodingKey {
        case lon, lat
        case typename = "__typename"
    }
}

// MARK: - Amenity
struct Amenity: Codable {
    let name, category, typename: String

    enum CodingKeys: String, CodingKey {
        case name, category
        case typename = "__typename"
    }
}

// MARK: - Gallery
struct Gallery: Codable {
    let typename: String
    let url: String
    let description: JSONNull?

    enum CodingKeys: String, CodingKey {
        case typename = "__typename"
        case url, description
    }
}

// MARK: - ResultPrice
struct ResultPrice: Codable {
    let amount: Int
    let currency, typename: String
    let originalAmount: Int

    enum CodingKeys: String, CodingKey {
        case amount, currency
        case typename = "__typename"
        case originalAmount
    }
}

// MARK: - QuantityDescriptors
struct QuantityDescriptors: Codable {
    let maxPeople: Int
    let typename: String
    let maxFreeChildrenAge: JSONNull?
    let nights, duration: Int

    enum CodingKeys: String, CodingKey {
        case maxPeople
        case typename = "__typename"
        case maxFreeChildrenAge, nights, duration
    }
}

// MARK: - Tag
struct Tag: Codable {
    let label, slug, typename: String

    enum CodingKeys: String, CodingKey {
        case label, slug
        case typename = "__typename"
    }
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

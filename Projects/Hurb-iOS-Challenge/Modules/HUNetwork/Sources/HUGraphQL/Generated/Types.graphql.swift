// @generated
//  This file was automatically generated and should not be edited.

import Apollo
import Foundation

/// HUGraphQL namespace
public enum HUGraphQL {
  /// Represents input data for field pagination.
  public struct SearchInputPagination: GraphQLMapConvertible {
    public var graphQLMap: GraphQLMap

    /// - Parameters:
    ///   - page: Current search page.
    ///   - limit: Limit for search results.
    ///   - sort: Sorts results.
    ///   - sortOrder: Sorts by order.
    public init(page: Swift.Optional<HUGScalarPositiveInt?> = nil, limit: Swift.Optional<HUGScalarPositiveInt?> = nil, sort: Swift.Optional<SearchInputSort?> = nil, sortOrder: Swift.Optional<SearchInputSortOrder?> = nil) {
      graphQLMap = ["page": page, "limit": limit, "sort": sort, "sortOrder": sortOrder]
    }

    /// Current search page.
    public var page: Swift.Optional<HUGScalarPositiveInt?> {
      get {
        return graphQLMap["page"] as? Swift.Optional<HUGScalarPositiveInt?> ?? Swift.Optional<HUGScalarPositiveInt?>.none
      }
      set {
        graphQLMap.updateValue(newValue, forKey: "page")
      }
    }

    /// Limit for search results.
    public var limit: Swift.Optional<HUGScalarPositiveInt?> {
      get {
        return graphQLMap["limit"] as? Swift.Optional<HUGScalarPositiveInt?> ?? Swift.Optional<HUGScalarPositiveInt?>.none
      }
      set {
        graphQLMap.updateValue(newValue, forKey: "limit")
      }
    }

    /// Sorts results.
    public var sort: Swift.Optional<SearchInputSort?> {
      get {
        return graphQLMap["sort"] as? Swift.Optional<SearchInputSort?> ?? Swift.Optional<SearchInputSort?>.none
      }
      set {
        graphQLMap.updateValue(newValue, forKey: "sort")
      }
    }

    /// Sorts by order.
    public var sortOrder: Swift.Optional<SearchInputSortOrder?> {
      get {
        return graphQLMap["sortOrder"] as? Swift.Optional<SearchInputSortOrder?> ?? Swift.Optional<SearchInputSortOrder?>.none
      }
      set {
        graphQLMap.updateValue(newValue, forKey: "sortOrder")
      }
    }
  }

  /// Sort types.
  public enum SearchInputSort: RawRepresentable, Equatable, Hashable, CaseIterable, Apollo.JSONDecodable, Apollo.JSONEncodable {
    public typealias RawValue = String
    case score
    case price
    /// Auto generated constant for unknown enum values
    case __unknown(RawValue)

    public init?(rawValue: RawValue) {
      switch rawValue {
        case "SCORE": self = .score
        case "PRICE": self = .price
        default: self = .__unknown(rawValue)
      }
    }

    public var rawValue: RawValue {
      switch self {
        case .score: return "SCORE"
        case .price: return "PRICE"
        case .__unknown(let value): return value
      }
    }

    public static func == (lhs: SearchInputSort, rhs: SearchInputSort) -> Bool {
      switch (lhs, rhs) {
        case (.score, .score): return true
        case (.price, .price): return true
        case (.__unknown(let lhsValue), .__unknown(let rhsValue)): return lhsValue == rhsValue
        default: return false
      }
    }

    public static var allCases: [SearchInputSort] {
      return [
        .score,
        .price,
      ]
    }
  }

  /// Order types.
  public enum SearchInputSortOrder: RawRepresentable, Equatable, Hashable, CaseIterable, Apollo.JSONDecodable, Apollo.JSONEncodable {
    public typealias RawValue = String
    case asc
    case desc
    /// Auto generated constant for unknown enum values
    case __unknown(RawValue)

    public init?(rawValue: RawValue) {
      switch rawValue {
        case "ASC": self = .asc
        case "DESC": self = .desc
        default: self = .__unknown(rawValue)
      }
    }

    public var rawValue: RawValue {
      switch self {
        case .asc: return "ASC"
        case .desc: return "DESC"
        case .__unknown(let value): return value
      }
    }

    public static func == (lhs: SearchInputSortOrder, rhs: SearchInputSortOrder) -> Bool {
      switch (lhs, rhs) {
        case (.asc, .asc): return true
        case (.desc, .desc): return true
        case (.__unknown(let lhsValue), .__unknown(let rhsValue)): return lhsValue == rhsValue
        default: return false
      }
    }

    public static var allCases: [SearchInputSortOrder] {
      return [
        .asc,
        .desc,
      ]
    }
  }

  /// Represents input data for field filters.
  public struct SearchHotelInputFilters: GraphQLMapConvertible {
    public var graphQLMap: GraphQLMap

    /// - Parameters:
    ///   - cities: Cities filter.
    ///   - states: States filter.
    ///   - countries: Countries filter.
    ///   - food: Food filter.
    ///   - people: People filter.
    ///   - priceInterval: Price interval filter.
    ///   - prices: Price filter.
    ///   - stars: Star filter.
    ///   - sku: Product SKU filter
    ///   - amenities: Product amenity filter
    public init(cities: Swift.Optional<SearchInputTermFilter?> = nil, states: Swift.Optional<SearchInputTermFilter?> = nil, countries: Swift.Optional<SearchInputTermFilter?> = nil, food: Swift.Optional<SearchInputTermFilter?> = nil, people: Swift.Optional<SearchInputTermFilter?> = nil, priceInterval: Swift.Optional<SearchInputIntervalFilter?> = nil, prices: Swift.Optional<SearchInputTermFilter?> = nil, stars: Swift.Optional<SearchInputTermFilter?> = nil, sku: Swift.Optional<SearchInputTermFilter?> = nil, amenities: Swift.Optional<SearchInputTermFilter?> = nil) {
      graphQLMap = ["cities": cities, "states": states, "countries": countries, "food": food, "people": people, "priceInterval": priceInterval, "prices": prices, "stars": stars, "sku": sku, "amenities": amenities]
    }

    /// Cities filter.
    public var cities: Swift.Optional<SearchInputTermFilter?> {
      get {
        return graphQLMap["cities"] as? Swift.Optional<SearchInputTermFilter?> ?? Swift.Optional<SearchInputTermFilter?>.none
      }
      set {
        graphQLMap.updateValue(newValue, forKey: "cities")
      }
    }

    /// States filter.
    public var states: Swift.Optional<SearchInputTermFilter?> {
      get {
        return graphQLMap["states"] as? Swift.Optional<SearchInputTermFilter?> ?? Swift.Optional<SearchInputTermFilter?>.none
      }
      set {
        graphQLMap.updateValue(newValue, forKey: "states")
      }
    }

    /// Countries filter.
    public var countries: Swift.Optional<SearchInputTermFilter?> {
      get {
        return graphQLMap["countries"] as? Swift.Optional<SearchInputTermFilter?> ?? Swift.Optional<SearchInputTermFilter?>.none
      }
      set {
        graphQLMap.updateValue(newValue, forKey: "countries")
      }
    }

    /// Food filter.
    public var food: Swift.Optional<SearchInputTermFilter?> {
      get {
        return graphQLMap["food"] as? Swift.Optional<SearchInputTermFilter?> ?? Swift.Optional<SearchInputTermFilter?>.none
      }
      set {
        graphQLMap.updateValue(newValue, forKey: "food")
      }
    }

    /// People filter.
    public var people: Swift.Optional<SearchInputTermFilter?> {
      get {
        return graphQLMap["people"] as? Swift.Optional<SearchInputTermFilter?> ?? Swift.Optional<SearchInputTermFilter?>.none
      }
      set {
        graphQLMap.updateValue(newValue, forKey: "people")
      }
    }

    /// Price interval filter.
    public var priceInterval: Swift.Optional<SearchInputIntervalFilter?> {
      get {
        return graphQLMap["priceInterval"] as? Swift.Optional<SearchInputIntervalFilter?> ?? Swift.Optional<SearchInputIntervalFilter?>.none
      }
      set {
        graphQLMap.updateValue(newValue, forKey: "priceInterval")
      }
    }

    /// Price filter.
    public var prices: Swift.Optional<SearchInputTermFilter?> {
      get {
        return graphQLMap["prices"] as? Swift.Optional<SearchInputTermFilter?> ?? Swift.Optional<SearchInputTermFilter?>.none
      }
      set {
        graphQLMap.updateValue(newValue, forKey: "prices")
      }
    }

    /// Star filter.
    public var stars: Swift.Optional<SearchInputTermFilter?> {
      get {
        return graphQLMap["stars"] as? Swift.Optional<SearchInputTermFilter?> ?? Swift.Optional<SearchInputTermFilter?>.none
      }
      set {
        graphQLMap.updateValue(newValue, forKey: "stars")
      }
    }

    /// Product SKU filter
    public var sku: Swift.Optional<SearchInputTermFilter?> {
      get {
        return graphQLMap["sku"] as? Swift.Optional<SearchInputTermFilter?> ?? Swift.Optional<SearchInputTermFilter?>.none
      }
      set {
        graphQLMap.updateValue(newValue, forKey: "sku")
      }
    }

    /// Product amenity filter
    public var amenities: Swift.Optional<SearchInputTermFilter?> {
      get {
        return graphQLMap["amenities"] as? Swift.Optional<SearchInputTermFilter?> ?? Swift.Optional<SearchInputTermFilter?>.none
      }
      set {
        graphQLMap.updateValue(newValue, forKey: "amenities")
      }
    }
  }

  /// Represents input data for field filters.
  public struct SearchInputTermFilter: GraphQLMapConvertible {
    public var graphQLMap: GraphQLMap

    /// - Parameters:
    ///   - values: Filter values.
    ///   - operator: Filter operator.
    public init(values: [String?], `operator`: SearchFilterOperator) {
      graphQLMap = ["values": values, "operator": `operator`]
    }

    /// Filter values.
    public var values: [String?] {
      get {
        return graphQLMap["values"] as! [String?]
      }
      set {
        graphQLMap.updateValue(newValue, forKey: "values")
      }
    }

    /// Filter operator.
    public var `operator`: SearchFilterOperator {
      get {
        return graphQLMap["operator"] as! SearchFilterOperator
      }
      set {
        graphQLMap.updateValue(newValue, forKey: "operator")
      }
    }
  }

  /// Filter Operator.
  public enum SearchFilterOperator: RawRepresentable, Equatable, Hashable, CaseIterable, Apollo.JSONDecodable, Apollo.JSONEncodable {
    public typealias RawValue = String
    case or
    case and
    case not
    /// Auto generated constant for unknown enum values
    case __unknown(RawValue)

    public init?(rawValue: RawValue) {
      switch rawValue {
        case "OR": self = .or
        case "AND": self = .and
        case "NOT": self = .not
        default: self = .__unknown(rawValue)
      }
    }

    public var rawValue: RawValue {
      switch self {
        case .or: return "OR"
        case .and: return "AND"
        case .not: return "NOT"
        case .__unknown(let value): return value
      }
    }

    public static func == (lhs: SearchFilterOperator, rhs: SearchFilterOperator) -> Bool {
      switch (lhs, rhs) {
        case (.or, .or): return true
        case (.and, .and): return true
        case (.not, .not): return true
        case (.__unknown(let lhsValue), .__unknown(let rhsValue)): return lhsValue == rhsValue
        default: return false
      }
    }

    public static var allCases: [SearchFilterOperator] {
      return [
        .or,
        .and,
        .not,
      ]
    }
  }

  /// Represents input data for interval filter.
  public struct SearchInputIntervalFilter: GraphQLMapConvertible {
    public var graphQLMap: GraphQLMap

    /// - Parameters:
    ///   - min: Min value.
    ///   - max: Max value.
    public init(min: Double, max: Double) {
      graphQLMap = ["min": min, "max": max]
    }

    /// Min value.
    public var min: Double {
      get {
        return graphQLMap["min"] as! Double
      }
      set {
        graphQLMap.updateValue(newValue, forKey: "min")
      }
    }

    /// Max value.
    public var max: Double {
      get {
        return graphQLMap["max"] as! Double
      }
      set {
        graphQLMap.updateValue(newValue, forKey: "max")
      }
    }
  }

  /// Localization information.
  public struct InputL10n: GraphQLMapConvertible {
    public var graphQLMap: GraphQLMap

    /// - Parameters:
    ///   - pos: Point of sale.
    ///   - locale: Localization.
    ///   - currency: Currency code.
    public init(pos: HUGScalarPosID, locale: Swift.Optional<HUGScalarLocale?> = nil, currency: Swift.Optional<String?> = nil) {
      graphQLMap = ["pos": pos, "locale": locale, "currency": currency]
    }

    /// Point of sale.
    public var pos: HUGScalarPosID {
      get {
        return graphQLMap["pos"] as! HUGScalarPosID
      }
      set {
        graphQLMap.updateValue(newValue, forKey: "pos")
      }
    }

    /// Localization.
    public var locale: Swift.Optional<HUGScalarLocale?> {
      get {
        return graphQLMap["locale"] as? Swift.Optional<HUGScalarLocale?> ?? Swift.Optional<HUGScalarLocale?>.none
      }
      set {
        graphQLMap.updateValue(newValue, forKey: "locale")
      }
    }

    /// Currency code.
    public var currency: Swift.Optional<String?> {
      get {
        return graphQLMap["currency"] as? Swift.Optional<String?> ?? Swift.Optional<String?>.none
      }
      set {
        graphQLMap.updateValue(newValue, forKey: "currency")
      }
    }
  }

  /// Room information.
  public struct SearchInputRooms: GraphQLMapConvertible {
    public var graphQLMap: GraphQLMap

    /// - Parameters:
    ///   - adults: Number of adults per room.
    ///   - children: List of children ages.
    public init(adults: HUGScalarPositiveInt, children: [HUGScalarNonNegativeInt]) {
      graphQLMap = ["adults": adults, "children": children]
    }

    /// Number of adults per room.
    public var adults: HUGScalarPositiveInt {
      get {
        return graphQLMap["adults"] as! HUGScalarPositiveInt
      }
      set {
        graphQLMap.updateValue(newValue, forKey: "adults")
      }
    }

    /// List of children ages.
    public var children: [HUGScalarNonNegativeInt] {
      get {
        return graphQLMap["children"] as! [HUGScalarNonNegativeInt]
      }
      set {
        graphQLMap.updateValue(newValue, forKey: "children")
      }
    }
  }

  /// Represents input data for field filters.
  public struct SearchPackageInputFilters: GraphQLMapConvertible {
    public var graphQLMap: GraphQLMap

    /// - Parameters:
    ///   - cities: Cities filter.
    ///   - states: States filter.
    ///   - countries: Countries filter.
    ///   - departureCities: Departure cities filter.
    ///   - duration: Duration filter.
    ///   - food: Food filter.
    ///   - people: People filter.
    ///   - priceInterval: Price interval filter.
    ///   - prices: Price filter.
    ///   - sku: Product SKU filter
    public init(cities: Swift.Optional<SearchInputTermFilter?> = nil, states: Swift.Optional<SearchInputTermFilter?> = nil, countries: Swift.Optional<SearchInputTermFilter?> = nil, departureCities: Swift.Optional<SearchInputTermFilter?> = nil, duration: Swift.Optional<SearchInputTermFilter?> = nil, food: Swift.Optional<SearchInputTermFilter?> = nil, people: Swift.Optional<SearchInputTermFilter?> = nil, priceInterval: Swift.Optional<SearchInputIntervalFilter?> = nil, prices: Swift.Optional<SearchInputTermFilter?> = nil, sku: Swift.Optional<SearchInputTermFilter?> = nil) {
      graphQLMap = ["cities": cities, "states": states, "countries": countries, "departureCities": departureCities, "duration": duration, "food": food, "people": people, "priceInterval": priceInterval, "prices": prices, "sku": sku]
    }

    /// Cities filter.
    public var cities: Swift.Optional<SearchInputTermFilter?> {
      get {
        return graphQLMap["cities"] as? Swift.Optional<SearchInputTermFilter?> ?? Swift.Optional<SearchInputTermFilter?>.none
      }
      set {
        graphQLMap.updateValue(newValue, forKey: "cities")
      }
    }

    /// States filter.
    public var states: Swift.Optional<SearchInputTermFilter?> {
      get {
        return graphQLMap["states"] as? Swift.Optional<SearchInputTermFilter?> ?? Swift.Optional<SearchInputTermFilter?>.none
      }
      set {
        graphQLMap.updateValue(newValue, forKey: "states")
      }
    }

    /// Countries filter.
    public var countries: Swift.Optional<SearchInputTermFilter?> {
      get {
        return graphQLMap["countries"] as? Swift.Optional<SearchInputTermFilter?> ?? Swift.Optional<SearchInputTermFilter?>.none
      }
      set {
        graphQLMap.updateValue(newValue, forKey: "countries")
      }
    }

    /// Departure cities filter.
    public var departureCities: Swift.Optional<SearchInputTermFilter?> {
      get {
        return graphQLMap["departureCities"] as? Swift.Optional<SearchInputTermFilter?> ?? Swift.Optional<SearchInputTermFilter?>.none
      }
      set {
        graphQLMap.updateValue(newValue, forKey: "departureCities")
      }
    }

    /// Duration filter.
    public var duration: Swift.Optional<SearchInputTermFilter?> {
      get {
        return graphQLMap["duration"] as? Swift.Optional<SearchInputTermFilter?> ?? Swift.Optional<SearchInputTermFilter?>.none
      }
      set {
        graphQLMap.updateValue(newValue, forKey: "duration")
      }
    }

    /// Food filter.
    public var food: Swift.Optional<SearchInputTermFilter?> {
      get {
        return graphQLMap["food"] as? Swift.Optional<SearchInputTermFilter?> ?? Swift.Optional<SearchInputTermFilter?>.none
      }
      set {
        graphQLMap.updateValue(newValue, forKey: "food")
      }
    }

    /// People filter.
    public var people: Swift.Optional<SearchInputTermFilter?> {
      get {
        return graphQLMap["people"] as? Swift.Optional<SearchInputTermFilter?> ?? Swift.Optional<SearchInputTermFilter?>.none
      }
      set {
        graphQLMap.updateValue(newValue, forKey: "people")
      }
    }

    /// Price interval filter.
    public var priceInterval: Swift.Optional<SearchInputIntervalFilter?> {
      get {
        return graphQLMap["priceInterval"] as? Swift.Optional<SearchInputIntervalFilter?> ?? Swift.Optional<SearchInputIntervalFilter?>.none
      }
      set {
        graphQLMap.updateValue(newValue, forKey: "priceInterval")
      }
    }

    /// Price filter.
    public var prices: Swift.Optional<SearchInputTermFilter?> {
      get {
        return graphQLMap["prices"] as? Swift.Optional<SearchInputTermFilter?> ?? Swift.Optional<SearchInputTermFilter?>.none
      }
      set {
        graphQLMap.updateValue(newValue, forKey: "prices")
      }
    }

    /// Product SKU filter
    public var sku: Swift.Optional<SearchInputTermFilter?> {
      get {
        return graphQLMap["sku"] as? Swift.Optional<SearchInputTermFilter?> ?? Swift.Optional<SearchInputTermFilter?>.none
      }
      set {
        graphQLMap.updateValue(newValue, forKey: "sku")
      }
    }
  }

  /// Suggestion product types.
  public enum SuggestionProductType: RawRepresentable, Equatable, Hashable, CaseIterable, Apollo.JSONDecodable, Apollo.JSONEncodable {
    public typealias RawValue = String
    case hotel
    case package
    case ticket
    /// Auto generated constant for unknown enum values
    case __unknown(RawValue)

    public init?(rawValue: RawValue) {
      switch rawValue {
        case "HOTEL": self = .hotel
        case "PACKAGE": self = .package
        case "TICKET": self = .ticket
        default: self = .__unknown(rawValue)
      }
    }

    public var rawValue: RawValue {
      switch self {
        case .hotel: return "HOTEL"
        case .package: return "PACKAGE"
        case .ticket: return "TICKET"
        case .__unknown(let value): return value
      }
    }

    public static func == (lhs: SuggestionProductType, rhs: SuggestionProductType) -> Bool {
      switch (lhs, rhs) {
        case (.hotel, .hotel): return true
        case (.package, .package): return true
        case (.ticket, .ticket): return true
        case (.__unknown(let lhsValue), .__unknown(let rhsValue)): return lhsValue == rhsValue
        default: return false
      }
    }

    public static var allCases: [SuggestionProductType] {
      return [
        .hotel,
        .package,
        .ticket,
      ]
    }
  }
}

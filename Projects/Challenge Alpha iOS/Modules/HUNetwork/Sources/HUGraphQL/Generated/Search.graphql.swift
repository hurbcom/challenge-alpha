// @generated
//  This file was automatically generated and should not be edited.

import Apollo
import Foundation

/// HUGraphQL namespace
public extension HUGraphQL {
  final class SearchQuery: GraphQLQuery {
    /// The raw GraphQL definition of this operation.
    public let operationDefinition: String =
      """
      query search($q: String!, $pagination: SearchInputPagination) {
        search(q: $q, pagination: $pagination) {
          __typename
          pagination {
            __typename
            count
            current
            hasNext
          }
          filters {
            __typename
            productType {
              __typename
              label
              filter
              count
            }
            cities {
              __typename
              label
              filter
              count
            }
            stars {
              __typename
              label
              filter
              count
            }
            prices {
              __typename
              min
              maxExclusive
              filter
              count
            }
          }
          meta {
            __typename
            count
            countWithAvailability
            countHotel
            countTicket
            countPackage
          }
          results {
            __typename
            id
            sku
            name
            url
            category
            description
            smallDescription
            isAvailable
            price {
              __typename
              currency
              amount
              originalAmount
              taxes {
                __typename
                originalAmount
                originalCurrency
              }
            }
            address {
              __typename
              city
              state
              country
              geoLocation {
                __typename
                lat
                lon
              }
            }
            tags {
              __typename
              label
              slug
            }
            gallery(limit: 10) {
              __typename
              url(quality: HIGH, resolution: ORIGINAL)
              description
            }
            amenities(limit: 10) {
              __typename
              name
              category
            }
          }
        }
      }
      """

    public let operationName: String = "search"

    public var q: String
    public var pagination: SearchInputPagination?

    public init(q: String, pagination: SearchInputPagination? = nil) {
      self.q = q
      self.pagination = pagination
    }

    public var variables: GraphQLMap? {
      return ["q": q, "pagination": pagination]
    }

    public struct Data: GraphQLSelectionSet {
      public static let possibleTypes: [String] = ["Query"]

      public static var selections: [GraphQLSelection] {
        return [
          GraphQLField("search", arguments: ["q": GraphQLVariable("q"), "pagination": GraphQLVariable("pagination")], type: .object(Search.selections)),
        ]
      }

      public private(set) var resultMap: ResultMap

      public init(unsafeResultMap: ResultMap) {
        self.resultMap = unsafeResultMap
      }

      public init(search: Search? = nil) {
        self.init(unsafeResultMap: ["__typename": "Query", "search": search.flatMap { (value: Search) -> ResultMap in value.resultMap }])
      }

      /// Fetches products in Search API.
      public var search: Search? {
        get {
          return (resultMap["search"] as? ResultMap).flatMap { Search(unsafeResultMap: $0) }
        }
        set {
          resultMap.updateValue(newValue?.resultMap, forKey: "search")
        }
      }

      public struct Search: GraphQLSelectionSet {
        public static let possibleTypes: [String] = ["Search"]

        public static var selections: [GraphQLSelection] {
          return [
            GraphQLField("__typename", type: .nonNull(.scalar(String.self))),
            GraphQLField("pagination", type: .object(Pagination.selections)),
            GraphQLField("filters", type: .object(Filter.selections)),
            GraphQLField("meta", type: .nonNull(.object(Metum.selections))),
            GraphQLField("results", type: .list(.nonNull(.object(Result.selections)))),
          ]
        }

        public private(set) var resultMap: ResultMap

        public init(unsafeResultMap: ResultMap) {
          self.resultMap = unsafeResultMap
        }

        public init(pagination: Pagination? = nil, filters: Filter? = nil, meta: Metum, results: [Result]? = nil) {
          self.init(unsafeResultMap: ["__typename": "Search", "pagination": pagination.flatMap { (value: Pagination) -> ResultMap in value.resultMap }, "filters": filters.flatMap { (value: Filter) -> ResultMap in value.resultMap }, "meta": meta.resultMap, "results": results.flatMap { (value: [Result]) -> [ResultMap] in value.map { (value: Result) -> ResultMap in value.resultMap } }])
        }

        public var __typename: String {
          get {
            return resultMap["__typename"]! as! String
          }
          set {
            resultMap.updateValue(newValue, forKey: "__typename")
          }
        }

        /// Necessary info to construct a pagination.
        public var pagination: Pagination? {
          get {
            return (resultMap["pagination"] as? ResultMap).flatMap { Pagination(unsafeResultMap: $0) }
          }
          set {
            resultMap.updateValue(newValue?.resultMap, forKey: "pagination")
          }
        }

        /// Filters available.
        public var filters: Filter? {
          get {
            return (resultMap["filters"] as? ResultMap).flatMap { Filter(unsafeResultMap: $0) }
          }
          set {
            resultMap.updateValue(newValue?.resultMap, forKey: "filters")
          }
        }

        /// Meta-information from search results.
        public var meta: Metum {
          get {
            return Metum(unsafeResultMap: resultMap["meta"]! as! ResultMap)
          }
          set {
            resultMap.updateValue(newValue.resultMap, forKey: "meta")
          }
        }

        /// Results found.
        public var results: [Result]? {
          get {
            return (resultMap["results"] as? [ResultMap]).flatMap { (value: [ResultMap]) -> [Result] in value.map { (value: ResultMap) -> Result in Result(unsafeResultMap: value) } }
          }
          set {
            resultMap.updateValue(newValue.flatMap { (value: [Result]) -> [ResultMap] in value.map { (value: Result) -> ResultMap in value.resultMap } }, forKey: "results")
          }
        }

        public struct Pagination: GraphQLSelectionSet {
          public static let possibleTypes: [String] = ["SearchPagination"]

          public static var selections: [GraphQLSelection] {
            return [
              GraphQLField("__typename", type: .nonNull(.scalar(String.self))),
              GraphQLField("count", type: .nonNull(.scalar(HUGScalarNonNegativeInt.self))),
              GraphQLField("current", type: .nonNull(.scalar(HUGScalarNonNegativeInt.self))),
              GraphQLField("hasNext", type: .nonNull(.scalar(Bool.self))),
            ]
          }

          public private(set) var resultMap: ResultMap

          public init(unsafeResultMap: ResultMap) {
            self.resultMap = unsafeResultMap
          }

          public init(count: HUGScalarNonNegativeInt, current: HUGScalarNonNegativeInt, hasNext: Bool) {
            self.init(unsafeResultMap: ["__typename": "SearchPagination", "count": count, "current": current, "hasNext": hasNext])
          }

          public var __typename: String {
            get {
              return resultMap["__typename"]! as! String
            }
            set {
              resultMap.updateValue(newValue, forKey: "__typename")
            }
          }

          /// Number of pages.
          public var count: HUGScalarNonNegativeInt {
            get {
              return resultMap["count"]! as! HUGScalarNonNegativeInt
            }
            set {
              resultMap.updateValue(newValue, forKey: "count")
            }
          }

          /// Inform current page.
          public var current: HUGScalarNonNegativeInt {
            get {
              return resultMap["current"]! as! HUGScalarNonNegativeInt
            }
            set {
              resultMap.updateValue(newValue, forKey: "current")
            }
          }

          /// Inform if exists a next page.
          public var hasNext: Bool {
            get {
              return resultMap["hasNext"]! as! Bool
            }
            set {
              resultMap.updateValue(newValue, forKey: "hasNext")
            }
          }
        }

        public struct Filter: GraphQLSelectionSet {
          public static let possibleTypes: [String] = ["SearchFilters"]

          public static var selections: [GraphQLSelection] {
            return [
              GraphQLField("__typename", type: .nonNull(.scalar(String.self))),
              GraphQLField("productType", type: .list(.object(ProductType.selections))),
              GraphQLField("cities", type: .list(.object(City.selections))),
              GraphQLField("stars", type: .list(.object(Star.selections))),
              GraphQLField("prices", type: .list(.object(Price.selections))),
            ]
          }

          public private(set) var resultMap: ResultMap

          public init(unsafeResultMap: ResultMap) {
            self.resultMap = unsafeResultMap
          }

          public init(productType: [ProductType?]? = nil, cities: [City?]? = nil, stars: [Star?]? = nil, prices: [Price?]? = nil) {
            self.init(unsafeResultMap: ["__typename": "SearchFilters", "productType": productType.flatMap { (value: [ProductType?]) -> [ResultMap?] in value.map { (value: ProductType?) -> ResultMap? in value.flatMap { (value: ProductType) -> ResultMap in value.resultMap } } }, "cities": cities.flatMap { (value: [City?]) -> [ResultMap?] in value.map { (value: City?) -> ResultMap? in value.flatMap { (value: City) -> ResultMap in value.resultMap } } }, "stars": stars.flatMap { (value: [Star?]) -> [ResultMap?] in value.map { (value: Star?) -> ResultMap? in value.flatMap { (value: Star) -> ResultMap in value.resultMap } } }, "prices": prices.flatMap { (value: [Price?]) -> [ResultMap?] in value.map { (value: Price?) -> ResultMap? in value.flatMap { (value: Price) -> ResultMap in value.resultMap } } }])
          }

          public var __typename: String {
            get {
              return resultMap["__typename"]! as! String
            }
            set {
              resultMap.updateValue(newValue, forKey: "__typename")
            }
          }

          /// Product type filter.
          public var productType: [ProductType?]? {
            get {
              return (resultMap["productType"] as? [ResultMap?]).flatMap { (value: [ResultMap?]) -> [ProductType?] in value.map { (value: ResultMap?) -> ProductType? in value.flatMap { (value: ResultMap) -> ProductType in ProductType(unsafeResultMap: value) } } }
            }
            set {
              resultMap.updateValue(newValue.flatMap { (value: [ProductType?]) -> [ResultMap?] in value.map { (value: ProductType?) -> ResultMap? in value.flatMap { (value: ProductType) -> ResultMap in value.resultMap } } }, forKey: "productType")
            }
          }

          /// Cities filter.
          public var cities: [City?]? {
            get {
              return (resultMap["cities"] as? [ResultMap?]).flatMap { (value: [ResultMap?]) -> [City?] in value.map { (value: ResultMap?) -> City? in value.flatMap { (value: ResultMap) -> City in City(unsafeResultMap: value) } } }
            }
            set {
              resultMap.updateValue(newValue.flatMap { (value: [City?]) -> [ResultMap?] in value.map { (value: City?) -> ResultMap? in value.flatMap { (value: City) -> ResultMap in value.resultMap } } }, forKey: "cities")
            }
          }

          /// Star filter.
          public var stars: [Star?]? {
            get {
              return (resultMap["stars"] as? [ResultMap?]).flatMap { (value: [ResultMap?]) -> [Star?] in value.map { (value: ResultMap?) -> Star? in value.flatMap { (value: ResultMap) -> Star in Star(unsafeResultMap: value) } } }
            }
            set {
              resultMap.updateValue(newValue.flatMap { (value: [Star?]) -> [ResultMap?] in value.map { (value: Star?) -> ResultMap? in value.flatMap { (value: Star) -> ResultMap in value.resultMap } } }, forKey: "stars")
            }
          }

          /// Price filter.
          public var prices: [Price?]? {
            get {
              return (resultMap["prices"] as? [ResultMap?]).flatMap { (value: [ResultMap?]) -> [Price?] in value.map { (value: ResultMap?) -> Price? in value.flatMap { (value: ResultMap) -> Price in Price(unsafeResultMap: value) } } }
            }
            set {
              resultMap.updateValue(newValue.flatMap { (value: [Price?]) -> [ResultMap?] in value.map { (value: Price?) -> ResultMap? in value.flatMap { (value: Price) -> ResultMap in value.resultMap } } }, forKey: "prices")
            }
          }

          public struct ProductType: GraphQLSelectionSet {
            public static let possibleTypes: [String] = ["SearchTermFilter"]

            public static var selections: [GraphQLSelection] {
              return [
                GraphQLField("__typename", type: .nonNull(.scalar(String.self))),
                GraphQLField("label", type: .nonNull(.scalar(String.self))),
                GraphQLField("filter", type: .nonNull(.scalar(String.self))),
                GraphQLField("count", type: .nonNull(.scalar(Int.self))),
              ]
            }

            public private(set) var resultMap: ResultMap

            public init(unsafeResultMap: ResultMap) {
              self.resultMap = unsafeResultMap
            }

            public init(label: String, filter: String, count: Int) {
              self.init(unsafeResultMap: ["__typename": "SearchTermFilter", "label": label, "filter": filter, "count": count])
            }

            public var __typename: String {
              get {
                return resultMap["__typename"]! as! String
              }
              set {
                resultMap.updateValue(newValue, forKey: "__typename")
              }
            }

            /// Filter label.
            public var label: String {
              get {
                return resultMap["label"]! as! String
              }
              set {
                resultMap.updateValue(newValue, forKey: "label")
              }
            }

            /// The term used to filter.
            public var filter: String {
              get {
                return resultMap["filter"]! as! String
              }
              set {
                resultMap.updateValue(newValue, forKey: "filter")
              }
            }

            /// Total results that contain this term.
            public var count: Int {
              get {
                return resultMap["count"]! as! Int
              }
              set {
                resultMap.updateValue(newValue, forKey: "count")
              }
            }
          }

          public struct City: GraphQLSelectionSet {
            public static let possibleTypes: [String] = ["SearchTermFilter"]

            public static var selections: [GraphQLSelection] {
              return [
                GraphQLField("__typename", type: .nonNull(.scalar(String.self))),
                GraphQLField("label", type: .nonNull(.scalar(String.self))),
                GraphQLField("filter", type: .nonNull(.scalar(String.self))),
                GraphQLField("count", type: .nonNull(.scalar(Int.self))),
              ]
            }

            public private(set) var resultMap: ResultMap

            public init(unsafeResultMap: ResultMap) {
              self.resultMap = unsafeResultMap
            }

            public init(label: String, filter: String, count: Int) {
              self.init(unsafeResultMap: ["__typename": "SearchTermFilter", "label": label, "filter": filter, "count": count])
            }

            public var __typename: String {
              get {
                return resultMap["__typename"]! as! String
              }
              set {
                resultMap.updateValue(newValue, forKey: "__typename")
              }
            }

            /// Filter label.
            public var label: String {
              get {
                return resultMap["label"]! as! String
              }
              set {
                resultMap.updateValue(newValue, forKey: "label")
              }
            }

            /// The term used to filter.
            public var filter: String {
              get {
                return resultMap["filter"]! as! String
              }
              set {
                resultMap.updateValue(newValue, forKey: "filter")
              }
            }

            /// Total results that contain this term.
            public var count: Int {
              get {
                return resultMap["count"]! as! Int
              }
              set {
                resultMap.updateValue(newValue, forKey: "count")
              }
            }
          }

          public struct Star: GraphQLSelectionSet {
            public static let possibleTypes: [String] = ["SearchTermFilter"]

            public static var selections: [GraphQLSelection] {
              return [
                GraphQLField("__typename", type: .nonNull(.scalar(String.self))),
                GraphQLField("label", type: .nonNull(.scalar(String.self))),
                GraphQLField("filter", type: .nonNull(.scalar(String.self))),
                GraphQLField("count", type: .nonNull(.scalar(Int.self))),
              ]
            }

            public private(set) var resultMap: ResultMap

            public init(unsafeResultMap: ResultMap) {
              self.resultMap = unsafeResultMap
            }

            public init(label: String, filter: String, count: Int) {
              self.init(unsafeResultMap: ["__typename": "SearchTermFilter", "label": label, "filter": filter, "count": count])
            }

            public var __typename: String {
              get {
                return resultMap["__typename"]! as! String
              }
              set {
                resultMap.updateValue(newValue, forKey: "__typename")
              }
            }

            /// Filter label.
            public var label: String {
              get {
                return resultMap["label"]! as! String
              }
              set {
                resultMap.updateValue(newValue, forKey: "label")
              }
            }

            /// The term used to filter.
            public var filter: String {
              get {
                return resultMap["filter"]! as! String
              }
              set {
                resultMap.updateValue(newValue, forKey: "filter")
              }
            }

            /// Total results that contain this term.
            public var count: Int {
              get {
                return resultMap["count"]! as! Int
              }
              set {
                resultMap.updateValue(newValue, forKey: "count")
              }
            }
          }

          public struct Price: GraphQLSelectionSet {
            public static let possibleTypes: [String] = ["SearchPriceGroupFilter"]

            public static var selections: [GraphQLSelection] {
              return [
                GraphQLField("__typename", type: .nonNull(.scalar(String.self))),
                GraphQLField("min", type: .nonNull(.scalar(Int.self))),
                GraphQLField("maxExclusive", type: .nonNull(.scalar(Int.self))),
                GraphQLField("filter", type: .nonNull(.scalar(String.self))),
                GraphQLField("count", type: .nonNull(.scalar(Int.self))),
              ]
            }

            public private(set) var resultMap: ResultMap

            public init(unsafeResultMap: ResultMap) {
              self.resultMap = unsafeResultMap
            }

            public init(min: Int, maxExclusive: Int, filter: String, count: Int) {
              self.init(unsafeResultMap: ["__typename": "SearchPriceGroupFilter", "min": min, "maxExclusive": maxExclusive, "filter": filter, "count": count])
            }

            public var __typename: String {
              get {
                return resultMap["__typename"]! as! String
              }
              set {
                resultMap.updateValue(newValue, forKey: "__typename")
              }
            }

            /// Min range value.
            public var min: Int {
              get {
                return resultMap["min"]! as! Int
              }
              set {
                resultMap.updateValue(newValue, forKey: "min")
              }
            }

            /// Max range value (exclusive).
            public var maxExclusive: Int {
              get {
                return resultMap["maxExclusive"]! as! Int
              }
              set {
                resultMap.updateValue(newValue, forKey: "maxExclusive")
              }
            }

            /// The range group represented as a term to filter.
            public var filter: String {
              get {
                return resultMap["filter"]! as! String
              }
              set {
                resultMap.updateValue(newValue, forKey: "filter")
              }
            }

            /// Total results that contain this term.
            public var count: Int {
              get {
                return resultMap["count"]! as! Int
              }
              set {
                resultMap.updateValue(newValue, forKey: "count")
              }
            }
          }
        }

        public struct Metum: GraphQLSelectionSet {
          public static let possibleTypes: [String] = ["SearchMeta"]

          public static var selections: [GraphQLSelection] {
            return [
              GraphQLField("__typename", type: .nonNull(.scalar(String.self))),
              GraphQLField("count", type: .nonNull(.scalar(Int.self))),
              GraphQLField("countWithAvailability", type: .scalar(Int.self)),
              GraphQLField("countHotel", type: .scalar(Int.self)),
              GraphQLField("countTicket", type: .scalar(Int.self)),
              GraphQLField("countPackage", type: .scalar(Int.self)),
            ]
          }

          public private(set) var resultMap: ResultMap

          public init(unsafeResultMap: ResultMap) {
            self.resultMap = unsafeResultMap
          }

          public init(count: Int, countWithAvailability: Int? = nil, countHotel: Int? = nil, countTicket: Int? = nil, countPackage: Int? = nil) {
            self.init(unsafeResultMap: ["__typename": "SearchMeta", "count": count, "countWithAvailability": countWithAvailability, "countHotel": countHotel, "countTicket": countTicket, "countPackage": countPackage])
          }

          public var __typename: String {
            get {
              return resultMap["__typename"]! as! String
            }
            set {
              resultMap.updateValue(newValue, forKey: "__typename")
            }
          }

          /// Quantity of products found.
          public var count: Int {
            get {
              return resultMap["count"]! as! Int
            }
            set {
              resultMap.updateValue(newValue, forKey: "count")
            }
          }

          /// Quantity of products found with availability.
          public var countWithAvailability: Int? {
            get {
              return resultMap["countWithAvailability"] as? Int
            }
            set {
              resultMap.updateValue(newValue, forKey: "countWithAvailability")
            }
          }

          /// Quantity of hotels found.
          public var countHotel: Int? {
            get {
              return resultMap["countHotel"] as? Int
            }
            set {
              resultMap.updateValue(newValue, forKey: "countHotel")
            }
          }

          /// Quantity of tickets found.
          public var countTicket: Int? {
            get {
              return resultMap["countTicket"] as? Int
            }
            set {
              resultMap.updateValue(newValue, forKey: "countTicket")
            }
          }

          /// Quantity of packages found.
          public var countPackage: Int? {
            get {
              return resultMap["countPackage"] as? Int
            }
            set {
              resultMap.updateValue(newValue, forKey: "countPackage")
            }
          }
        }

        public struct Result: GraphQLSelectionSet {
          public static let possibleTypes: [String] = ["SearchResultHotelItem", "SearchResultPackageItem", "SearchResultTicketItem"]

          public static var selections: [GraphQLSelection] {
            return [
              GraphQLField("__typename", type: .nonNull(.scalar(String.self))),
              GraphQLField("id", type: .scalar(GraphQLID.self)),
              GraphQLField("sku", type: .nonNull(.scalar(HUGScalarSKU.self))),
              GraphQLField("name", type: .nonNull(.scalar(String.self))),
              GraphQLField("url", type: .nonNull(.scalar(String.self))),
              GraphQLField("category", type: .nonNull(.scalar(String.self))),
              GraphQLField("description", type: .nonNull(.scalar(String.self))),
              GraphQLField("smallDescription", type: .nonNull(.scalar(String.self))),
              GraphQLField("isAvailable", type: .nonNull(.scalar(Bool.self))),
              GraphQLField("price", type: .nonNull(.object(Price.selections))),
              GraphQLField("address", type: .object(Address.selections)),
              GraphQLField("tags", type: .nonNull(.list(.nonNull(.object(Tag.selections))))),
              GraphQLField("gallery", arguments: ["limit": 10], type: .nonNull(.list(.nonNull(.object(Gallery.selections))))),
              GraphQLField("amenities", arguments: ["limit": 10], type: .nonNull(.list(.nonNull(.object(Amenity.selections))))),
            ]
          }

          public private(set) var resultMap: ResultMap

          public init(unsafeResultMap: ResultMap) {
            self.resultMap = unsafeResultMap
          }

          public static func makeSearchResultHotelItem(id: GraphQLID? = nil, sku: HUGScalarSKU, name: String, url: String, category: String, description: String, smallDescription: String, isAvailable: Bool, price: Price, address: Address? = nil, tags: [Tag], gallery: [Gallery], amenities: [Amenity]) -> Result {
            return Result(unsafeResultMap: ["__typename": "SearchResultHotelItem", "id": id, "sku": sku, "name": name, "url": url, "category": category, "description": description, "smallDescription": smallDescription, "isAvailable": isAvailable, "price": price.resultMap, "address": address.flatMap { (value: Address) -> ResultMap in value.resultMap }, "tags": tags.map { (value: Tag) -> ResultMap in value.resultMap }, "gallery": gallery.map { (value: Gallery) -> ResultMap in value.resultMap }, "amenities": amenities.map { (value: Amenity) -> ResultMap in value.resultMap }])
          }

          public static func makeSearchResultPackageItem(id: GraphQLID? = nil, sku: HUGScalarSKU, name: String, url: String, category: String, description: String, smallDescription: String, isAvailable: Bool, price: Price, address: Address? = nil, tags: [Tag], gallery: [Gallery], amenities: [Amenity]) -> Result {
            return Result(unsafeResultMap: ["__typename": "SearchResultPackageItem", "id": id, "sku": sku, "name": name, "url": url, "category": category, "description": description, "smallDescription": smallDescription, "isAvailable": isAvailable, "price": price.resultMap, "address": address.flatMap { (value: Address) -> ResultMap in value.resultMap }, "tags": tags.map { (value: Tag) -> ResultMap in value.resultMap }, "gallery": gallery.map { (value: Gallery) -> ResultMap in value.resultMap }, "amenities": amenities.map { (value: Amenity) -> ResultMap in value.resultMap }])
          }

          public static func makeSearchResultTicketItem(id: GraphQLID? = nil, sku: HUGScalarSKU, name: String, url: String, category: String, description: String, smallDescription: String, isAvailable: Bool, price: Price, address: Address? = nil, tags: [Tag], gallery: [Gallery], amenities: [Amenity]) -> Result {
            return Result(unsafeResultMap: ["__typename": "SearchResultTicketItem", "id": id, "sku": sku, "name": name, "url": url, "category": category, "description": description, "smallDescription": smallDescription, "isAvailable": isAvailable, "price": price.resultMap, "address": address.flatMap { (value: Address) -> ResultMap in value.resultMap }, "tags": tags.map { (value: Tag) -> ResultMap in value.resultMap }, "gallery": gallery.map { (value: Gallery) -> ResultMap in value.resultMap }, "amenities": amenities.map { (value: Amenity) -> ResultMap in value.resultMap }])
          }

          public var __typename: String {
            get {
              return resultMap["__typename"]! as! String
            }
            set {
              resultMap.updateValue(newValue, forKey: "__typename")
            }
          }

          /// Product id.
          public var id: GraphQLID? {
            get {
              return resultMap["id"] as? GraphQLID
            }
            set {
              resultMap.updateValue(newValue, forKey: "id")
            }
          }

          /// Product sku.
          public var sku: HUGScalarSKU {
            get {
              return resultMap["sku"]! as! HUGScalarSKU
            }
            set {
              resultMap.updateValue(newValue, forKey: "sku")
            }
          }

          /// Product name.
          public var name: String {
            get {
              return resultMap["name"]! as! String
            }
            set {
              resultMap.updateValue(newValue, forKey: "name")
            }
          }

          /// Product url.
          public var url: String {
            get {
              return resultMap["url"]! as! String
            }
            set {
              resultMap.updateValue(newValue, forKey: "url")
            }
          }

          /// Product category.
          public var category: String {
            get {
              return resultMap["category"]! as! String
            }
            set {
              resultMap.updateValue(newValue, forKey: "category")
            }
          }

          /// Product description.
          public var description: String {
            get {
              return resultMap["description"]! as! String
            }
            set {
              resultMap.updateValue(newValue, forKey: "description")
            }
          }

          /// Product short description.
          public var smallDescription: String {
            get {
              return resultMap["smallDescription"]! as! String
            }
            set {
              resultMap.updateValue(newValue, forKey: "smallDescription")
            }
          }

          /// Inform if the product is available
          public var isAvailable: Bool {
            get {
              return resultMap["isAvailable"]! as! Bool
            }
            set {
              resultMap.updateValue(newValue, forKey: "isAvailable")
            }
          }

          /// Product price.
          public var price: Price {
            get {
              return Price(unsafeResultMap: resultMap["price"]! as! ResultMap)
            }
            set {
              resultMap.updateValue(newValue.resultMap, forKey: "price")
            }
          }

          /// Product address.
          public var address: Address? {
            get {
              return (resultMap["address"] as? ResultMap).flatMap { Address(unsafeResultMap: $0) }
            }
            set {
              resultMap.updateValue(newValue?.resultMap, forKey: "address")
            }
          }

          /// Product tags.
          public var tags: [Tag] {
            get {
              return (resultMap["tags"] as! [ResultMap]).map { (value: ResultMap) -> Tag in Tag(unsafeResultMap: value) }
            }
            set {
              resultMap.updateValue(newValue.map { (value: Tag) -> ResultMap in value.resultMap }, forKey: "tags")
            }
          }

          /// List of product images.
          public var gallery: [Gallery] {
            get {
              return (resultMap["gallery"] as! [ResultMap]).map { (value: ResultMap) -> Gallery in Gallery(unsafeResultMap: value) }
            }
            set {
              resultMap.updateValue(newValue.map { (value: Gallery) -> ResultMap in value.resultMap }, forKey: "gallery")
            }
          }

          /// List of product amenities.
          public var amenities: [Amenity] {
            get {
              return (resultMap["amenities"] as! [ResultMap]).map { (value: ResultMap) -> Amenity in Amenity(unsafeResultMap: value) }
            }
            set {
              resultMap.updateValue(newValue.map { (value: Amenity) -> ResultMap in value.resultMap }, forKey: "amenities")
            }
          }

          public struct Price: GraphQLSelectionSet {
            public static let possibleTypes: [String] = ["ProductPrice"]

            public static var selections: [GraphQLSelection] {
              return [
                GraphQLField("__typename", type: .nonNull(.scalar(String.self))),
                GraphQLField("currency", type: .scalar(String.self)),
                GraphQLField("amount", type: .nonNull(.scalar(Double.self))),
                GraphQLField("originalAmount", type: .scalar(Double.self)),
                GraphQLField("taxes", type: .nonNull(.list(.nonNull(.object(Tax.selections))))),
              ]
            }

            public private(set) var resultMap: ResultMap

            public init(unsafeResultMap: ResultMap) {
              self.resultMap = unsafeResultMap
            }

            public init(currency: String? = nil, amount: Double, originalAmount: Double? = nil, taxes: [Tax]) {
              self.init(unsafeResultMap: ["__typename": "ProductPrice", "currency": currency, "amount": amount, "originalAmount": originalAmount, "taxes": taxes.map { (value: Tax) -> ResultMap in value.resultMap }])
            }

            public var __typename: String {
              get {
                return resultMap["__typename"]! as! String
              }
              set {
                resultMap.updateValue(newValue, forKey: "__typename")
              }
            }

            /// Price currency.
            public var currency: String? {
              get {
                return resultMap["currency"] as? String
              }
              set {
                resultMap.updateValue(newValue, forKey: "currency")
              }
            }

            /// Price amount.
            public var amount: Double {
              get {
                return resultMap["amount"]! as! Double
              }
              set {
                resultMap.updateValue(newValue, forKey: "amount")
              }
            }

            /// Original price amount.
            public var originalAmount: Double? {
              get {
                return resultMap["originalAmount"] as? Double
              }
              set {
                resultMap.updateValue(newValue, forKey: "originalAmount")
              }
            }

            /// Price taxes
            public var taxes: [Tax] {
              get {
                return (resultMap["taxes"] as! [ResultMap]).map { (value: ResultMap) -> Tax in Tax(unsafeResultMap: value) }
              }
              set {
                resultMap.updateValue(newValue.map { (value: Tax) -> ResultMap in value.resultMap }, forKey: "taxes")
              }
            }

            public struct Tax: GraphQLSelectionSet {
              public static let possibleTypes: [String] = ["ProductPriceTaxes"]

              public static var selections: [GraphQLSelection] {
                return [
                  GraphQLField("__typename", type: .nonNull(.scalar(String.self))),
                  GraphQLField("originalAmount", type: .nonNull(.scalar(Double.self))),
                  GraphQLField("originalCurrency", type: .nonNull(.scalar(String.self))),
                ]
              }

              public private(set) var resultMap: ResultMap

              public init(unsafeResultMap: ResultMap) {
                self.resultMap = unsafeResultMap
              }

              public init(originalAmount: Double, originalCurrency: String) {
                self.init(unsafeResultMap: ["__typename": "ProductPriceTaxes", "originalAmount": originalAmount, "originalCurrency": originalCurrency])
              }

              public var __typename: String {
                get {
                  return resultMap["__typename"]! as! String
                }
                set {
                  resultMap.updateValue(newValue, forKey: "__typename")
                }
              }

              /// Original tax amount
              public var originalAmount: Double {
                get {
                  return resultMap["originalAmount"]! as! Double
                }
                set {
                  resultMap.updateValue(newValue, forKey: "originalAmount")
                }
              }

              /// Original tax currency
              public var originalCurrency: String {
                get {
                  return resultMap["originalCurrency"]! as! String
                }
                set {
                  resultMap.updateValue(newValue, forKey: "originalCurrency")
                }
              }
            }
          }

          public struct Address: GraphQLSelectionSet {
            public static let possibleTypes: [String] = ["SearchResultItemAddress"]

            public static var selections: [GraphQLSelection] {
              return [
                GraphQLField("__typename", type: .nonNull(.scalar(String.self))),
                GraphQLField("city", type: .scalar(String.self)),
                GraphQLField("state", type: .scalar(String.self)),
                GraphQLField("country", type: .scalar(String.self)),
                GraphQLField("geoLocation", type: .object(GeoLocation.selections)),
              ]
            }

            public private(set) var resultMap: ResultMap

            public init(unsafeResultMap: ResultMap) {
              self.resultMap = unsafeResultMap
            }

            public init(city: String? = nil, state: String? = nil, country: String? = nil, geoLocation: GeoLocation? = nil) {
              self.init(unsafeResultMap: ["__typename": "SearchResultItemAddress", "city": city, "state": state, "country": country, "geoLocation": geoLocation.flatMap { (value: GeoLocation) -> ResultMap in value.resultMap }])
            }

            public var __typename: String {
              get {
                return resultMap["__typename"]! as! String
              }
              set {
                resultMap.updateValue(newValue, forKey: "__typename")
              }
            }

            /// Product city.
            public var city: String? {
              get {
                return resultMap["city"] as? String
              }
              set {
                resultMap.updateValue(newValue, forKey: "city")
              }
            }

            /// Product state.
            public var state: String? {
              get {
                return resultMap["state"] as? String
              }
              set {
                resultMap.updateValue(newValue, forKey: "state")
              }
            }

            /// Product country.
            public var country: String? {
              get {
                return resultMap["country"] as? String
              }
              set {
                resultMap.updateValue(newValue, forKey: "country")
              }
            }

            /// Product geolocation.
            public var geoLocation: GeoLocation? {
              get {
                return (resultMap["geoLocation"] as? ResultMap).flatMap { GeoLocation(unsafeResultMap: $0) }
              }
              set {
                resultMap.updateValue(newValue?.resultMap, forKey: "geoLocation")
              }
            }

            public struct GeoLocation: GraphQLSelectionSet {
              public static let possibleTypes: [String] = ["Coordinates"]

              public static var selections: [GraphQLSelection] {
                return [
                  GraphQLField("__typename", type: .nonNull(.scalar(String.self))),
                  GraphQLField("lat", type: .nonNull(.scalar(Double.self))),
                  GraphQLField("lon", type: .nonNull(.scalar(Double.self))),
                ]
              }

              public private(set) var resultMap: ResultMap

              public init(unsafeResultMap: ResultMap) {
                self.resultMap = unsafeResultMap
              }

              public init(lat: Double, lon: Double) {
                self.init(unsafeResultMap: ["__typename": "Coordinates", "lat": lat, "lon": lon])
              }

              public var __typename: String {
                get {
                  return resultMap["__typename"]! as! String
                }
                set {
                  resultMap.updateValue(newValue, forKey: "__typename")
                }
              }

              public var lat: Double {
                get {
                  return resultMap["lat"]! as! Double
                }
                set {
                  resultMap.updateValue(newValue, forKey: "lat")
                }
              }

              public var lon: Double {
                get {
                  return resultMap["lon"]! as! Double
                }
                set {
                  resultMap.updateValue(newValue, forKey: "lon")
                }
              }
            }
          }

          public struct Tag: GraphQLSelectionSet {
            public static let possibleTypes: [String] = ["SearchResultItemTag"]

            public static var selections: [GraphQLSelection] {
              return [
                GraphQLField("__typename", type: .nonNull(.scalar(String.self))),
                GraphQLField("label", type: .nonNull(.scalar(String.self))),
                GraphQLField("slug", type: .nonNull(.scalar(String.self))),
              ]
            }

            public private(set) var resultMap: ResultMap

            public init(unsafeResultMap: ResultMap) {
              self.resultMap = unsafeResultMap
            }

            public init(label: String, slug: String) {
              self.init(unsafeResultMap: ["__typename": "SearchResultItemTag", "label": label, "slug": slug])
            }

            public var __typename: String {
              get {
                return resultMap["__typename"]! as! String
              }
              set {
                resultMap.updateValue(newValue, forKey: "__typename")
              }
            }

            /// Label of the product tag.
            public var label: String {
              get {
                return resultMap["label"]! as! String
              }
              set {
                resultMap.updateValue(newValue, forKey: "label")
              }
            }

            /// Slug of the product tag.
            public var slug: String {
              get {
                return resultMap["slug"]! as! String
              }
              set {
                resultMap.updateValue(newValue, forKey: "slug")
              }
            }
          }

          public struct Gallery: GraphQLSelectionSet {
            public static let possibleTypes: [String] = ["SearchResultItemGalleryImage"]

            public static var selections: [GraphQLSelection] {
              return [
                GraphQLField("__typename", type: .nonNull(.scalar(String.self))),
                GraphQLField("url", arguments: ["quality": "HIGH", "resolution": "ORIGINAL"], type: .scalar(String.self)),
                GraphQLField("description", type: .scalar(String.self)),
              ]
            }

            public private(set) var resultMap: ResultMap

            public init(unsafeResultMap: ResultMap) {
              self.resultMap = unsafeResultMap
            }

            public init(url: String? = nil, description: String? = nil) {
              self.init(unsafeResultMap: ["__typename": "SearchResultItemGalleryImage", "url": url, "description": description])
            }

            public var __typename: String {
              get {
                return resultMap["__typename"]! as! String
              }
              set {
                resultMap.updateValue(newValue, forKey: "__typename")
              }
            }

            /// Gallery image url.
            public var url: String? {
              get {
                return resultMap["url"] as? String
              }
              set {
                resultMap.updateValue(newValue, forKey: "url")
              }
            }

            /// Gallery image description.
            public var description: String? {
              get {
                return resultMap["description"] as? String
              }
              set {
                resultMap.updateValue(newValue, forKey: "description")
              }
            }
          }

          public struct Amenity: GraphQLSelectionSet {
            public static let possibleTypes: [String] = ["SearchResultItemAmenity"]

            public static var selections: [GraphQLSelection] {
              return [
                GraphQLField("__typename", type: .nonNull(.scalar(String.self))),
                GraphQLField("name", type: .nonNull(.scalar(String.self))),
                GraphQLField("category", type: .nonNull(.scalar(String.self))),
              ]
            }

            public private(set) var resultMap: ResultMap

            public init(unsafeResultMap: ResultMap) {
              self.resultMap = unsafeResultMap
            }

            public init(name: String, category: String) {
              self.init(unsafeResultMap: ["__typename": "SearchResultItemAmenity", "name": name, "category": category])
            }

            public var __typename: String {
              get {
                return resultMap["__typename"]! as! String
              }
              set {
                resultMap.updateValue(newValue, forKey: "__typename")
              }
            }

            /// Amenity name.
            public var name: String {
              get {
                return resultMap["name"]! as! String
              }
              set {
                resultMap.updateValue(newValue, forKey: "name")
              }
            }

            /// Amenity category.
            public var category: String {
              get {
                return resultMap["category"]! as! String
              }
              set {
                resultMap.updateValue(newValue, forKey: "category")
              }
            }
          }
        }
      }
    }
  }
}

// @generated
//  This file was automatically generated and should not be edited.

import Apollo
import Foundation

/// HUGraphQL namespace
public extension HUGraphQL {
  final class SuggestionsQuery: GraphQLQuery {
    /// The raw GraphQL definition of this operation.
    public let operationDefinition: String =
      """
      query suggestions($q: String!, $limit: Int, $productType: SuggestionProductType, $l10n: InputL10n!) {
        suggestions(q: $q, limit: $limit, productType: $productType, l10n: $l10n) {
          __typename
          results {
            __typename
            ... on LocationSuggestion {
              text
              filter
              suggestionType
              country
              state
              city
            }
            ... on ProductSuggestion {
              text
              filter
              suggestionType
              sku
              productType
              country
              state
              city
            }
            ... on TagSuggestion {
              text
              filter
              suggestionType
            }
          }
          total
        }
      }
      """

    public let operationName: String = "suggestions"

    public var q: String
    public var limit: Int?
    public var productType: SuggestionProductType?
    public var l10n: InputL10n

    public init(q: String, limit: Int? = nil, productType: SuggestionProductType? = nil, l10n: InputL10n) {
      self.q = q
      self.limit = limit
      self.productType = productType
      self.l10n = l10n
    }

    public var variables: GraphQLMap? {
      return ["q": q, "limit": limit, "productType": productType, "l10n": l10n]
    }

    public struct Data: GraphQLSelectionSet {
      public static let possibleTypes: [String] = ["Query"]

      public static var selections: [GraphQLSelection] {
        return [
          GraphQLField("suggestions", arguments: ["q": GraphQLVariable("q"), "limit": GraphQLVariable("limit"), "productType": GraphQLVariable("productType"), "l10n": GraphQLVariable("l10n")], type: .object(Suggestion.selections)),
        ]
      }

      public private(set) var resultMap: ResultMap

      public init(unsafeResultMap: ResultMap) {
        self.resultMap = unsafeResultMap
      }

      public init(suggestions: Suggestion? = nil) {
        self.init(unsafeResultMap: ["__typename": "Query", "suggestions": suggestions.flatMap { (value: Suggestion) -> ResultMap in value.resultMap }])
      }

      /// Fetches suggestions in Search API.
      public var suggestions: Suggestion? {
        get {
          return (resultMap["suggestions"] as? ResultMap).flatMap { Suggestion(unsafeResultMap: $0) }
        }
        set {
          resultMap.updateValue(newValue?.resultMap, forKey: "suggestions")
        }
      }

      public struct Suggestion: GraphQLSelectionSet {
        public static let possibleTypes: [String] = ["SuggestionPayload"]

        public static var selections: [GraphQLSelection] {
          return [
            GraphQLField("__typename", type: .nonNull(.scalar(String.self))),
            GraphQLField("results", type: .nonNull(.list(.nonNull(.object(Result.selections))))),
            GraphQLField("total", type: .nonNull(.scalar(Int.self))),
          ]
        }

        public private(set) var resultMap: ResultMap

        public init(unsafeResultMap: ResultMap) {
          self.resultMap = unsafeResultMap
        }

        public init(results: [Result], total: Int) {
          self.init(unsafeResultMap: ["__typename": "SuggestionPayload", "results": results.map { (value: Result) -> ResultMap in value.resultMap }, "total": total])
        }

        public var __typename: String {
          get {
            return resultMap["__typename"]! as! String
          }
          set {
            resultMap.updateValue(newValue, forKey: "__typename")
          }
        }

        /// Results found.
        public var results: [Result] {
          get {
            return (resultMap["results"] as! [ResultMap]).map { (value: ResultMap) -> Result in Result(unsafeResultMap: value) }
          }
          set {
            resultMap.updateValue(newValue.map { (value: Result) -> ResultMap in value.resultMap }, forKey: "results")
          }
        }

        /// Total results counter.
        public var total: Int {
          get {
            return resultMap["total"]! as! Int
          }
          set {
            resultMap.updateValue(newValue, forKey: "total")
          }
        }

        public struct Result: GraphQLSelectionSet {
          public static let possibleTypes: [String] = ["LocationSuggestion", "ProductSuggestion", "TagSuggestion"]

          public static var selections: [GraphQLSelection] {
            return [
              GraphQLTypeCase(
                variants: ["LocationSuggestion": AsLocationSuggestion.selections, "ProductSuggestion": AsProductSuggestion.selections, "TagSuggestion": AsTagSuggestion.selections],
                default: [
                  GraphQLField("__typename", type: .nonNull(.scalar(String.self))),
                ]
              )
            ]
          }

          public private(set) var resultMap: ResultMap

          public init(unsafeResultMap: ResultMap) {
            self.resultMap = unsafeResultMap
          }

          public static func makeLocationSuggestion(text: String, filter: String, suggestionType: String, country: String? = nil, state: String? = nil, city: String? = nil) -> Result {
            return Result(unsafeResultMap: ["__typename": "LocationSuggestion", "text": text, "filter": filter, "suggestionType": suggestionType, "country": country, "state": state, "city": city])
          }

          public static func makeProductSuggestion(text: String, filter: String, suggestionType: String, sku: HUGScalarSKU, productType: String, country: String? = nil, state: String? = nil, city: String? = nil) -> Result {
            return Result(unsafeResultMap: ["__typename": "ProductSuggestion", "text": text, "filter": filter, "suggestionType": suggestionType, "sku": sku, "productType": productType, "country": country, "state": state, "city": city])
          }

          public static func makeTagSuggestion(text: String, filter: String, suggestionType: String) -> Result {
            return Result(unsafeResultMap: ["__typename": "TagSuggestion", "text": text, "filter": filter, "suggestionType": suggestionType])
          }

          public var __typename: String {
            get {
              return resultMap["__typename"]! as! String
            }
            set {
              resultMap.updateValue(newValue, forKey: "__typename")
            }
          }

          public var asLocationSuggestion: AsLocationSuggestion? {
            get {
              if !AsLocationSuggestion.possibleTypes.contains(__typename) { return nil }
              return AsLocationSuggestion(unsafeResultMap: resultMap)
            }
            set {
              guard let newValue = newValue else { return }
              resultMap = newValue.resultMap
            }
          }

          public struct AsLocationSuggestion: GraphQLSelectionSet {
            public static let possibleTypes: [String] = ["LocationSuggestion"]

            public static var selections: [GraphQLSelection] {
              return [
                GraphQLField("__typename", type: .nonNull(.scalar(String.self))),
                GraphQLField("text", type: .nonNull(.scalar(String.self))),
                GraphQLField("filter", type: .nonNull(.scalar(String.self))),
                GraphQLField("suggestionType", type: .nonNull(.scalar(String.self))),
                GraphQLField("country", type: .scalar(String.self)),
                GraphQLField("state", type: .scalar(String.self)),
                GraphQLField("city", type: .scalar(String.self)),
              ]
            }

            public private(set) var resultMap: ResultMap

            public init(unsafeResultMap: ResultMap) {
              self.resultMap = unsafeResultMap
            }

            public init(text: String, filter: String, suggestionType: String, country: String? = nil, state: String? = nil, city: String? = nil) {
              self.init(unsafeResultMap: ["__typename": "LocationSuggestion", "text": text, "filter": filter, "suggestionType": suggestionType, "country": country, "state": state, "city": city])
            }

            public var __typename: String {
              get {
                return resultMap["__typename"]! as! String
              }
              set {
                resultMap.updateValue(newValue, forKey: "__typename")
              }
            }

            /// Suggestion text.
            public var text: String {
              get {
                return resultMap["text"]! as! String
              }
              set {
                resultMap.updateValue(newValue, forKey: "text")
              }
            }

            /// Suggestion filter.
            public var filter: String {
              get {
                return resultMap["filter"]! as! String
              }
              set {
                resultMap.updateValue(newValue, forKey: "filter")
              }
            }

            /// Suggestion Type.
            public var suggestionType: String {
              get {
                return resultMap["suggestionType"]! as! String
              }
              set {
                resultMap.updateValue(newValue, forKey: "suggestionType")
              }
            }

            /// Location suggestion country.
            public var country: String? {
              get {
                return resultMap["country"] as? String
              }
              set {
                resultMap.updateValue(newValue, forKey: "country")
              }
            }

            /// Location suggestion state.
            public var state: String? {
              get {
                return resultMap["state"] as? String
              }
              set {
                resultMap.updateValue(newValue, forKey: "state")
              }
            }

            /// Location suggestion city.
            public var city: String? {
              get {
                return resultMap["city"] as? String
              }
              set {
                resultMap.updateValue(newValue, forKey: "city")
              }
            }
          }

          public var asProductSuggestion: AsProductSuggestion? {
            get {
              if !AsProductSuggestion.possibleTypes.contains(__typename) { return nil }
              return AsProductSuggestion(unsafeResultMap: resultMap)
            }
            set {
              guard let newValue = newValue else { return }
              resultMap = newValue.resultMap
            }
          }

          public struct AsProductSuggestion: GraphQLSelectionSet {
            public static let possibleTypes: [String] = ["ProductSuggestion"]

            public static var selections: [GraphQLSelection] {
              return [
                GraphQLField("__typename", type: .nonNull(.scalar(String.self))),
                GraphQLField("text", type: .nonNull(.scalar(String.self))),
                GraphQLField("filter", type: .nonNull(.scalar(String.self))),
                GraphQLField("suggestionType", type: .nonNull(.scalar(String.self))),
                GraphQLField("sku", type: .nonNull(.scalar(HUGScalarSKU.self))),
                GraphQLField("productType", type: .nonNull(.scalar(String.self))),
                GraphQLField("country", type: .scalar(String.self)),
                GraphQLField("state", type: .scalar(String.self)),
                GraphQLField("city", type: .scalar(String.self)),
              ]
            }

            public private(set) var resultMap: ResultMap

            public init(unsafeResultMap: ResultMap) {
              self.resultMap = unsafeResultMap
            }

            public init(text: String, filter: String, suggestionType: String, sku: HUGScalarSKU, productType: String, country: String? = nil, state: String? = nil, city: String? = nil) {
              self.init(unsafeResultMap: ["__typename": "ProductSuggestion", "text": text, "filter": filter, "suggestionType": suggestionType, "sku": sku, "productType": productType, "country": country, "state": state, "city": city])
            }

            public var __typename: String {
              get {
                return resultMap["__typename"]! as! String
              }
              set {
                resultMap.updateValue(newValue, forKey: "__typename")
              }
            }

            /// Suggestion text.
            public var text: String {
              get {
                return resultMap["text"]! as! String
              }
              set {
                resultMap.updateValue(newValue, forKey: "text")
              }
            }

            /// Suggestion filter.
            public var filter: String {
              get {
                return resultMap["filter"]! as! String
              }
              set {
                resultMap.updateValue(newValue, forKey: "filter")
              }
            }

            /// Suggestion Type.
            public var suggestionType: String {
              get {
                return resultMap["suggestionType"]! as! String
              }
              set {
                resultMap.updateValue(newValue, forKey: "suggestionType")
              }
            }

            /// Suggestion product SKU.
            public var sku: HUGScalarSKU {
              get {
                return resultMap["sku"]! as! HUGScalarSKU
              }
              set {
                resultMap.updateValue(newValue, forKey: "sku")
              }
            }

            /// Suggestion product type.
            public var productType: String {
              get {
                return resultMap["productType"]! as! String
              }
              set {
                resultMap.updateValue(newValue, forKey: "productType")
              }
            }

            /// Suggestion product country.
            public var country: String? {
              get {
                return resultMap["country"] as? String
              }
              set {
                resultMap.updateValue(newValue, forKey: "country")
              }
            }

            /// Suggestion product state.
            public var state: String? {
              get {
                return resultMap["state"] as? String
              }
              set {
                resultMap.updateValue(newValue, forKey: "state")
              }
            }

            /// Suggestion product city.
            public var city: String? {
              get {
                return resultMap["city"] as? String
              }
              set {
                resultMap.updateValue(newValue, forKey: "city")
              }
            }
          }

          public var asTagSuggestion: AsTagSuggestion? {
            get {
              if !AsTagSuggestion.possibleTypes.contains(__typename) { return nil }
              return AsTagSuggestion(unsafeResultMap: resultMap)
            }
            set {
              guard let newValue = newValue else { return }
              resultMap = newValue.resultMap
            }
          }

          public struct AsTagSuggestion: GraphQLSelectionSet {
            public static let possibleTypes: [String] = ["TagSuggestion"]

            public static var selections: [GraphQLSelection] {
              return [
                GraphQLField("__typename", type: .nonNull(.scalar(String.self))),
                GraphQLField("text", type: .nonNull(.scalar(String.self))),
                GraphQLField("filter", type: .nonNull(.scalar(String.self))),
                GraphQLField("suggestionType", type: .nonNull(.scalar(String.self))),
              ]
            }

            public private(set) var resultMap: ResultMap

            public init(unsafeResultMap: ResultMap) {
              self.resultMap = unsafeResultMap
            }

            public init(text: String, filter: String, suggestionType: String) {
              self.init(unsafeResultMap: ["__typename": "TagSuggestion", "text": text, "filter": filter, "suggestionType": suggestionType])
            }

            public var __typename: String {
              get {
                return resultMap["__typename"]! as! String
              }
              set {
                resultMap.updateValue(newValue, forKey: "__typename")
              }
            }

            /// Suggestion text.
            public var text: String {
              get {
                return resultMap["text"]! as! String
              }
              set {
                resultMap.updateValue(newValue, forKey: "text")
              }
            }

            /// Suggestion filter.
            public var filter: String {
              get {
                return resultMap["filter"]! as! String
              }
              set {
                resultMap.updateValue(newValue, forKey: "filter")
              }
            }

            /// Suggestion Type.
            public var suggestionType: String {
              get {
                return resultMap["suggestionType"]! as! String
              }
              set {
                resultMap.updateValue(newValue, forKey: "suggestionType")
              }
            }
          }
        }
      }
    }
  }
}

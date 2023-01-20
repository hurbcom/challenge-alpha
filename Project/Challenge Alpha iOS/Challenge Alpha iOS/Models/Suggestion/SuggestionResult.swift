//
//  SuggestionResult.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 19/01/23.
//

import Foundation

// MARK: - SuggestionResult
struct SuggestionResult: Codable {
    var text: String?
    var filter: String?
    var suggestionType: SuggestionType?
}

enum SuggestionType: String, Codable {
    case city = "city"
    case state = "estado"
    case hotel = "hotel"
    case package = "package"
    case tag = "tag"
    case neighborhood = "neighborhood"
    case unknown
    
    init(from decoder: Decoder) throws {
        let container = try decoder.singleValueContainer()
        let string = try container.decode(String.self)
        
        switch string {
        case "city": self = .city
        case "estado": self = .state
        case "hotel": self = .hotel
        case "package": self = .package
        case "tag": self = .tag
        case "neighborhood": self = .neighborhood
        default: self = .unknown
        }
    }
}

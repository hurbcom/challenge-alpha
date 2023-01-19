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
    var suggestionType: SuggestionType
}

enum SuggestionType: String, Codable {
    case city = "city"
    case state = "estado"
    case hotel = "hotel"
    case tag = "tag"
}

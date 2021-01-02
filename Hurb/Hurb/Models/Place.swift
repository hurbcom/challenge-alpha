//
//  Places.swift
//  Hurb
//
//  Created by Arthur Givigir on 1/2/21.
//

import Foundation

// MARK: - PlaceModel
struct PlaceModel: Codable {
    var suggestions: [Suggestion]?
    var total: Int?
    var search: String?
    var packages: [JSONAny]?
}

// MARK: - Suggestion
struct Suggestion: Codable {
    var text, suggestionType, country, state: String?
    var city, filter, url: String?
}

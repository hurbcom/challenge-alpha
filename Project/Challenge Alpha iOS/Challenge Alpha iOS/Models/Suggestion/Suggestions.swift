//
//  Suggestions.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 19/01/23.
//

import Foundation

// MARK: - Suggestions GraphQL Model
struct Suggestions: Codable {
    var results: [SuggestionResult]
}

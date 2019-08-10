//
//  Suggestion.swift
//  Hurb
//
//  Created by Alexandre Papanis on 10/08/19.
//  Copyright © 2019 Papanis. All rights reserved.
//

import Foundation

struct ResultSuggestion: Codable {
    let total: Int?
    let search: String?
    let suggestions: [Suggestion]?

}

struct Suggestion: Codable {
    let text: String?
    let suggestionType: String?
    let country: String?
    let state: String?
    let city: String?
    let sku: String?
    let filter: String?
    let url: String?
}

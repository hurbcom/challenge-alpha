//
//  Suggestion.swift
//  hurb-challenge-alpha
//
//  Created by Hannah  on 26/12/2019.
//  Copyright © 2019 Hannah . All rights reserved.
//

import Foundation

struct Suggestions: Decodable {
    
    let suggestions: [Suggestion]?
}

struct Suggestion: Identifiable, Decodable {
    
    let id = UUID()
    let text: String?
    let suggestionType: String?
    let country: String?
    let state: String?
    let city: String?

}

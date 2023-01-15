//
//  Search.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 15/01/23.
//

import Foundation

// MARK: - GraphQL Search Model
struct Search: Codable {
    var results: [SearchResult]
}

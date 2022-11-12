//
//  SearchService.swift
//  Challenge_iOS
//
//  Created by Helio Junior on 12/11/22.
//

import Foundation
import HUGraphQL

protocol SearchServiceProtocol {
    func getSuggestionsFrom(text: String, completion: @escaping ([Suggestion]) -> Void)
    func fetchResultsFrom(query: String, completion: ([Search]) -> Void)
}

struct SearchService: SearchServiceProtocol {
    
    // MARK: Properties
    typealias Suggestion = HUGraphQL.SuggestionsQuery.Data.Suggestion
    typealias Search = HUGraphQL.SearchQuery.Data.Search
    private let graphQL = HUGService(enableLog: true)
    
    // MARK: Methods
    func getSuggestionsFrom(text: String, completion: @escaping ([Suggestion]) -> Void) {
        
    }
    
    func fetchResultsFrom(query: String, completion: ([Search]) -> Void) {
        
    }
}

//
//  SearchViewModel.swift
//  Challenge_iOS
//
//  Created by Helio Junior on 12/11/22.
//

import Foundation

final class SearchViewModel {
    // MARK: Properties
    private let service: SearchServiceProtocol
    private var searchResults: [SearchModel] = []
    var didReturnSuggestions: (([SuggestionModel]) -> Void)?
    var shouldUpdateUI: (() -> Void)?
    
    // MARK: Initialization
    init(service: SearchServiceProtocol = SearchService()) {
        self.service = service
    }
    
    // MARK: Methods
    func getSearchResults() -> [SearchModel] {
        searchResults
    }
    
    func getSuggestionsFrom(text: String) {
        service.getSuggestionsFrom(text: text) { suggestions in
            self.didReturnSuggestions?(suggestions)
        }
    }
    
    func fetchSearchFrom(query: String) {
        service.fetchSearchFrom(query: query, pagination: 1) { results in
            self.searchResults = results
            self.shouldUpdateUI?()
        }
    }
}

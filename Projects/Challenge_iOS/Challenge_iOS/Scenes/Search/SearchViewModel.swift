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
    var didReturnSuggestions: (([SuggestionModel]) -> Void)?
    var didReturnResults: (([SearchModel]) -> Void)?
    
    // MARK: Initialization
    init(service: SearchServiceProtocol = SearchService()) {
        self.service = service
    }
    
    // MARK: Methods
    func getSuggestionsFrom(text: String) {
        service.getSuggestionsFrom(text: text) { suggestions in
            self.didReturnSuggestions?(suggestions)
        }
    }
    
    func fetchSearchFrom(query: String) {
        service.fetchSearchFrom(query: query) { results in
            self.didReturnResults?(results)
        }
    }
}

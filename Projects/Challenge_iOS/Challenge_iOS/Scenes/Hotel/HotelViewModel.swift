//
//  HotelViewModel.swift
//  Challenge_iOS
//
//  Created by Helio Junior on 13/11/22.
//

import Foundation

final class HotelViewModel {
    // MARK: Properties
    private let service: HotelServiceProtocol
    private var searchResults: [SearchResultModel] = []
    var didReturnSuggestions: (([SuggestionModel]) -> Void)?
    var shouldUpdateUI: (() -> Void)?
    var shouldShowNotFound: (() -> Void)?
    
    // MARK: Initialization
    init(service: HotelServiceProtocol = HotelService()) {
        self.service = service
    }
    
    // MARK: Methods
    func getSearchResults() -> [SearchResultModel] {
        searchResults
    }
    
    func getSuggestionsFrom(text: String) {
        service.getSuggestionsFrom(text: text) { suggestions in
            self.didReturnSuggestions?(suggestions)
        }
    }
    
    func findHotelFrom(query: String) {
        service.findHotelFrom(query: query, pagination: 1) { response in
            switch response {
            case .success(let results):
                self.searchResults = results
                self.shouldUpdateUI?()
                
            case .failure:
                self.searchResults = []
                self.shouldShowNotFound?()
            }
        }
    }
}

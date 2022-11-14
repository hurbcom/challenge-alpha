//
//  PackageViewModel.swift
//  Challenge_iOS
//
//  Created by Helio Junior on 13/11/22.
//

import Foundation

final class PackageViewModel {
    // MARK: Properties
    private let service: PackageServiceProtocol
    private var searchResults: [SearchResultModel] = []
    var didReturnSuggestions: (([SuggestionModel]) -> Void)?
    var shouldUpdateUI: (() -> Void)?
    var shouldShowNotFound: (() -> Void)?
    
    // MARK: Initialization
    init(service: PackageServiceProtocol = PackageService()) {
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
    
    func findPackageFrom(query: String) {
        service.findPackageFrom(query: query, pagination: 1) { response in
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

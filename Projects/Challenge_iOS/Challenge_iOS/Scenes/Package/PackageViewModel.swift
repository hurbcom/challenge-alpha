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
    private var suggestionsResults: [SuggestionModel] = []
    private var searchResults: [SearchResultModel] = []
    var didReturnSuggestions: (() -> Void)?
    var shouldUpdateUI: (() -> Void)?
    var shouldShowNotFound: (() -> Void)?
    
    // MARK: Initialization
    init(service: PackageServiceProtocol = PackageService()) {
        self.service = service
    }
    
    // MARK: Methods
    func getSuggestionsResults() -> [SuggestionModel] {
        suggestionsResults
    }
    
    func getSearchResults() -> [SearchResultModel] {
        searchResults
    }
    
    func getSuggestionsFrom(text: String) {
        service.getSuggestionsFrom(text: text) { response in
            switch response {
            case .success(let suggestions):
                self.suggestionsResults = suggestions
                self.didReturnSuggestions?()
            case .failure(let error):
                self.suggestionsResults = []
                print("==> ERROR: \(error.localizedDescription)")
            }
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
                self.shouldUpdateUI?()
                self.shouldShowNotFound?()
            }
        }
    }
}

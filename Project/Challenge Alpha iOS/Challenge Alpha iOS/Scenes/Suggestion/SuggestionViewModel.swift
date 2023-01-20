//
//  SuggestionViewModel.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 19/01/23.
//

import Foundation
import Combine

final class SuggestionViewModel: ObservableObject {
    // MARK: - Published properties
    @Published var destinationSuggestions: [SuggestionResult] = []
    @Published var otherSuggestions: [SuggestionResult] = []
    @Published var searchText: String = ""
    
    // MARK: - Dispose bag
    var cancellables = Set<AnyCancellable>()
    
    // MARK: - Dependencies
    var suggestionType: SuggestionType
    var interactor: SuggestionInteractorInput
    var router: SuggestionRouterProtocol
    var onSearchComplete: ((String) -> Void)
    
    init(
        suggestionType: SuggestionType,
        interactor: SuggestionInteractorInput,
        router: SuggestionRouterProtocol,
        onSearchComplete: @escaping ((String) -> Void))
    {
        self.suggestionType = suggestionType
        self.interactor = interactor
        self.router = router
        self.onSearchComplete = onSearchComplete
    }
    
    // MARK: - Actions
    func onCloseTap() {
        router.dismiss()
    }
    
    func onChangeOfSearchText() {
        guard searchText.count > 2 else {
            return
        }
        
        interactor.getSuggestions(
            query: self.searchText,
            pagination: .init(
                page: 1,
                limit: 20,
                sort: .price,
                sortOrder: .desc),
            productType: suggestionType == .hotel ? .hotel : .package
        )
        .receive(on: DispatchQueue.main)
        .sink { completion in
            if case .failure(_) = completion {
                return
            }
        } receiveValue: { suggestions in
            self.destinationSuggestions = suggestions.filter({ suggestion in
                return (suggestion.suggestionType == .city) || (suggestion.suggestionType == .state)
            })
            
            self.otherSuggestions = suggestions.filter({ suggestion in
                return suggestion.suggestionType == self.suggestionType
            })
        }
        .store(in: &cancellables)
    }
    
    func onTextfieldSubmit() {
        self.onSearchComplete(self.searchText)
        router.dismiss()
    }
    
    func onDestinationTap(name: String) {
        self.onSearchComplete(name)
        router.dismiss()
    }
    
    // MARK: - Helpers
    func getOtherSuggestionsTitle() -> String {
        switch suggestionType {
        case .city:
            return "Destinos"
        case .state:
            return "Destinos"
        case .hotel:
            return "Hotéis"
        case .package:
            return "Pacotes"
        case .tag:
            return "Tags"
        case .neighborhood:
            return ""
        case .unknown:
            return ""
        }
    }
    
    func getOtherSuggestionsImage() -> String {
        switch suggestionType {
        case .hotel:
            return "building.fill"
        default:
            return "bag.fill"
        }
    }
}

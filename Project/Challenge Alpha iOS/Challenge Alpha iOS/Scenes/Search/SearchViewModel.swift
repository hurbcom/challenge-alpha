//
//  SearchViewModel.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 15/01/23.
//

import Foundation
import Combine

final class SearchViewModel: ObservableObject {
    // MARK: - Published variables
    @Published var searchResults: [SearchResult] = []
    @Published var selectedSegmentedControlIndex = 0
    @Published var searchText: String
    @Published var showLoading: Bool = true
    @Published var showError: Bool = false
    
    // MARK: - Dispose bag
    var cancellables = Set<AnyCancellable>()
    
    // MARK: - Dependencies
    var interactor: SearchInteractorInput
    var router: SearchRouterProtocol
    
    init(interactor: SearchInteractorInput, router: SearchRouterProtocol) {
        self.interactor = interactor
        self.router = router
        
        self.searchText = UserDefaultsManager.shared.getLastSearchedHotelQuery()
    }
    
    func onViewAppear() {
        self.interactor
            .getSearchResults(
                query: Constants.DEFAULT_DESTINATION,
                pagination: .init(
                    page: 1,
                    limit: 20,
                    sort: .price,
                    sortOrder: .desc)
            )
            .receive(on: DispatchQueue.main)
            .sink { completion in
                if case .failure(_) = completion {
                    self.showError = true
                }
                
                self.hideLoading()
            } receiveValue: { searchResults in
                if searchResults.isEmpty {
                    self.showError = true
                }
                
                self.searchResults = searchResults
            }
            .store(in: &cancellables)
    }
    
    func onChangeOfSelectedSegment() {
        self.searchText = self.selectedSegmentedControlIndex == 0 ? UserDefaultsManager.shared.getLastSearchedHotelQuery() : UserDefaultsManager.shared.getLastSearchedPackageQuery()
    }
    
    // MARK: - Actions
    func onSearchTap() {
        var suggestionType: SuggestionType = .hotel
        switch self.selectedSegmentedControlIndex {
        case 1:
            suggestionType = .package
        default:
            suggestionType = .hotel
        }
        
        router.presentSuggestions(suggestionType: suggestionType) { searchTerm in
            self.searchText = searchTerm
            
            suggestionType == .hotel ? self.router.navigateToHotelList(with: searchTerm) : self.router.navigateToPackageList(with: searchTerm)
            self.selectedSegmentedControlIndex == 0 ? UserDefaultsManager.shared.saveLastSearchedHotelQuery(searchTerm) : UserDefaultsManager.shared.saveLastSearchedPackageQuery(searchTerm)
        }
    }
    
    func onSearchButtonTap() {
        if self.selectedSegmentedControlIndex == 0 {
            self.router.navigateToHotelList(with: self.searchText)
            UserDefaultsManager.shared.saveLastSearchedHotelQuery(self.searchText)
            return
        }
        
        self.router.navigateToPackageList(with: self.searchText)
        UserDefaultsManager.shared.saveLastSearchedPackageQuery(self.searchText)
    }
    
    func onHotelTap(_ hotel: HotelResult) {
        router.navigateToHotelDetails(hotel)
    }
    
    func onPackageTap(_ package: PackageResult) {
        router.navigateToPackageDetails(package)
    }
    
    // MARK: - Helpers
    private func hideLoading() {
        self.showLoading = false
    }
}

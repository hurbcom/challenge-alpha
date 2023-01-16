//
//  SerachViewModel.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 15/01/23.
//

import Foundation
import Combine

final class SearchViewModel: ObservableObject {
    // MARK: - Published variables
    @Published var serachResults: [SearchResult] = []
    @Published var searchText: String = "Rio de Janeiro"
    @Published var showError: Bool = false
    
    // MARK: - Dispose bag
    var cancellables = Set<AnyCancellable>()
    
    // MARK: - Dependencies
    var interactor: SearchInteractorInput
    var router: SearchRouterProtocol
    
    init(interactor: SearchInteractorInput, router: SearchRouterProtocol) {
        self.interactor = interactor
        self.router = router
    }
    
    func onViewAppear() {
        self.interactor
            .getSearchResults(
                query: "Rio de Janeiro",
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
            } receiveValue: { searchResults in
                self.serachResults = searchResults
            }
            .store(in: &cancellables)
    }
    
    // MARK: - Actions
    func onSearchTap() {
        
    }
}

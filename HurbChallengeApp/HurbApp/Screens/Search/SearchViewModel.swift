//
//  SearchViewModel.swift
//  HurbApp
//
//  Created by Fabio Takahashi on 17/01/23.
//

import Foundation
import HUGraphQL

protocol SearchViewModelDelegate: AnyObject {
    func didUpdateSearchList()
    func didSearchFailed(error: AlertMessage)
}

protocol SearchViewModelProtocol {

    var searchResults: [SearchResult] { get }

    func updateTextValue(_ searchText: String?)
    func searchTextValue()
}

final class SearchViewModel {

    weak var delegate: SearchViewModelDelegate?

    private(set) var searchResults: [SearchResult]

    private var searchText: String

    init() {
        self.searchResults = []
        self.searchText = ""
    }

}

extension SearchViewModel: SearchViewModelProtocol {

    func updateTextValue(_ searchText: String?) {
        self.searchText = searchText ?? ""
    }

    func searchTextValue() {

        let pagination: HUGraphQL.SearchInputPagination? = nil

        let query = HUGraphQL.SearchQuery(
            q: searchText,
            pagination: pagination
        )

        GraphQLNetworkService.makeRequest(query: query) { [weak self] result in
            switch result {
            case .failure(let error):
                let alertMessage = AlertMessage(
                    title: "Atenção",
                    message: error.localizedDescription
                )
                self?.delegate?.didSearchFailed(error: alertMessage)
            case .success(let data):

                self?.searchResults = data.search?.results ?? []

                self?.delegate?.didUpdateSearchList()
            }
        }
    }

}

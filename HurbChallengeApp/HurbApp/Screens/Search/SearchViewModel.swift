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
}

protocol SearchViewModelProtocol {

    var searchList: [String] { get }

    func updateTextValue(_ searchText: String?)
    func searchTextValue()
}

final class SearchViewModel {

    weak var delegate: SearchViewModelDelegate?

    private(set) var searchList: [String]

    private var searchText: String

    init() {
        self.searchList = []
        self.searchText = ""
    }

    deinit {
        print("[DEBUG] HomeViewModel \(#function)")
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
                print("[DEBUG] SearchViewModel ERROR")
                dump(error)
            case .success(let data):
                print("[DEBUG] SearchViewModel SUCCESS")

                let searchResults: [SearchResult] = data.search?.results ?? []

                self?.searchList = [
                    "Teste",
                    "Teste",
                    "Teste",
                    "Teste",
                ]

//                let searchPagination: SearchPagination? = data.search?.pagination

                self?.delegate?.didUpdateSearchList()
            }
        }
    }

}

typealias SearchResult = HUGraphQL.SearchQuery.Data.Search.Result
//typealias SearchPagination = HUGraphQL.SearchQuery.Data.Search.Pagination

struct SearchItem {
    
}

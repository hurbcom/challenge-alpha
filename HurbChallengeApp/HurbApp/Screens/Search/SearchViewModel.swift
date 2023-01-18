//
//  SearchViewModel.swift
//  HurbApp
//
//  Created by Fabio Takahashi on 17/01/23.
//

import Foundation
import HUGraphQL

protocol SearchViewModelProtocol {
    func loadData()
}

final class SearchViewModel {

    init() {
        print("[DEBUG] HomeViewModel \(#function)")
    }

    deinit {
        print("[DEBUG] HomeViewModel \(#function)")
    }

}

extension SearchViewModel: SearchViewModelProtocol {

    func loadData() {
        print("[DEBUG] HomeViewModel \(#function)")

        let term: String = ""
        let pagination: HUGraphQL.SearchInputPagination? = nil

        let query = HUGraphQL.SearchQuery(q: term, pagination: pagination)

        GraphQLNetworkService.makeRequest(query: query) { result in
            switch result {
            case .failure(let error):
                print("[DEBUG] SearchViewModel ERROR")
                dump(error)
            case .success(let data):
                print("[DEBUG] SearchViewModel SUCCESS")
                dump(data)
            }
        }
    }

}

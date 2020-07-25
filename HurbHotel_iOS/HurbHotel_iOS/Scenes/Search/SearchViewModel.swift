//
//  SearchViewModel.swift
//  HurbHotel_iOS
//
//  Created by Helio Junior on 22/07/20.
//  Copyright © 2020 HelioTecnologia. All rights reserved.
//

import Foundation

final class SearchViewModel {
    
    // MARK: Properties
    private let service = SearchService()
    var filters: Filters?
    var products: [Product] = []
    private var maxPagination = 1
    private var pagination: Int = 1
    private var termSearch: String?
    var didSuccess: (() -> ())?
    var didError: ((String) -> ())?
    var notFound: (() -> ())?
    var didReturnSuggestions: ((Suggestions) -> ())?
    
    // MARK: Methods
    func paginationSearch() {
        guard maxPagination > pagination else {return}
        pagination += 1
        service.search(termSearch, page: pagination.description, success: { [weak self] searchResult in
            self?.products.append(contentsOf: searchResult.results ?? [])
            self?.didSuccess?()
        }) { [weak self] error in
            self?.didError?(error)
        }
    }
    
    func newSearchFrom(term: String?) {
        pagination = 1
        termSearch = term
        service.search(term, page: pagination.description, success: { [weak self] searchResult in
            self?.maxPagination = searchResult.pagination?.count ?? 1
            self?.products = searchResult.results ?? []
            self?.filters = searchResult.filters
            self?.didSuccess?()
        }) { [weak self] error in
            self?.products = []
            self?.notFound?()
        }
    }
    
    func getSuggestions(term: String?) {
        service.getSuggestions(term, success: { [weak self] suggestions in
            self?.didReturnSuggestions?(suggestions)
        }) { error in
            debugPrint("==> Error: \(error)")
        }
    }
}

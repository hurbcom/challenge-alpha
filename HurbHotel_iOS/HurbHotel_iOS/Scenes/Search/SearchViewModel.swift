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
    var searchResult: SearchResult?
    var didSuccess: (() -> ())?
    var didError: ((String) -> ())?
    private let service = SearchService()
    
    // MARK: Methods
    func searchFrom(term: String, page: String) {
        service.search(term, page: page, success: { [weak self] searchResult in
            self?.searchResult = searchResult
            self?.didSuccess?()
        }) { [weak self] error in
            self?.searchResult = nil
            self?.didError?(error)
        }
    }
}

//
//  SearchViewModel.swift
//  HurbHotel_iOS
//
//  Created by Helio Junior on 22/07/20.
//  Copyright © 2020 HelioTecnologia. All rights reserved.
//

import Foundation

class SearchViewModel {
    
    private let service = SearchService()
    
    var didSuccess: ((SearchResult) -> ())?
    var didError: ((String) -> ())?
    
    
    func searchFrom(term: String, page: String) {
        service.search(term, page: page, success: { [weak self] result in
            self?.didSuccess?(result)
        }) { [weak self] error in
            self?.didError?(error)
        }
    }
}

//
//  SearchViewController.swift
//  HurbHotel_iOS
//
//  Created by Helio Junior on 21/07/20.
//  Copyright © 2020 HelioTecnologia. All rights reserved.
//

import UIKit

final class SearchViewController: BaseViewController {
    
    // MARK: Properties
    let viewModel = SearchViewModel()
    var searchResult: SearchResult?
    
    // MARK: Overrides
    override func viewDidLoad() {
        super.viewDidLoad()
        navigationController?.navigationBar.isHidden = true
        
        bindEvents()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
                
        viewModel.searchFrom(term: "fortaleza", page: "1")
    }
    
    // MARK: Helpers
    func bindEvents() {
        viewModel.didSuccess = { [weak self] searchResult in
            self?.searchResult = searchResult
            print("##> SearchResult: \(searchResult.results?.compactMap({ $0.sku }))")
        }
        
        viewModel.didError = { error in
            print("##> Error: \(error)")
        }
    }
}

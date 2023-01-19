//
//  SearchProductPresenter.swift
//  Hurb-iOS-Challenge
//
//  Created by Rômulo Monteiro on 10/01/23.
//  Copyright (c) 2023 ___ORGANIZATIONNAME___. All rights reserved.
//
//  This file was generated by the Clean Swift Xcode Templates so
//  you can apply clean architecture to your iOS and Mac projects,
//  see http://clean-swift.com
//

import UIKit

protocol SearchProductPresentationLogic {
    
    func presentNewProducts(response: SearchProduct.Query.Response)
    func presentNoSearchResultsView()
    func presentErrorAlert()
    
    func presentProductDetails()
}

class SearchProductPresenter: SearchProductPresentationLogic {
    
    weak var viewController: SearchProductDisplayLogic?
    
    init(viewController: SearchProductDisplayLogic) {
        
        self.viewController = viewController
    }
    
    // MARK: Do something
    
    func presentNewProducts(response: SearchProduct.Query.Response) {
        
        let viewModel: SearchProduct.Query.ViewModel = SearchProduct.Query.ViewModel(pagination: response.pagination, products: response.products)
        self.viewController?.displayNewProducts(viewModel: viewModel)
    }
    
    func presentNoSearchResultsView() {
        
        self.viewController?.displayNoSearchResultsView()
    }
    
    func presentErrorAlert() {
        
        self.viewController?.displayErrorAlert()
    }
    
    func presentProductDetails() {
        
        self.viewController?.displayProductDetails()
    }
}

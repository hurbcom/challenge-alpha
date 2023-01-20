//
//  SearchProductViewControllerSpy.swift
//  Hurb-iOS-ChallengeTests
//
//  Created by Rômulo Monteiro on 20/01/23.
//

@testable import Hurb_iOS_Challenge

class SearchProductViewControllerSpy: SearchProductDisplayLogic {

    var invokedDisplayNewProducts = false
    var invokedDisplayNewProductsCount = 0
    var invokedDisplayNewProductsParameters: (viewModel: SearchProduct.Query.ViewModel, Void)?
    var invokedDisplayNewProductsParametersList = [(viewModel: SearchProduct.Query.ViewModel, Void)]()

    func displayNewProducts(viewModel: SearchProduct.Query.ViewModel) {
        invokedDisplayNewProducts = true
        invokedDisplayNewProductsCount += 1
        invokedDisplayNewProductsParameters = (viewModel, ())
        invokedDisplayNewProductsParametersList.append((viewModel, ()))
    }

    var invokedDisplayNoSearchResultsView = false
    var invokedDisplayNoSearchResultsViewCount = 0

    func displayNoSearchResultsView() {
        invokedDisplayNoSearchResultsView = true
        invokedDisplayNoSearchResultsViewCount += 1
    }

    var invokedDisplayErrorAlert = false
    var invokedDisplayErrorAlertCount = 0

    func displayErrorAlert() {
        invokedDisplayErrorAlert = true
        invokedDisplayErrorAlertCount += 1
    }

    var invokedDisplayProductDetails = false
    var invokedDisplayProductDetailsCount = 0

    func displayProductDetails() {
        invokedDisplayProductDetails = true
        invokedDisplayProductDetailsCount += 1
    }
}

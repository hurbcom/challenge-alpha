//
//  SearchProductPresenterSpy.swift
//  Hurb-iOS-ChallengeTests
//
//  Created by Rômulo Monteiro on 20/01/23.
//

@testable import Hurb_iOS_Challenge

class SearchProductPresenterSpy: SearchProductPresentationLogic {

    var invokedPresentNewProducts = false
    var invokedPresentNewProductsCount = 0
    var invokedPresentNewProductsParameters: (response: SearchProduct.Query.Response, Void)?
    var invokedPresentNewProductsParametersList = [(response: SearchProduct.Query.Response, Void)]()

    func presentNewProducts(response: SearchProduct.Query.Response) {
        invokedPresentNewProducts = true
        invokedPresentNewProductsCount += 1
        invokedPresentNewProductsParameters = (response, ())
        invokedPresentNewProductsParametersList.append((response, ()))
    }

    var invokedPresentNoSearchResultsView = false
    var invokedPresentNoSearchResultsViewCount = 0

    func presentNoSearchResultsView() {
        invokedPresentNoSearchResultsView = true
        invokedPresentNoSearchResultsViewCount += 1
    }

    var invokedPresentErrorAlert = false
    var invokedPresentErrorAlertCount = 0

    func presentErrorAlert() {
        invokedPresentErrorAlert = true
        invokedPresentErrorAlertCount += 1
    }

    var invokedPresentProductDetails = false
    var invokedPresentProductDetailsCount = 0

    func presentProductDetails() {
        invokedPresentProductDetails = true
        invokedPresentProductDetailsCount += 1
    }
}

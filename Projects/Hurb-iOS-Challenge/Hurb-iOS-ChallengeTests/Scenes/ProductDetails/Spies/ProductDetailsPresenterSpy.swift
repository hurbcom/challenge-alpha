//
//  ProductDetailsPresenterSpy.swift
//  Hurb-iOS-ChallengeTests
//
//  Created by Rômulo Monteiro on 20/01/23.
//

@testable import Hurb_iOS_Challenge

class ProductDetailsPresenterSpy: ProductDetailsPresentationLogic {

    var invokedPresentSetupView = false
    var invokedPresentSetupViewCount = 0
    var invokedPresentSetupViewParameters: (response: ProductDetails.Setup.Response, Void)?
    var invokedPresentSetupViewParametersList = [(response: ProductDetails.Setup.Response, Void)]()

    func presentSetupView(response: ProductDetails.Setup.Response) {
        invokedPresentSetupView = true
        invokedPresentSetupViewCount += 1
        invokedPresentSetupViewParameters = (response, ())
        invokedPresentSetupViewParametersList.append((response, ()))
    }

    var invokedPresentShareProduct = false
    var invokedPresentShareProductCount = 0
    var invokedPresentShareProductParameters: (response: ProductDetails.Share.Response, Void)?
    var invokedPresentShareProductParametersList = [(response: ProductDetails.Share.Response, Void)]()

    func presentShareProduct(response: ProductDetails.Share.Response) {
        invokedPresentShareProduct = true
        invokedPresentShareProductCount += 1
        invokedPresentShareProductParameters = (response, ())
        invokedPresentShareProductParametersList.append((response, ()))
    }
}

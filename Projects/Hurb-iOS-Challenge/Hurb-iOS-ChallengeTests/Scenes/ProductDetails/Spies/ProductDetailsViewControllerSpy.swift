//
//  ProductDetailsViewControllerSpy.swift
//  Hurb-iOS-ChallengeTests
//
//  Created by Rômulo Monteiro on 20/01/23.
//

@testable import Hurb_iOS_Challenge

class ProductDetailsViewControllerSpy: ProductDetailsDisplayLogic {

    var invokedDisplaySetupView = false
    var invokedDisplaySetupViewCount = 0
    var invokedDisplaySetupViewParameters: (viewModel: ProductDetails.Setup.ViewModel, Void)?
    var invokedDisplaySetupViewParametersList = [(viewModel: ProductDetails.Setup.ViewModel, Void)]()

    func displaySetupView(viewModel: ProductDetails.Setup.ViewModel) {
        invokedDisplaySetupView = true
        invokedDisplaySetupViewCount += 1
        invokedDisplaySetupViewParameters = (viewModel, ())
        invokedDisplaySetupViewParametersList.append((viewModel, ()))
    }

    var invokedDisplayShareProduct = false
    var invokedDisplayShareProductCount = 0
    var invokedDisplayShareProductParameters: (viewModel: ProductDetails.Share.ViewModel, Void)?
    var invokedDisplayShareProductParametersList = [(viewModel: ProductDetails.Share.ViewModel, Void)]()

    func displayShareProduct(viewModel: ProductDetails.Share.ViewModel) {
        invokedDisplayShareProduct = true
        invokedDisplayShareProductCount += 1
        invokedDisplayShareProductParameters = (viewModel, ())
        invokedDisplayShareProductParametersList.append((viewModel, ()))
    }
}

//
//  SearchLocationViewControllerSpy.swift
//  Hurb-iOS-ChallengeTests
//
//  Created by Rômulo Monteiro on 20/01/23.
//

@testable import Hurb_iOS_Challenge

class SearchLocationViewControllerSpy: SearchLocationDisplayLogic {

    var invokedDisplayLocations = false
    var invokedDisplayLocationsCount = 0
    var invokedDisplayLocationsParameters: (viewModel: SearchLocation.Setup.ViewModel, Void)?
    var invokedDisplayLocationsParametersList = [(viewModel: SearchLocation.Setup.ViewModel, Void)]()

    func displayLocations(viewModel: SearchLocation.Setup.ViewModel) {
        invokedDisplayLocations = true
        invokedDisplayLocationsCount += 1
        invokedDisplayLocationsParameters = (viewModel, ())
        invokedDisplayLocationsParametersList.append((viewModel, ()))
    }

    var invokedDisplayHideSkeleton = false
    var invokedDisplayHideSkeletonCount = 0

    func displayHideSkeleton() {
        invokedDisplayHideSkeleton = true
        invokedDisplayHideSkeletonCount += 1
    }

    var invokedDisplayErrorAlert = false
    var invokedDisplayErrorAlertCount = 0

    func displayErrorAlert() {
        invokedDisplayErrorAlert = true
        invokedDisplayErrorAlertCount += 1
    }
}

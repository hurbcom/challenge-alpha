//
//  SearchLocationPresenterSpy.swift
//  Hurb-iOS-ChallengeTests
//
//  Created by Rômulo Monteiro on 20/01/23.
//

@testable import Hurb_iOS_Challenge

class SearchLocationPresenterSpy: SearchLocationPresentationLogic {

    var invokedPresentLocations = false
    var invokedPresentLocationsCount = 0
    var invokedPresentLocationsParameters: (response: SearchLocation.Setup.Response, Void)?
    var invokedPresentLocationsParametersList = [(response: SearchLocation.Setup.Response, Void)]()

    func presentLocations(response: SearchLocation.Setup.Response) {
        invokedPresentLocations = true
        invokedPresentLocationsCount += 1
        invokedPresentLocationsParameters = (response, ())
        invokedPresentLocationsParametersList.append((response, ()))
    }

    var invokedPresentHideSkeleton = false
    var invokedPresentHideSkeletonCount = 0

    func presentHideSkeleton() {
        invokedPresentHideSkeleton = true
        invokedPresentHideSkeletonCount += 1
    }

    var invokedPresentErrorAlert = false
    var invokedPresentErrorAlertCount = 0

    func presentErrorAlert() {
        invokedPresentErrorAlert = true
        invokedPresentErrorAlertCount += 1
    }
}

//
//  SearchLocationPresenterTest.swift
//  Hurb-iOS-ChallengeTests
//
//  Created by Rômulo Monteiro on 20/01/23.
//

import XCTest
@testable import Hurb_iOS_Challenge

class SearchLocationPresenterTest: XCTestCase {
    
    var sut: SearchLocationPresentationLogic!
    var viewControllerSpy: SearchLocationViewControllerSpy!
    
    override func setUp() {
        
        self.viewControllerSpy = SearchLocationViewControllerSpy()
        self.sut = SearchLocationPresenter(viewController: self.viewControllerSpy)
    }
    
    func testPresentLocations() {
        
        self.sut.presentLocations(response: SearchLocation.Setup.Response(locations: [""]))
        
        XCTAssertTrue(self.viewControllerSpy.invokedDisplayLocations)
    }
    
    func testPresentHideSkeleton() {
        
        self.sut.presentHideSkeleton()
        
        XCTAssertTrue(self.viewControllerSpy.invokedDisplayHideSkeleton)
    }
    
    func testPresentErrorAlert() {
        
        self.sut.presentErrorAlert()
        
        XCTAssertTrue(self.viewControllerSpy.invokedDisplayErrorAlert)
    }
}

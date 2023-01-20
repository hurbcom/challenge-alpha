//
//  SearchProductPresenterTest.swift
//  Hurb-iOS-ChallengeTests
//
//  Created by Rômulo Monteiro on 20/01/23.
//

import XCTest
@testable import Hurb_iOS_Challenge

class SearchProductPresenterTest: XCTestCase {
    
    var sut: SearchProductPresentationLogic!
    var viewControllerSpy: SearchProductViewControllerSpy!
    
    override func setUp() {
        
        self.viewControllerSpy = SearchProductViewControllerSpy()
        self.sut = SearchProductPresenter(viewController: self.viewControllerSpy)
    }
    
    func testPresentNewProducts() {
        
        self.sut.presentNewProducts(response: SearchProduct.Query.Response(products: []))

        XCTAssertTrue(self.viewControllerSpy.invokedDisplayNewProducts)
    }
    
    func testPresentNoSearchResultsView() {
        
        self.sut.presentNoSearchResultsView()

        XCTAssertTrue(self.viewControllerSpy.invokedDisplayNoSearchResultsView)
    }
    
    func testPresentErrorAlert() {
        
        self.sut.presentErrorAlert()

        XCTAssertTrue(self.viewControllerSpy.invokedDisplayErrorAlert)
    }
    
    func testPresentProductDetails() {
        
        self.sut.presentProductDetails()

        XCTAssertTrue(self.viewControllerSpy.invokedDisplayProductDetails)
    }
}

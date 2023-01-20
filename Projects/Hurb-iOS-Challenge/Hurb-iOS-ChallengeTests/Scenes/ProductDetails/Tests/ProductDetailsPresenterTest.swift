//
//  ProductDetailsPresenterTest.swift
//  Hurb-iOS-ChallengeTests
//
//  Created by Rômulo Monteiro on 20/01/23.
//

import XCTest
@testable import Hurb_iOS_Challenge

class ProductDetailsPresenterTest: XCTestCase {
    
    var sut: ProductDetailsPresentationLogic!
    var viewControllerSpy: ProductDetailsViewControllerSpy!
    
    override func setUp() {
        
        self.viewControllerSpy = ProductDetailsViewControllerSpy()
        self.sut = ProductDetailsPresenter(viewController: self.viewControllerSpy)
    }
    
    func testPresentSetupView() {
        
        let product: Product = Product(url: nil, category: .hotel, description: "", medias: [], price: Price(amount: 0, currency: ""), location: Location(state: nil, country: nil, city: nil, text: nil), name: "", amenities: [])
        self.sut.presentSetupView(response: ProductDetails.Setup.Response(product: product))

        XCTAssertTrue(self.viewControllerSpy.invokedDisplaySetupView)
    }
    
    func testPresentShareProduct() {
        
        self.sut.presentShareProduct(response: ProductDetails.Share.Response(sharedText: ""))

        XCTAssertTrue(self.viewControllerSpy.invokedDisplayShareProduct)
    }
}

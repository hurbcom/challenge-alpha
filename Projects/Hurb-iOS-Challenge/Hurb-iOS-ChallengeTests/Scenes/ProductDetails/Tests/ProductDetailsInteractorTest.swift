//
//  ProductDetailsInteractorTest.swift
//  Hurb-iOS-ChallengeTests
//
//  Created by Rômulo Monteiro on 20/01/23.
//

import XCTest
@testable import Hurb_iOS_Challenge

class ProductDetailsInteractorTest: XCTestCase {
    
    var sut: ProductDetailsInteractor!
    var workerSpy: ProductDetailsWorkerSpy!
    var presenterSpy: ProductDetailsPresenterSpy!
    
    override func setUp() {
        
        self.workerSpy = ProductDetailsWorkerSpy()
        self.presenterSpy = ProductDetailsPresenterSpy()
        self.sut = ProductDetailsInteractor(presenter: self.presenterSpy, worker: self.workerSpy)
    }
    
    func testSetupView() {
        
        self.sut.product = Product(url: nil, category: .hotel, description: "", medias: [], price: Price(amount: 0, currency: ""), location: Location(state: nil, country: nil, city: nil, text: nil), name: "", amenities: [])
        self.sut?.setupView()
        
        XCTAssertTrue(self.presenterSpy.invokedPresentSetupView)
    }

    func testShareProduct() {
        
        self.sut.product = Product(url: nil, category: .hotel, description: "", medias: [], price: Price(amount: 0, currency: ""), location: Location(state: nil, country: nil, city: nil, text: nil), name: "", amenities: [])
        self.sut?.shareProduct()

        XCTAssertTrue(self.presenterSpy.invokedPresentShareProduct)
    }
}

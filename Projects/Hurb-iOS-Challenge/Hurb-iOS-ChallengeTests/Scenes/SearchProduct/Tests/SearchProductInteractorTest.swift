//
//  SearchProductInteractorTest.swift
//  Hurb-iOS-ChallengeTests
//
//  Created by Rômulo Monteiro on 20/01/23.
//

import XCTest
@testable import Hurb_iOS_Challenge

class SearchProductInteractorTest: XCTestCase {
    
    var sut: SearchProductInteractor!
    var workerSpy: SearchProductWorkerSpy!
    var presenterSpy: SearchProductPresenterSpy!
    
    override func setUp() {
        
        self.workerSpy = SearchProductWorkerSpy()
        self.presenterSpy = SearchProductPresenterSpy()
        self.sut = SearchProductInteractor(presenter: self.presenterSpy, worker: self.workerSpy)
    }
    
    func testSearchProductsSuccess() {
        
        self.workerSpy.stubbedSearchProductsCompletionResult = (.success(ProductContainer(pagination: nil, products: [Product(url: nil, category: .hotel, description: "", medias: [], price: Price(amount: 0, currency: ""), location: Location(state: nil, country: nil, city: nil, text: nil), name: "", amenities: [])])), ())

        self.sut?.searchProducts(request: SearchProduct.Query.Request(term: "teste", page: 1, limit: 10))
        XCTAssertTrue(self.presenterSpy.invokedPresentNewProducts)
    }

    func testSearchProductsSuccessWithEmptyProductsInFirstPage() {
        
        self.workerSpy.stubbedSearchProductsCompletionResult = (.success(ProductContainer(pagination: nil, products: nil)), ())

        self.sut?.searchProducts(request: SearchProduct.Query.Request(term: "teste", page: 1, limit: 10))
        XCTAssertTrue(self.presenterSpy.invokedPresentNoSearchResultsView)
    }
    
    func testSearchProductsFailure() {
        
        self.workerSpy.stubbedSearchProductsCompletionResult = (.failure(.emptyReturn), ())

        self.sut?.searchProducts(request: SearchProduct.Query.Request(term: "teste", page: 1, limit: 10))
        XCTAssertTrue(self.presenterSpy.invokedPresentErrorAlert)
    }
    
    func testSelectingAProduct() {
        
        let product: Product = Product(url: nil, category: .hotel, description: "", medias: [], price: Price(amount: 0, currency: ""), location: Location(state: nil, country: nil, city: nil, text: nil), name: "", amenities: [])
        self.sut?.didSeletedProduct(request: SearchProduct.Selection.Request(product: product))
        
        XCTAssertEqual(product, self.sut.product)
    }
}

//
//  SearchLocationInteractorTest.swift
//  Hurb-iOS-ChallengeTests
//
//  Created by Rômulo Monteiro on 20/01/23.
//

import XCTest
@testable import Hurb_iOS_Challenge

class SearchLocationInteractorTest: XCTestCase {
    
    var sut: SearchLocationInteractor!
    var workerSpy: SearchLocationWorkerSpy!
    var presenterSpy: SearchLocationPresenterSpy!
    
    override func setUp() {
        
        self.workerSpy = SearchLocationWorkerSpy()
        self.presenterSpy = SearchLocationPresenterSpy()
        self.sut = SearchLocationInteractor(presenter: self.presenterSpy, worker: self.workerSpy)
    }
    
    func testEqualTerm() {
        
        self.sut.term = "test"

        self.sut?.searchTerm(request: SearchLocation.Setup.Request(term: "test", limit: 1))
        XCTAssertTrue(self.presenterSpy.invokedPresentHideSkeleton)
    }
    
    func testEqualTermWithThreeLetters() {
        
        self.sut.term = "tes"

        self.workerSpy.stubbedSearchLocationsCompletionResult = (.success([Location(state: "", country: "", city: "", text: "")]), ())

        self.sut?.searchTerm(request: SearchLocation.Setup.Request(term: "tes", limit: 1))
        XCTAssertTrue(self.presenterSpy.invokedPresentHideSkeleton)
        XCTAssertTrue(self.presenterSpy.invokedPresentLocations)
    }

    func testSearchTermSuccess() {
        
        self.workerSpy.stubbedSearchLocationsCompletionResult = (.success([Location(state: "", country: "", city: "", text: "")]), ())

        self.sut?.searchTerm(request: SearchLocation.Setup.Request(term: "teste", limit: 1))
        XCTAssertTrue(self.presenterSpy.invokedPresentHideSkeleton)
        XCTAssertTrue(self.presenterSpy.invokedPresentLocations)
    }
    
    func testSearchTermFailed() {
        
        self.workerSpy.stubbedSearchLocationsCompletionResult = (.failure(.emptyReturn), ())

        self.sut?.searchTerm(request: SearchLocation.Setup.Request(term: "teeeees", limit: 1))
        XCTAssertTrue(self.presenterSpy.invokedPresentHideSkeleton)
        XCTAssertTrue(self.presenterSpy.invokedPresentErrorAlert)
    }
}

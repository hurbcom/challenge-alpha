//
//  HomeViewModelTest.swift
//  Hurb AlphaTests
//
//  Created by Thiago Augusto on 30/07/20.
//  Copyright © 2020 objectivesev. All rights reserved.
//

import XCTest
@testable import Hurb_Alpha



class HomeViewModelTest: XCTestCase {
    class MockHotelService: IHotelService {
        var hotels = [Result]()
        var isSuccess = true
        var errorCode = 0
        
        func getHotels(query: String, page: Int, handler: @escaping HotelsHandler) {
            let queryResult = QueryResult(JSON: [:])!
            queryResult.results = hotels
            let error = NetworkError.withError(error: NSError.from(code: errorCode, data: Data(), description: ""))
            handler(isSuccess ? .success(queryResult, 200) : .failure(error, errorCode))
        }
    }
    
    private var mockService: MockHotelService!
    private var viewModel: HomeViewModel!
    private var delegateExpectation: XCTestExpectation!
    private var actionResult: HomeViewModelAction!
    
    override func setUp() {
        mockService = MockHotelService()
        viewModel = HomeViewModel(hotelsService: mockService)
        viewModel.delegate = self
    }
    
    override func tearDown() {
        mockService = nil
        viewModel = nil
        delegateExpectation = nil
        actionResult = nil
    }
    
    // MARK: Initial Values
    func testInit_Empty() {
        XCTAssertEqual(0, viewModel.numberOfSections())
    }
    
    // MARK: Error
    func testError_delegatesError() {
        createExpectation()
        
        mockService.isSuccess = false
        mockService.errorCode = 404
        viewModel.updateQuery(text: "xxxxxxxxxxx")
        viewModel.getItems(reload: true)
        
        wait(for: [delegateExpectation], timeout: 20)
        switch actionResult {
        case .failure(_, let code):
            XCTAssertEqual(code, 404)
        default:
            XCTFail()
        }
    }
    
    func testError_emptyQuery() {
        createExpectation()
        
        mockService.isSuccess = false
        mockService.errorCode = HAError.invalidQuery.code
        viewModel.updateQuery(text: nil)
        viewModel.getItems(reload: true)
        
        wait(for: [delegateExpectation], timeout: 20)
        switch actionResult {
        case .failure(_, let code):
            XCTAssertEqual(code, 555)
        default:
            XCTFail()
        }
    }
    
    // MARK: Results
    func testResult_hasItems() {
        createExpectation()
        
        let resultHotel = Result(JSON: ["isHotel": true, "name": "first"])!
        mockService.hotels = [resultHotel]
        
        viewModel.updateQuery(text: "hotels")
        viewModel.getItems(reload: true)
        
        wait(for: [delegateExpectation], timeout: 20)
        XCTAssertEqual(viewModel.numberOfSections(), 1)
        XCTAssertEqual(viewModel.numberOfRowsIn(0), 1)
        let viewModelItem = viewModel.itemAt(IndexPath(row: 0, section: 0))
        XCTAssertEqual(viewModelItem.name, "first")
    }
    
    func testResult_sectionByStars() {
        createExpectation()
        
        let hotel2Stars = Result(JSON: ["isHotel": true, "name": "first", "stars": 2])!
        let hotel5Stars = Result(JSON: ["isHotel": true, "name": "second", "stars": 5])!
        
        mockService.hotels = [hotel2Stars, hotel5Stars]
        
        viewModel.updateQuery(text: "hotels")
        viewModel.getItems(reload: true)
        
        wait(for: [delegateExpectation], timeout: 20)
        XCTAssertEqual(viewModel.numberOfSections(), 2)
        XCTAssertEqual(viewModel.numberOfRowsIn(0), 1)
        let viewModel5 = viewModel.itemAt(IndexPath(row: 0, section: 0))
        XCTAssertEqual(viewModel5.name, "second")
        let viewModel2 = viewModel.itemAt(IndexPath(row: 0, section: 1))
        XCTAssertEqual(viewModel2.name, "first")
    }
    
    func testResult_sectionForPackage() {
        createExpectation()
        
        let hotel2Stars = Result(JSON: ["isHotel": true, "name": "first", "stars": 2])!
        let hotel5Stars = Result(JSON: ["isHotel": true, "name": "second", "stars": 5])!
        let package = Result(JSON: ["isPackage": true, "name": "package"])!
        
        mockService.hotels = [hotel2Stars, package, hotel5Stars]
        
        viewModel.updateQuery(text: "hotels")
        viewModel.getItems(reload: true)
        
        wait(for: [delegateExpectation], timeout: 20)
        XCTAssertEqual(viewModel.numberOfSections(), 3)
        XCTAssertEqual(viewModel.numberOfRowsIn(2), 1)
        let viewModelPackage = viewModel.itemAt(IndexPath(row: 0, section: 2))
        XCTAssertEqual(viewModelPackage.name, "package")
    }
    
    
    func testResult_pagination() {
        
        let hotel2Stars = Result(JSON: ["isHotel": true, "name": "first", "stars": 2])!
        let hotel5Stars = Result(JSON: ["isHotel": true, "name": "second", "stars": 5])!
        let package = Result(JSON: ["isPackage": true, "name": "package"])!
        
        mockService.hotels = [hotel2Stars, package, hotel5Stars]
        
        viewModel.updateQuery(text: "hotels")
        viewModel.getItems(reload: true)
        
        let hotel1Stars = Result(JSON: ["isHotel": true, "name": "firstLater", "stars": 1])!
        let hotel1AlsoStars = Result(JSON: ["isHotel": true, "name": "secondLater", "stars": 1])!
        let hotel5AlsoStars = Result(JSON: ["isHotel": true, "name": "fiveLater", "stars": 5])!
        let package2 = Result(JSON: ["isPackage": true, "name": "package2"])!
        
        createExpectation()
        
        mockService.hotels = [hotel1Stars, hotel1AlsoStars, hotel5AlsoStars, package2]
        viewModel.getItems(reload: false)
        
        wait(for: [delegateExpectation], timeout: 20)
        XCTAssertEqual(viewModel.numberOfSections(), 4)
        XCTAssertEqual(viewModel.numberOfRowsIn(0), 2)
        XCTAssertEqual(viewModel.itemAt(IndexPath(row: 0, section: 0)).name, "second")
        XCTAssertEqual(viewModel.itemAt(IndexPath(row: 1, section: 0)).name, "fiveLater")
        XCTAssertEqual(viewModel.numberOfRowsIn(1), 1)
        XCTAssertEqual(viewModel.itemAt(IndexPath(row: 0, section: 1)).name, "first")
        XCTAssertEqual(viewModel.numberOfRowsIn(2), 2)
        XCTAssertEqual(viewModel.itemAt(IndexPath(row: 0, section: 2)).name, "firstLater")
        XCTAssertEqual(viewModel.itemAt(IndexPath(row: 1, section: 2)).name, "secondLater")
        XCTAssertEqual(viewModel.numberOfRowsIn(3), 2)
        XCTAssertEqual(viewModel.itemAt(IndexPath(row: 0, section: 3)).name, "package")
        XCTAssertEqual(viewModel.itemAt(IndexPath(row: 1, section: 3)).name, "package2")
    }
}

// MARK: Helpers
private extension HomeViewModelTest {
    // Não dá pra colocar no setUp pois nem todos os métodos de teste usam a expectation.
    // Logo eles iriam acabar falhando sem precisar
    func createExpectation() {
        delegateExpectation = expectation(description: "HomeViewModel")
    }
}

extension HomeViewModelTest: HomeViewModelDelegate {
    func didSelectAction(_ action: HomeViewModelAction) {
        actionResult = action
        delegateExpectation?.fulfill()
    }
}

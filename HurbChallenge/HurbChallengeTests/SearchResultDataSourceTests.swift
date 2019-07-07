//
//  SearchResultDataSourceTests.swift
//  HurbChallengeTests
//
//  Created by Felipe Alves on 07/07/19.
//  Copyright © 2019 Bolzaniapps. All rights reserved.
//

import XCTest

let FIVE_STAR_SECTION = 0
let FOUR_STAR_SECTION = 1
let THREE_STAR_SECTION = 2
let TWO_STAR_SECTION = 3
let ONE_STAR_SECTION = 4
let TICKET_SECTION = 5
let PACKAGE_SECTION = 6

class SearchResultDataSourceTests: XCTestCase {

    var datasource: SearchResultsDataSource!
    var oneStarHotel = loadSingleResultJSON(filename: "1star")!
    var twoStarsHotel = loadSingleResultJSON(filename: "2stars")!
    var threeStarsHotel = loadSingleResultJSON(filename: "3stars")!
    var fourStarsHotel = loadSingleResultJSON(filename: "4stars")!
    var fiveStarsHotel = loadSingleResultJSON(filename: "5stars")!
    var ticket = loadSingleResultJSON(filename: "ticket")!
    var package = loadSingleResultJSON(filename: "package")!
    
    
    override func setUp() {
        datasource = SearchResultsDataSource()
    }

    override func tearDown() {
        datasource = nil
    }

    // MARK: - Single insertion on empty DataSource
    
    func testInsertingSingleOneStarHotelIntoEmptyDataSource() {
        let expectation = self.expectation(description: "EqualIndexPaths")
        let desiredResponse = [IndexPath(row: 0, section: ONE_STAR_SECTION)]
        var receivedResponse: [IndexPath]!
        datasource
            .update(with: [oneStarHotel])
            .then({
                receivedResponse = $0
                expectation.fulfill()
            })
        waitForExpectations(timeout: 1, handler: nil)
        XCTAssertEqual(desiredResponse, receivedResponse)
        
    }
    
    func testInsertingSingleTwoStarsHotelIntoEmptyDataSource() {
        let expectation = self.expectation(description: "EqualIndexPaths")
        let desiredResponse = [IndexPath(row: 0, section: TWO_STAR_SECTION)]
        var receivedResponse: [IndexPath]!
        datasource
            .update(with: [twoStarsHotel])
            .then({
                receivedResponse = $0
                expectation.fulfill()
            })
        waitForExpectations(timeout: 1, handler: nil)
        XCTAssertEqual(desiredResponse, receivedResponse)
        
    }
    
    func testInsertingSingleThreeStarsHotelIntoEmptyDataSource() {
        let expectation = self.expectation(description: "EqualIndexPaths")
        let desiredResponse = [IndexPath(row: 0, section: THREE_STAR_SECTION)]
        var receivedResponse: [IndexPath]!
        datasource
            .update(with: [threeStarsHotel])
            .then({
                receivedResponse = $0
                expectation.fulfill()
            })
        waitForExpectations(timeout: 1, handler: nil)
        XCTAssertEqual(desiredResponse, receivedResponse)
        
    }
    
    func testInsertingSingleFourStarsHotelIntoEmptyDataSource() {
        let expectation = self.expectation(description: "EqualIndexPaths")
        let desiredResponse = [IndexPath(row: 0, section: FOUR_STAR_SECTION)]
        var receivedResponse: [IndexPath]!
        datasource
            .update(with: [fourStarsHotel])
            .then({
                receivedResponse = $0
                expectation.fulfill()
            })
        waitForExpectations(timeout: 1, handler: nil)
        XCTAssertEqual(desiredResponse, receivedResponse)
        
    }
    
    func testInsertingSingleFiveStarsHotelIntoEmptyDataSource() {
        let expectation = self.expectation(description: "EqualIndexPaths")
        let desiredResponse = [IndexPath(row: 0, section: FIVE_STAR_SECTION)]
        var receivedResponse: [IndexPath]!
        datasource
            .update(with: [fiveStarsHotel])
            .then({
                receivedResponse = $0
                expectation.fulfill()
            })
        waitForExpectations(timeout: 1, handler: nil)
        XCTAssertEqual(desiredResponse, receivedResponse)
        
    }
    
    func testInsertingSingleTicketIntoEmptyDataSource() {
        let expectation = self.expectation(description: "EqualIndexPaths")
        let desiredResponse = [IndexPath(row: 0, section: TICKET_SECTION)]
        var receivedResponse: [IndexPath]!
        datasource
            .update(with: [ticket])
            .then({
                receivedResponse = $0
                expectation.fulfill()
            })
        waitForExpectations(timeout: 1, handler: nil)
        XCTAssertEqual(desiredResponse, receivedResponse)
        
    }
    
    func testInsertingSinglePackageIntoEmptyDataSource() {
        let expectation = self.expectation(description: "EqualIndexPaths")
        let desiredResponse = [IndexPath(row: 0, section: PACKAGE_SECTION)]
        var receivedResponse: [IndexPath]!
        datasource
            .update(with: [package])
            .then({
                receivedResponse = $0
                expectation.fulfill()
            })
        waitForExpectations(timeout: 1, handler: nil)
        XCTAssertEqual(desiredResponse, receivedResponse)
        
    }
    
    // MARK: - Single insertion on pre filled DataSource
    
    func testInsertingSingleOneStarHotelIntoPreFilledDataSource() {
        let expectation = self.expectation(description: "EqualIndexPaths")
        let desiredResponse = [IndexPath(row: 2, section: ONE_STAR_SECTION)]
        var receivedResponse: [IndexPath]!
        datasource
            .update(with: [oneStarHotel, oneStarHotel])
            .then{ _ in return self.datasource.update(with: [self.oneStarHotel]) }
            .then({
                receivedResponse = $0
                expectation.fulfill()
            })
        waitForExpectations(timeout: 1, handler: nil)
        XCTAssertEqual(desiredResponse, receivedResponse)
        
    }
    
    func testInsertingSingleTwoStarsHotelIntoPreFilledDataSource() {
        let expectation = self.expectation(description: "EqualIndexPaths")
        let desiredResponse = [IndexPath(row: 2, section: TWO_STAR_SECTION)]
        var receivedResponse: [IndexPath]!
        datasource
            .update(with: [twoStarsHotel, twoStarsHotel])
            .then{ _ in return self.datasource.update(with: [self.twoStarsHotel]) }
            .then({
                receivedResponse = $0
                expectation.fulfill()
            })
        waitForExpectations(timeout: 1, handler: nil)
        XCTAssertEqual(desiredResponse, receivedResponse)
        
    }
    
    func testInsertingSingleThreeStarsHotelIntoPreFilledDataSource() {
        let expectation = self.expectation(description: "EqualIndexPaths")
        let desiredResponse = [IndexPath(row: 2, section: THREE_STAR_SECTION)]
        var receivedResponse: [IndexPath]!
        datasource
            .update(with: [threeStarsHotel, threeStarsHotel])
            .then{ _ in return self.datasource.update(with: [self.threeStarsHotel]) }
            .then({
                receivedResponse = $0
                expectation.fulfill()
            })
        waitForExpectations(timeout: 1, handler: nil)
        XCTAssertEqual(desiredResponse, receivedResponse)
        
    }
    
    func testInsertingSingleFourStarsHotelIntoPreFilledDataSource() {
        let expectation = self.expectation(description: "EqualIndexPaths")
        let desiredResponse = [IndexPath(row: 2, section: FOUR_STAR_SECTION)]
        var receivedResponse: [IndexPath]!
        datasource
            .update(with: [fourStarsHotel, fourStarsHotel])
            .then{ _ in return self.datasource.update(with: [self.fourStarsHotel]) }
            .then({
                receivedResponse = $0
                expectation.fulfill()
            })
        waitForExpectations(timeout: 1, handler: nil)
        XCTAssertEqual(desiredResponse, receivedResponse)
        
    }
    
    func testInsertingSingleFiveStarsHotelIntoPreFilledDataSource() {
        let expectation = self.expectation(description: "EqualIndexPaths")
        let desiredResponse = [IndexPath(row: 2, section: FIVE_STAR_SECTION)]
        var receivedResponse: [IndexPath]!
        datasource
            .update(with: [fiveStarsHotel, fiveStarsHotel])
            .then{ _ in return self.datasource.update(with: [self.fiveStarsHotel]) }
            .then({
                receivedResponse = $0
                expectation.fulfill()
            })
        waitForExpectations(timeout: 1, handler: nil)
        XCTAssertEqual(desiredResponse, receivedResponse)
        
    }
    
    func testInsertingSingleTicketIntoPreFilledDataSource() {
        let expectation = self.expectation(description: "EqualIndexPaths")
        let desiredResponse = [IndexPath(row: 2, section: TICKET_SECTION)]
        var receivedResponse: [IndexPath]!
        datasource
            .update(with: [ticket, ticket])
            .then{ _ in return self.datasource.update(with: [self.ticket]) }
            .then({
                receivedResponse = $0
                expectation.fulfill()
            })
        waitForExpectations(timeout: 1, handler: nil)
        XCTAssertEqual(desiredResponse, receivedResponse)
        
    }
    
    func testInsertingSinglePackageIntoPreFilledDataSource() {
        let expectation = self.expectation(description: "EqualIndexPaths")
        let desiredResponse = [IndexPath(row: 2, section: PACKAGE_SECTION)]
        var receivedResponse: [IndexPath]!
        datasource
            .update(with: [package, package])
            .then{ _ in return self.datasource.update(with: [self.package]) }
            .then({
                receivedResponse = $0
                expectation.fulfill()
            })
        waitForExpectations(timeout: 1, handler: nil)
        XCTAssertEqual(desiredResponse, receivedResponse)
        
    }
    
    // MARK: - Multiple insertions on empty DataSource
    
    func testInsertingMultipleOneStarHotelsIntoEmptyDataSource() {
        let expectation = self.expectation(description: "EqualIndexPaths")
        let desiredResponse = [IndexPath(row: 0, section: ONE_STAR_SECTION), IndexPath(row: 1, section: ONE_STAR_SECTION)]
        var receivedResponse: [IndexPath]!
        datasource
            .update(with: [oneStarHotel, oneStarHotel])
            .then({
                receivedResponse = $0
                expectation.fulfill()
            })
        waitForExpectations(timeout: 1, handler: nil)
        XCTAssertEqual(desiredResponse, receivedResponse)
        
    }
    
    func testInsertingMultipleTwoStarsHotelsIntoEmptyDataSource() {
        let expectation = self.expectation(description: "EqualIndexPaths")
        let desiredResponse = [IndexPath(row: 0, section: TWO_STAR_SECTION), IndexPath(row: 1, section: TWO_STAR_SECTION)]
        var receivedResponse: [IndexPath]!
        datasource
            .update(with: [twoStarsHotel, twoStarsHotel])
            .then({
                receivedResponse = $0
                expectation.fulfill()
            })
        waitForExpectations(timeout: 1, handler: nil)
        XCTAssertEqual(desiredResponse, receivedResponse)
        
    }
    
    func testInsertingMultipleThreeStarsHotelsIntoEmptyDataSource() {
        let expectation = self.expectation(description: "EqualIndexPaths")
        let desiredResponse = [IndexPath(row: 0, section: THREE_STAR_SECTION), IndexPath(row: 1, section: THREE_STAR_SECTION)]
        var receivedResponse: [IndexPath]!
        datasource
            .update(with: [threeStarsHotel, threeStarsHotel])
            .then({
                receivedResponse = $0
                expectation.fulfill()
            })
        waitForExpectations(timeout: 1, handler: nil)
        XCTAssertEqual(desiredResponse, receivedResponse)
        
    }
    
    func testInsertingMultipleFourStarsHotelsIntoEmptyDataSource() {
        let expectation = self.expectation(description: "EqualIndexPaths")
        let desiredResponse = [IndexPath(row: 0, section: FOUR_STAR_SECTION), IndexPath(row: 1, section: FOUR_STAR_SECTION)]
        var receivedResponse: [IndexPath]!
        datasource
            .update(with: [fourStarsHotel, fourStarsHotel])
            .then({
                receivedResponse = $0
                expectation.fulfill()
            })
        waitForExpectations(timeout: 1, handler: nil)
        XCTAssertEqual(desiredResponse, receivedResponse)
        
    }
    
    func testInsertingMultipleFiveStarsHotelsIntoEmptyDataSource() {
        let expectation = self.expectation(description: "EqualIndexPaths")
        let desiredResponse = [IndexPath(row: 0, section: FIVE_STAR_SECTION), IndexPath(row: 1, section: FIVE_STAR_SECTION)]
        var receivedResponse: [IndexPath]!
        datasource
            .update(with: [fiveStarsHotel, fiveStarsHotel])
            .then({
                receivedResponse = $0
                expectation.fulfill()
            })
        waitForExpectations(timeout: 1, handler: nil)
        XCTAssertEqual(desiredResponse, receivedResponse)
        
    }
    
    func testInsertingMultipleTicketsIntoEmptyDataSource() {
        let expectation = self.expectation(description: "EqualIndexPaths")
        let desiredResponse = [IndexPath(row: 0, section: TICKET_SECTION), IndexPath(row: 1, section: TICKET_SECTION)]
        var receivedResponse: [IndexPath]!
        datasource
            .update(with: [ticket, ticket])
            .then({
                receivedResponse = $0
                expectation.fulfill()
            })
        waitForExpectations(timeout: 1, handler: nil)
        XCTAssertEqual(desiredResponse, receivedResponse)
        
    }
    
    func testInsertingMultiplePackagesIntoEmptyDataSource() {
        let expectation = self.expectation(description: "EqualIndexPaths")
        let desiredResponse = [IndexPath(row: 0, section: PACKAGE_SECTION), IndexPath(row: 1, section: PACKAGE_SECTION)]
        var receivedResponse: [IndexPath]!
        datasource
            .update(with: [package, package])
            .then({
                receivedResponse = $0
                expectation.fulfill()
            })
        waitForExpectations(timeout: 1, handler: nil)
        XCTAssertEqual(desiredResponse, receivedResponse)
        
    }
    
    // MARK: - Multiple insertions on pre filled DataSource
    
    func testInsertingMultipleOneStarHotelsIntoPreFilledDataSource() {
        let expectation = self.expectation(description: "EqualIndexPaths")
        let desiredResponse = [IndexPath(row: 1, section: ONE_STAR_SECTION), IndexPath(row: 2, section: ONE_STAR_SECTION)]
        var receivedResponse: [IndexPath]!
        datasource
            .update(with: [oneStarHotel])
            .then{ _ in self.datasource.update(with: [self.oneStarHotel, self.oneStarHotel]) }
            .then({
                receivedResponse = $0
                expectation.fulfill()
            })
        waitForExpectations(timeout: 1, handler: nil)
        XCTAssertEqual(desiredResponse, receivedResponse)
        
    }
    
    func testInsertingMultipleTwoStarsHotelsIntoPreFilledDataSource() {
        let expectation = self.expectation(description: "EqualIndexPaths")
        let desiredResponse = [IndexPath(row: 1, section: TWO_STAR_SECTION), IndexPath(row: 2, section: TWO_STAR_SECTION)]
        var receivedResponse: [IndexPath]!
        datasource
            .update(with: [twoStarsHotel])
            .then{ _ in self.datasource.update(with: [self.twoStarsHotel, self.twoStarsHotel]) }
            .then({
                receivedResponse = $0
                expectation.fulfill()
            })
        waitForExpectations(timeout: 1, handler: nil)
        XCTAssertEqual(desiredResponse, receivedResponse)
        
    }
    
    func testInsertingMultipleThreeStarsHotelsIntoPreFilledDataSource() {
        let expectation = self.expectation(description: "EqualIndexPaths")
        let desiredResponse = [IndexPath(row: 1, section: THREE_STAR_SECTION), IndexPath(row: 2, section: THREE_STAR_SECTION)]
        var receivedResponse: [IndexPath]!
        datasource
            .update(with: [threeStarsHotel])
            .then{ _ in self.datasource.update(with: [self.threeStarsHotel, self.threeStarsHotel]) }
            .then({
                receivedResponse = $0
                expectation.fulfill()
            })
        waitForExpectations(timeout: 1, handler: nil)
        XCTAssertEqual(desiredResponse, receivedResponse)
        
    }
    
    func testInsertingMultipleFourStarsHotelsIntoPreFilledDataSource() {
        let expectation = self.expectation(description: "EqualIndexPaths")
        let desiredResponse = [IndexPath(row: 1, section: FOUR_STAR_SECTION), IndexPath(row: 2, section: FOUR_STAR_SECTION)]
        var receivedResponse: [IndexPath]!
        datasource
            .update(with: [fourStarsHotel])
            .then{ _ in self.datasource.update(with: [self.fourStarsHotel, self.fourStarsHotel]) }
            .then({
                receivedResponse = $0
                expectation.fulfill()
            })
        waitForExpectations(timeout: 1, handler: nil)
        XCTAssertEqual(desiredResponse, receivedResponse)
        
    }
    
    func testInsertingMultipleFiveStarsHotelsIntoPreFilledDataSource() {
        let expectation = self.expectation(description: "EqualIndexPaths")
        let desiredResponse = [IndexPath(row: 1, section: FIVE_STAR_SECTION), IndexPath(row: 2, section: FIVE_STAR_SECTION)]
        var receivedResponse: [IndexPath]!
        datasource
            .update(with: [fiveStarsHotel])
            .then{ _ in self.datasource.update(with: [self.fiveStarsHotel, self.fiveStarsHotel]) }
            .then({
                receivedResponse = $0
                expectation.fulfill()
            })
        waitForExpectations(timeout: 1, handler: nil)
        XCTAssertEqual(desiredResponse, receivedResponse)
        
    }
    
    func testInsertingMultipleTicketsIntoPreFilledDataSource() {
        let expectation = self.expectation(description: "EqualIndexPaths")
        let desiredResponse = [IndexPath(row: 1, section: TICKET_SECTION), IndexPath(row: 2, section: TICKET_SECTION)]
        var receivedResponse: [IndexPath]!
        datasource
            .update(with: [ticket])
            .then{ _ in self.datasource.update(with: [self.ticket, self.ticket]) }
            .then({
                receivedResponse = $0
                expectation.fulfill()
            })
        waitForExpectations(timeout: 1, handler: nil)
        XCTAssertEqual(desiredResponse, receivedResponse)
        
    }
    
    func testInsertingMultiplePackagesIntoPreFilledDataSource() {
        let expectation = self.expectation(description: "EqualIndexPaths")
        let desiredResponse = [IndexPath(row: 1, section: PACKAGE_SECTION), IndexPath(row: 2, section: PACKAGE_SECTION)]
        var receivedResponse: [IndexPath]!
        datasource
            .update(with: [package])
            .then{ _ in self.datasource.update(with: [self.package, self.package]) }
            .then({
                receivedResponse = $0
                expectation.fulfill()
            })
        waitForExpectations(timeout: 1, handler: nil)
        XCTAssertEqual(desiredResponse, receivedResponse)
        
    }
    
    // MARK: Mixed elements insert
    
    func testInsertingMultipleMixedElementsIntoDataSource() {
        let expectation = self.expectation(description: "EqualIndexPaths")
        let desiredResponse = [
            IndexPath(row: 0, section: PACKAGE_SECTION),
            IndexPath(row: 0, section: TICKET_SECTION),
            IndexPath(row: 1, section: TICKET_SECTION),
            IndexPath(row: 0, section: ONE_STAR_SECTION),
            IndexPath(row: 0, section: THREE_STAR_SECTION),
            IndexPath(row: 1, section: ONE_STAR_SECTION),
            IndexPath(row: 2, section: TICKET_SECTION)]
        var receivedResponse: [IndexPath]!
        datasource
            .update(with: [package, ticket, ticket, oneStarHotel, threeStarsHotel, oneStarHotel, ticket])
            .then({
                receivedResponse = $0
                expectation.fulfill()
            })
        waitForExpectations(timeout: 1, handler: nil)
        XCTAssertEqual(desiredResponse, receivedResponse)
        
    }

}

func loadSingleResultJSON(filename fileName: String) -> SearchResultElement? {
    if let url = Bundle(for: SearchResultDataSourceTests.self).url(forResource: fileName, withExtension: "json") {
        do {
            let data = try Data(contentsOf: url)
            let decoder = JSONDecoder()
            let result = try decoder.decode(SearchResultElement.self, from: data)
            return result
        } catch {
            print(error)
        }
    }
    return nil
}

//
//  ServicesTest.swift
//  Challenge Alpha New VersionTests
//
//  Created by Rafael Oliveira on 15/04/23.
//

import XCTest
import HUGraphQL
@testable import Challenge_Alpha_New_Version

class ServicesTest: XCTestCase {
    
    func testPerformPackageSearch() {
            let query = "Rio de Janeiro"
        
            let expectation = self.expectation(description: "Search Package")
       
        Services.servicesNetwork.performPackageSearch(query: query, pagination: nil) { result in
                XCTAssertNotNil(result)
                XCTAssertGreaterThan(result.count, 0)
                expectation.fulfill()
            }
            waitForExpectations(timeout: 5, handler: nil)
        }
    
    func testPerformHotelSearch() {
        let expectation = XCTestExpectation(description: "Search hotels")
        let query = "Seattle"
        Services.servicesNetwork.performHotelSearch(query: query) { results in
            XCTAssertGreaterThan(results.count, 0)
            expectation.fulfill()
        }
        wait(for: [expectation], timeout: 10)
    }
}

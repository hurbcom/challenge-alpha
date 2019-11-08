//
//  HurbAlphaTests.swift
//  HurbAlphaTests
//
//  Created by Rodrigo Bukowitz on 04/11/19.
//  Copyright © 2019 Rodrigo Bukowitz. All rights reserved.
//

import XCTest
@testable import HurbAlpha

class HurbAlphaTests: XCTestCase {
    
    override func setUp() {
        // Put setup code here. This method is called before the invocation of each test method in the class.
    }
    
    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
    }
    
    /// Test the get request from API with the key and query requested
    ///
    /// Needs internet connection to happen 
    func testGetFromAPI() {
        // This is an example of a functional test case.
        // Use XCTAssert and related functions to verify your tests produce the correct results.
        let expectation = self.expectation(description: "get request")
        
        netManager.getOffers(place: "buzios", page: 1) { (offers, error) in
            if error != nil {
                XCTFail("Couldnt make the request")
            } else {
                guard let _ = offers else {
                    XCTFail("Wrong type of Data ")
                    return
                }
                expectation.fulfill()
            }
        }
        // timeout fro the request
        self.waitForExpectations(timeout: 10.0)
    }
    
    func testPerformanceExample() {
        // This is an example of a performance test case.
        self.measure {
            // Put the code you want to measure the time of here.
        }
    }
    
}

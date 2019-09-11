//
//  HurbAlphaTests.swift
//  HurbAlphaTests
//
//  Created by Julia Rocha on 03/09/19.
//  Copyright © 2019 Julia Rocha. All rights reserved.
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
    
    func testDAOPopulatesHotel() {
        
        struct Requester: DAORequester {
            
            var expectation = XCTestExpectation(description: "DAO fetch URL")
            
            func finishedLoading() {
//                guard DAO.instance.hotel?.pagination.count == DAO.instance.loadedHotels.count else {
//                    XCTFail()
//                    return
//                }
                expectation.fulfill()
            }
            
            func finishedLoading(with Error: HotelReadingError) {
                XCTFail()
            }
            
            
        }
        
        let requester = Requester()
        DAO.instance.jsonDataRequest(page: 1, requester: requester)
        wait(for: [requester.expectation], timeout: 3)
    }
    
    func throwError() throws {
        struct MyError: Error {}
        throw MyError()
    }

    func testExample() {
//        XCTAssertEqual(2 + 2, 4)
//        let a: Int? = nil
//        XCTAssertNotNil(a)
//        XCTAssertThrowsError(try throwError())
        let a = 2 + 2
        let b = 5
        guard a == b else {
            XCTFail()
            return
        }
    }

    func testPerformanceExample() {
        // This is an example of a performance test case.
        self.measure {
            // Put the code you want to measure the time of here.
        }
    }

}

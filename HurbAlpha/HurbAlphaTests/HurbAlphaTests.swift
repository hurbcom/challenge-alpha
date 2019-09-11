//
//  HurbAlphaTests.swift
//  HurbAlphaTests
//
//  Created by Julia Rocha on 03/09/19.
//  Copyright © 2019 Julia Rocha. All rights reserved.
//

import XCTest
import UIKit
@testable import HurbAlpha

class HurbAlphaTests: XCTestCase {

    override func setUp() {
        // Put setup code here. This method is called before the invocation of each test method in the class.
    }

    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
    }
    
    // Struct that emulates the DAO Requester Protocol for testing
    struct TestRequester: DAORequester {
        var expectation = XCTestExpectation(description: "DAO fetch URL")
        func finishedLoading() {
            expectation.fulfill()
        }
        func finishedLoading(with Error: HotelReadingError) {
            XCTFail()
        }
    }
    
    // Testing for JSON reader success
    func testDAOPopulatesHotel() {
        let requester = TestRequester()
        DAO.instance.jsonDataRequest(page: 1, requester: requester)
        wait(for: [requester.expectation], timeout: 3)
    }

    // Testing for image URL convertion
    func testConvertImageURLToImageFromURL() {
        ImagesManager.instance.onImageView = UIImageView()
        ImagesManager.instance.tryConvertionFromURL(from: "https://media.omnibees.com/Images/6533/Property/229896.jpg")
        guard let _ = ImagesManager.instance.onImageView else {
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

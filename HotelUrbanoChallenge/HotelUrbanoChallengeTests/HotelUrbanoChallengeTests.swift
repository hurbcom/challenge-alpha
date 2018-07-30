//
//  HotelUrbanoChallengeTests.swift
//  HotelUrbanoChallengeTests
//
//  Created by maciosdev on 30/07/2018.
//  Copyright © 2018 Ronilson. All rights reserved.
//

import XCTest
@testable import HotelUrbanoChallenge

class HotelUrbanoChallengeTests: XCTestCase {
    
    let service = HotelUrbanoService()
    var viewControllerUnderTest: HotelListViewController!
    
    override func setUp() {
        super.setUp()
        
        self.viewControllerUnderTest = HotelListViewController(nibName: "HotelListViewController", bundle: nil)
        
        self.viewControllerUnderTest.loadView()
        self.viewControllerUnderTest.viewDidLoad()
    }
    
    
    func testForItemsInResult() {
        let exp = self.expectation(description: "Wait for getHotels completion")
        var error: ServiceError?
        var hotels: [Results] = []
        
        service.getHotels( success: { result in
            hotels = result.results!
            exp.fulfill()
            
        }, fail: { fail in
            error = fail
            exp.fulfill()
        })
        
        waitForExpectations(timeout: 5, handler: nil)
        XCTAssertNil(error)
        XCTAssertNotNil(hotels)
        XCTAssert(hotels.count > 0, "hotel results")
    }

    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
        super.tearDown()
    }
    
    func testHasATableView() {
        XCTAssertNotNil(viewControllerUnderTest.tableView)
    }
    
    func testTableViewHasDelegate() {
        XCTAssertNotNil(viewControllerUnderTest.tableView.delegate)
    }
    
    func testTableViewConfromsToTableViewDelegateProtocol() {
        XCTAssertTrue(viewControllerUnderTest.conforms(to: UITableViewDelegate.self))
    }
    
    func testTableViewHasDataSource() {
        XCTAssertNotNil(viewControllerUnderTest.tableView.dataSource)
    }
    
    func testTableViewConformsToTableViewDataSourceProtocol() {
        XCTAssertTrue(viewControllerUnderTest.conforms(to: UITableViewDataSource.self))
        XCTAssertTrue(viewControllerUnderTest.responds(to: #selector(viewControllerUnderTest.numberOfSections(in:))))
        XCTAssertTrue(viewControllerUnderTest.responds(to: #selector(viewControllerUnderTest.tableView(_:numberOfRowsInSection:))))
        XCTAssertTrue(viewControllerUnderTest.responds(to: #selector(viewControllerUnderTest.tableView(_:cellForRowAt:))))
    }    
}

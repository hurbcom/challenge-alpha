//
//  MoneyTests.swift
//  HotelsTests
//
//  Created by Adolfho Athyla on 02/09/2018.
//  Copyright © 2018 a7hyla. All rights reserved.
//

import XCTest
@testable import Hotels

class MoneyTests: XCTestCase {
    
    override func setUp() {
        super.setUp()
        // Put setup code here. This method is called before the invocation of each test method in the class.
    }
    
    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
        super.tearDown()
    }
    
    func testPerformanceExample() {
        // This is an example of a performance test case.
        self.measure {
            // Put the code you want to measure the time of here.
        }
    }
    
    func testDoubleToMoney() {
        let money = 345.89.getMoneyValue()
        XCTAssertEqual(money, "345,89")
    }
    
}

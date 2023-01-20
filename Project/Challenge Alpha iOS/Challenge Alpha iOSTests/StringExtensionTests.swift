//
//  StringExtensionTests.swift
//  Challenge Alpha iOSTests
//
//  Created by Yuri Strack on 20/01/23.
//

import XCTest
@testable import Challenge_Alpha_iOS

final class StringExtensionTests: XCTestCase {

    func testTransformToMonthAndYear() throws {
        let dateString = "2023-01-20T00:00:00"
        let expectedResult = "jan/23"
        let monthAndYear = dateString.monthAndYearString()
        
        XCTAssertEqual(monthAndYear, expectedResult)
    }
    
    func testFailToTransformToMonthAndYear() throws {
        let dateString = "2023-01-20"
        let expectedResult = ""
        let monthAndYear = dateString.monthAndYearString()
        
        XCTAssertEqual(monthAndYear, expectedResult)
    }
    
    func testAddFontToHTMLString() throws {
        let string = "Oi"
        let htmlString = string.addFontToHTMLString()
        
        XCTAssertTrue(htmlString.contains("</span>"))
        XCTAssertTrue(htmlString.contains("Oi"))
    }
}

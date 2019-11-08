//
//  HurbAlphaUITests.swift
//  HurbAlphaUITests
//
//  Created by Rodrigo Bukowitz on 04/11/19.
//  Copyright © 2019 Rodrigo Bukowitz. All rights reserved.
//

import XCTest

/// this class handles the UI test coverage aroud the aplication
class HurbAlphaUITests: XCTestCase {
    /// the instance of the appliction to be tested
    var app: XCUIApplication!
    
    /// set up for the UITssts
    override func setUp() {

        // In UI tests it is usually best to stop immediately when a failure occurs.
        continueAfterFailure = false
        
        app = XCUIApplication()
        app.launch()

        // In UI tests it’s important to set the initial state - such as interface orientation - required for your tests before they run. The setUp method is a good place to do this
    }

    override func tearDown() {
        // there are nothing to tearDown
    }
    
    /// This is a simple UITest to see all tableView behaviors
    func testFeedFlow() {
        let firstElement = app.tables.element
        self.waitForElementToAppear(element: firstElement)
        app.swipeUp()
        app.swipeUp()
        app.swipeDown()
        app.swipeDown()
        app.swipeDown()
        // stretch the view
        app.swipeDown()
        app.swipeDown()
        // follow the normal course
        app.swipeUp()
        app.swipeUp()
        // Use recording to get started writing UI tests.
        // Use XCTAssert and related functions to verify your tests produce the correct results.
    }

    func testLaunchPerformance() {
        if #available(macOS 10.15, iOS 13.0, tvOS 13.0, *) {
            // This measures how long it takes to launch your application.
            measure(metrics: [XCTOSSignpostMetric.applicationLaunch]) {
                XCUIApplication().launch()
            }
        }
    }
    
    // from (https://stackoverflow.com/a/33855219/12138335)
    func waitForElementToAppear(element: XCUIElement,
                                timeout: TimeInterval = 5,
                                file: String = #file,
                                line: UInt = #line) {
        
        let existsPredicate = NSPredicate(format: "exists == true")

        expectation(for: existsPredicate,
                    evaluatedWith: element, handler: nil)

        waitForExpectations(timeout: timeout) { (error) -> Void in
            if (error != nil) {
                let message = "Failed to find \(element) after \(timeout) seconds."
                self.recordFailure(withDescription: message, inFile: file, atLine: Int(line), expected: true)
            }
        }
    }
}

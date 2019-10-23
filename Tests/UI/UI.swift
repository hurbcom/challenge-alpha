//
//  UITests.swift
//  UITests
//
//  Created by Theo Mendes on 13/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

import XCTest

class UITests: XCTestCase {
    var app: XCUIApplication!

    override func setUp() {
        continueAfterFailure = false
        app = XCUIApplication()
        app.launch()
    }

    override func tearDown() {}
    
    // Since the app has a simple UI, we just need to test the vertical and horizontal scroll
    func testFeed() {
        let sectionOneFirst = app.tables["feedTableView"].cells["Star[1, 0]"].images["HotelImage[0, 0]"]
        self.waitForElementToAppear(element: sectionOneFirst)
        sectionOneFirst.swipeLeft()
        sectionOneFirst.swipeUp()
        app.tables["feedTableView"].cells["Star[2, 0]"].swipeLeft()
        app.tables["feedTableView"].cells["Star[2, 0]"].swipeLeft()
        app.tables["feedTableView"].cells["Star[2, 0]"].swipeRight()
        app.tables["feedTableView"].cells["Star[2, 0]"].swipeRight()
        app.tables["feedTableView"].cells["Star[2, 0]"].swipeDown()
        sectionOneFirst.swipeRight()
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

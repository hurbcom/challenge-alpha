//___FILEHEADER___

import XCTest

class ___FILEBASENAMEASIDENTIFIER___: XCTestCase {
    
    var app: XCUIApplication!
    
    override func setUp() {
        continueAfterFailure = false
        app = XCUIApplication()
        app.launch()
    }

    override func tearDown() {}
    
    /// Tests the scroll in the feed and in feed cells
    func feedFlowTest() {
        let firstCell = app.tables["feedTableView"].cells["Experience[0, 0]"]
        self.waitForElementToAppear(element: firstCell)
        firstCell.swipeLeft()
        firstCell.swipeUp()
        app.tables["feedTableView"].cells["Experience[1, 0]"].swipeLeft()
        app.tables["feedTableView"].cells["Experience[1, 0]"].swipeLeft()
        app.tables["feedTableView"].cells["Experience[1, 0]"].swipeUp()
        app.tables["feedTableView"].cells["Experience[2, 0]"].swipeLeft()
    }

    func testLaunchPerformance() {
        if #available(macOS 10.15, iOS 13.0, tvOS 13.0, *) {
            // This measures how long it takes to launch your application.
            measure(metrics: [XCTOSSignpostMetric.applicationLaunch]) {
                XCUIApplication().launch()
            }
        }
    }
    
    // Extracted from (https://stackoverflow.com/a/33855219/12138335)
    func waitForElementToAppear(element: XCUIElement,
                                timeout: TimeInterval = 10,
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

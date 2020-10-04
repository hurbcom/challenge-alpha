//
//  Hoteis_Buzios_UITests.swift
//  Hoteis Buzios UITests
//
//  Created by Andre Dias on 04/10/20.
//  Copyright © 2020 Andre Dias. All rights reserved.
//

import XCTest

class Hoteis_Buzios_UITests: XCTestCase {

    override func setUpWithError() throws {
        continueAfterFailure = false
    }

    // Testa a existencia dos elementos de UI, como tableview, celula no indexpath (0,0) e a imagem da celula.
    func testHome() throws {
        let app = XCUIApplication()
        app.launch()
        let homeTableView = app.tables.matching(identifier: "HomeTableView")
        let firstCell = homeTableView.cells.element(matching: .cell, identifier: "HotelTableViewCellAtIndexPath:_0_0")
        let existencePredicate = NSPredicate(format: "exists == 1")
        let expectationEval = expectation(for: existencePredicate, evaluatedWith: firstCell, handler: nil)
        let mobWaiter = XCTWaiter.wait(for: [expectationEval], timeout: 10.0)
        
        XCTAssert(XCTWaiter.Result.completed == mobWaiter, "Test Case Failed.")
       
        let callImageView = app.otherElements.containing(.image, identifier: "hotelImage").firstMatch
        XCTAssertTrue(callImageView.exists, "Image view not exist.")
        XCTAssertTrue(firstCell.exists, "Tableviewcell não existe.")
    }

    func testLaunchPerformance() throws {
        if #available(macOS 10.15, iOS 13.0, tvOS 13.0, *) {
            measure(metrics: [XCTOSSignpostMetric.applicationLaunch]) {
                XCUIApplication().launch()
            }
        }
    }
}

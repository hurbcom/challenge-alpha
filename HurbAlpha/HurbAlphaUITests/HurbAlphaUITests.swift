//
//  HurbAlphaUITests.swift
//  HurbAlphaUITests
//
//  Created by Julia Rocha on 03/09/19.
//  Copyright © 2019 Julia Rocha. All rights reserved.
//

import XCTest

class HurbAlphaUITests: XCTestCase {

    override func setUp() {
        // Put setup code here. This method is called before the invocation of each test method in the class.

        // In UI tests it is usually best to stop immediately when a failure occurs.
        continueAfterFailure = false

        // UI tests must launch the application that they test. Doing this in setup will make sure it happens for each test method.
        XCUIApplication().launch()

        // In UI tests it’s important to set the initial state - such as interface orientation - required for your tests before they run. The setUp method is a good place to do this.
    }

    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
    }

    func testExample() {
        // Use recording to get started writing UI tests.
        // Use XCTAssert and related functions to verify your tests produce the correct results.
        
    }
    
    
    
    func testAddToFavorites() {
        let app = XCUIApplication()
        app.tables/*@START_MENU_TOKEN@*/.staticTexts["BRL 348,19"]/*[[".cells.staticTexts[\"BRL 348,19\"]",".staticTexts[\"BRL 348,19\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.tap()
        app.buttons["favoriteFlag"].tap()
        app.buttons["Voltar"].tap()
        app.tabBars.children(matching: .button).element(boundBy: 1).tap()
        if app.tables["Empty list"].exists {
            XCTFail()
        }
        
    }
    
    

}

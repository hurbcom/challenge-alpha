//
//  HotelsUITests.swift
//  HotelsUITests
//
//  Created by Adolfho Athyla on 24/08/2018.
//  Copyright © 2018 a7hyla. All rights reserved.
//

import XCTest

class HotelsUITests: XCTestCase {
        
    override func setUp() {
        super.setUp()
        
        // Put setup code here. This method is called before the invocation of each test method in the class.
        
        // In UI tests it is usually best to stop immediately when a failure occurs.
        continueAfterFailure = false
        // UI tests must launch the application that they test. Doing this in setup will make sure it happens for each test method.
        XCUIApplication().launch()

        // In UI tests it’s important to set the initial state - such as interface orientation - required for your tests before they run. The setUp method is a good place to do this.
    }
    
    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
        super.tearDown()
    }
    
    func testExample() {
        // Use recording to get started writing UI tests.
        // Use XCTAssert and related functions to verify your tests produce the correct results.
    }
    
    func testGeneral() {
        
        let app = XCUIApplication()
        
        sleep(10)
        app.tables/*@START_MENU_TOKEN@*/.staticTexts["Pacote Fortaleza - 2019"]/*[[".cells.staticTexts[\"Pacote Fortaleza - 2019\"]",".staticTexts[\"Pacote Fortaleza - 2019\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.swipeLeft()
        
        
        app.tables/*@START_MENU_TOKEN@*/.staticTexts["Hotel Oásis Atlântico"]/*[[".cells.staticTexts[\"Hotel Oásis Atlântico\"]",".staticTexts[\"Hotel Oásis Atlântico\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.swipeUp()
        
        let collectionViewsQuery = app.collectionViews
        collectionViewsQuery/*@START_MENU_TOKEN@*/.staticTexts["São Paulo"]/*[[".cells.staticTexts[\"São Paulo\"]",".staticTexts[\"São Paulo\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.tap()
        
        sleep(10)
        collectionViewsQuery/*@START_MENU_TOKEN@*/.staticTexts["Santa Catarina"]/*[[".cells.staticTexts[\"Santa Catarina\"]",".staticTexts[\"Santa Catarina\"]"],[[[-1,1],[-1,0]]],[0]]@END_MENU_TOKEN@*/.swipeLeft()
        
    }
    
}

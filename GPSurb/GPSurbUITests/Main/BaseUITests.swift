//
//  BaseUITests.swift
//  GPSurbUITests
//
//  Created by Gilson Santos on 25/10/19.
//  Copyright © 2019 Gilson Santos. All rights reserved.
//

import XCTest

class BaseUITests: XCTestCase {
     // MARK: - CONSTANTS -
    let app = XCUIApplication()
    let homeViewIdentifier = "home-view-identifier"
    let homeCellIdentifier = "home-cell-Rio de Janeiro - Brasil"
    let hotelAndPackageViewIdentifier = "hotelAndPackage-view-identifier"
    let loadingViewIdentifier = "loading-view-identifier"
    let tabBarHomeIdentifier = "Home-identifier"
    let tabBarFavoriteIdentifier = "Favoritos-identifier"
    let destinationCellIdentifier = "destination-cell"
    let hotelCellIdentifier = "hotel-cell-identifier"
    let packageCellIdentifier = "package-cell-identifier"
    let segmentControlIdentifier = "segment-identifier"
    let tableViewListIdentifier = "tableViewList-identifier"
    let errorViewIdentifier = "errorView-identifier"
    let detailViewIdentifier = "detail-view-identifier"
    let favoriteViewIdentifier = "favorite-view-identifier"
    let titleHomeScreen = "GPSurb"
    
}

// MARK: - SETUP AND TEARDOWN -
extension BaseUITests {
    override func setUp() {
        super.setUp()
        self.continueAfterFailure = false
    }
    
    override func tearDown() {
        self.app.terminate()
        super.tearDown()
    }
}


// MARK: - MANAGER TEST STEPS -
extension BaseUITests {
    func afterLaunchingTheApp() {
        XCTContext.runActivity(named: "Após iniciar o aplicativo") { _ in
            self.app.launch()
        }
    }
}

// MARK: - VIEW TEST STEPS -
extension BaseUITests {
    func iSeeTheScreen(identifier: String) {
        XCTContext.runActivity(named: "Vejo a tela \(identifier)") { _ in
            let resultView = self.app.otherElements[identifier]
            let viewExists = resultView.waitForExistence(timeout: 10)
            XCTAssert(viewExists)
        }
    }
    
    func iTouchTheView(identifier: String) {
        XCTContext.runActivity(named: "") { _ in
            let resultView = self.app.otherElements[identifier]
            resultView.tap()
        }
    }
}

// MARK: - BUTTON TEST STEPS -
extension BaseUITests {
    func iSeeTheButton(identifier: String) {
        XCTContext.runActivity(named: "Vejo o botão \(identifier)") { _ in
            XCTAssert(self.app.buttons[identifier].exists)
        }
    }
    
    func iTouchTheButton(identifier: String) {
        XCTContext.runActivity(named: "Toco no botão \(identifier)") { _ in
            self.app.buttons[identifier].tap()
        }
    }
}

// MARK: - TABLEVIEW TEST STEPS -
extension BaseUITests {
    func iSeeTheCell(identifier: String) {
        XCTContext.runActivity(named: "Vejo a célula \(identifier)") { _ in
            let tablesQuery = self.app.tables
            let resultCell = tablesQuery.cells[identifier]
            let viewExists = resultCell.waitForExistence(timeout: 10)
            XCTAssert(viewExists)
        }
    }
    
    func iSelectTheCell(identifier: String) {
        XCTContext.runActivity(named: "Seleciono a célula \(identifier)") { _ in
            self.app.tables.cells[identifier].tap()
        }
    }
}

// MARK: - NAVIGATION CONTROLLER STEPS -
extension BaseUITests {
    func iSeeScreenTitle(title: String) {
        XCTContext.runActivity(named: "Vejo o titúlo \(title)") { _ in
            XCTAssert(self.app.otherElements[title].exists)
        }
    }
}

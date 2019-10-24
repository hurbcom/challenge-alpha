//
//  HomePresenterTests.swift
//  GPSurbTests
//
//  Created by Gilson Santos on 24/10/19.
//  Copyright © 2019 Gilson Santos. All rights reserved.
//

import XCTest
@testable import GPSurb

class HomePresenterTests: XCTestCase {
    
    func testSuccess() {
        let service = HomeServiceMock(fileName: "Success")
        let controller = HomeViewControllerMock()
        let presenter = HomePresenter(viewDelegate: controller, service: service)
        presenter.getBestDestinations()
        XCTAssertEqual(controller.state, State.success)
        XCTAssertNotNil(controller.viewData)
        XCTAssertEqual(controller.viewData?.bestDestinations.count, 6)
    }
    
    func testError() {
        let service = HomeServiceMock(fileName: "Error")
        let controller = HomeViewControllerMock()
        let presenter = HomePresenter(viewDelegate: controller, service: service)
        presenter.getBestDestinations()
        XCTAssertEqual(controller.state, State.error)
    }
}

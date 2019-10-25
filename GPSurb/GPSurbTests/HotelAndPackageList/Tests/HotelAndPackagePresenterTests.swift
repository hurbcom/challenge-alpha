//
//  HotelAndPackagePresenterTests.swift
//  GPSurbTests
//
//  Created by Gilson Santos on 25/10/19.
//  Copyright © 2019 Gilson Santos. All rights reserved.
//

import XCTest

class HotelAndPackagePresenterTests: HotelAndPackageBaseTests {
    
    func testSuccessHotel() {
        let service = HotelAndPackageServiceMock(fileName: "HotelSuccess")
        let controller = HotelAndPackageViewControllerMock()
        let presenter = HotelAndPackageListPresenter(viewDelegate: controller, service: service)
        presenter.getOffers(query: self.validQuery, filter: self.filterHotel)
        XCTAssertNotNil(controller.hotelViewData)
        XCTAssertEqual(controller.hotelViewData?.currentPage, 1)
        XCTAssertEqual(controller.hotelViewData?.list.count, 20)
        XCTAssertEqual(controller.state, State.success)
    }
    
    func testInvalidQueryHotel() {
        let service = HotelAndPackageServiceMock(fileName: "NotFound")
        let controller = HotelAndPackageViewControllerMock()
        let presenter = HotelAndPackageListPresenter(viewDelegate: controller, service: service)
        presenter.getOffers(query: self.invalidQuery, filter: self.filterHotel)
        XCTAssertEqual(controller.state, State.error)
    }
    
    func testInvalidPageHotel() {
        let service = HotelAndPackageServiceMock(fileName: "NotFound")
        let controller = HotelAndPackageViewControllerMock()
        let presenter = HotelAndPackageListPresenter(viewDelegate: controller, service: service)
        presenter.getOffers(for: self.invalidPage, query: self.validQuery, filter: self.filterHotel)
        XCTAssertEqual(controller.state, State.error)
    }
    
    func testNextPageHotel() {
        let service = HotelAndPackageServiceMock(fileName: "HotelSuccess")
        let controller = HotelAndPackageViewControllerMock()
        let presenter = HotelAndPackageListPresenter(viewDelegate: controller, service: service)
        presenter.getOffers(query: self.validQuery, filter: self.filterHotel)
        presenter.getOffers(for: 2, query: self.validQuery, filter: self.filterHotel)
        XCTAssertNotNil(controller.hotelViewData)
        XCTAssertEqual(controller.hotelViewData?.list.count, 20)
        XCTAssertNotNil(controller.resultViewData)
        XCTAssertEqual(controller.resultViewData?.count, 20)
        XCTAssertEqual(controller.state, State.success)
    }
    
    func testEmptyHotel() {
        let service = HotelAndPackageServiceMock(fileName: "HotelEmpty")
        let controller = HotelAndPackageViewControllerMock()
        let presenter = HotelAndPackageListPresenter(viewDelegate: controller, service: service)
        presenter.getOffers(query: self.invalidQuery, filter: self.filterHotel)
        XCTAssertEqual(controller.state, State.error)
    }
    
    func testErrorJsonHotel() {
        let service = HotelAndPackageServiceMock(fileName: "HotelError")
        let controller = HotelAndPackageViewControllerMock()
        let presenter = HotelAndPackageListPresenter(viewDelegate: controller, service: service)
        presenter.getOffers(query: self.invalidQuery, filter: self.filterHotel)
        XCTAssertEqual(controller.state, State.error)
    }
    
    // PACKAGE
    func testSuccessPackage() {
        let service = HotelAndPackageServiceMock(fileName: "PackageSuccess")
        let controller = HotelAndPackageViewControllerMock()
        let presenter = HotelAndPackageListPresenter(viewDelegate: controller, service: service)
        presenter.getOffers(query: self.validQuery, filter: self.filterPackage)
        XCTAssertNotNil(controller.hotelViewData)
        XCTAssertEqual(controller.hotelViewData?.currentPage, 1)
        XCTAssertEqual(controller.hotelViewData?.list.count, 20)
        XCTAssertEqual(controller.state, State.success)
    }
    
    func testInvalidQueryPackage() {
        let service = HotelAndPackageServiceMock(fileName: "NotFound")
        let controller = HotelAndPackageViewControllerMock()
        let presenter = HotelAndPackageListPresenter(viewDelegate: controller, service: service)
        presenter.getOffers(query: self.invalidQuery, filter: self.filterPackage)
        XCTAssertEqual(controller.state, State.error)
    }
    
    func testInvalidPagePackage() {
        let service = HotelAndPackageServiceMock(fileName: "NotFound")
        let controller = HotelAndPackageViewControllerMock()
        let presenter = HotelAndPackageListPresenter(viewDelegate: controller, service: service)
        presenter.getOffers(for: self.invalidPage, query: self.validQuery, filter: self.filterPackage)
        XCTAssertEqual(controller.state, State.error)
    }
    
    func testNextPagePackage() {
        let service = HotelAndPackageServiceMock(fileName: "PackageSuccess")
        let controller = HotelAndPackageViewControllerMock()
        let presenter = HotelAndPackageListPresenter(viewDelegate: controller, service: service)
        presenter.getOffers(query: self.validQuery, filter: self.filterHotel)
        presenter.getOffers(for: 2, query: self.validQuery, filter: self.filterPackage)
        XCTAssertNotNil(controller.hotelViewData)
        XCTAssertEqual(controller.hotelViewData?.list.count, 20)
        XCTAssertNotNil(controller.resultViewData)
        XCTAssertEqual(controller.resultViewData?.count, 20)
        XCTAssertEqual(controller.state, State.success)
    }
    
    func testEmptyPackage() {
        let service = HotelAndPackageServiceMock(fileName: "PackageEmpty")
        let controller = HotelAndPackageViewControllerMock()
        let presenter = HotelAndPackageListPresenter(viewDelegate: controller, service: service)
        presenter.getOffers(query: self.invalidQuery, filter: self.filterPackage)
        XCTAssertEqual(controller.state, State.error)
    }
    
    func testErrorJsonPackage() {
        let service = HotelAndPackageServiceMock(fileName: "PackageError")
        let controller = HotelAndPackageViewControllerMock()
        let presenter = HotelAndPackageListPresenter(viewDelegate: controller, service: service)
        presenter.getOffers(query: self.invalidQuery, filter: self.filterPackage)
        XCTAssertEqual(controller.state, State.error)
    }
}

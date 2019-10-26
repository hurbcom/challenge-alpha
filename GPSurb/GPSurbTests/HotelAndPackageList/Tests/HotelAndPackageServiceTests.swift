//
//  HotelAndPackageServiceTests.swift
//  GPSurbTests
//
//  Created by Gilson Santos on 25/10/19.
//  Copyright © 2019 Gilson Santos. All rights reserved.
//

import XCTest
@testable import GPSurb

class HotelAndPackageServiceTests: HotelAndPackageBaseTests {
    
    func testSuccessHotel() {
        let expectation = self.expectation(description: "hotelSuccess")
        self.hotelPackageService.getHotelAndPackage(query: self.validQuery, filter: self.filterHotel, page: self.validPage) { (result) in
            switch result {
            case .success(let resultModel):
                if let result = resultModel.results {
                    self.resultCount = result.count
                }
            case .failure(_):
                break
            }
            expectation.fulfill()
        }
        self.waitForExpectations(timeout: 7, handler: nil)
        XCTAssertTrue(self.resultCount > 0)
    }
    
    func testInvalidQueryHotel() {
        let expectation = self.expectation(description: "hotelInvalidQuery")
        self.hotelPackageService.getHotelAndPackage(query: self.invalidQuery, filter: self.filterHotel, page: self.validPage) { (result) in
            switch result {
            case .success(_):
                break
            case .failure(_):
                self.serviceError = true
            }
            expectation.fulfill()
        }
        self.waitForExpectations(timeout: 7, handler: nil)
        XCTAssertTrue(self.serviceError)
    }
    
    func testInvalidPageHotel() {
        let expectation = self.expectation(description: "hotelInvalidPage")
        self.hotelPackageService.getHotelAndPackage(query: self.invalidQuery, filter: self.filterHotel, page: self.invalidPage) { (result) in
            switch result {
            case .success(_):
                break
            case .failure(_):
                self.serviceError = true
            }
            expectation.fulfill()
        }
        self.waitForExpectations(timeout: 7, handler: nil)
        XCTAssertTrue(self.serviceError)
    }
    
    // PACKAGE
    
    func testSuccessPackage() {
        let expectation = self.expectation(description: "packageSuccess")
        self.hotelPackageService.getHotelAndPackage(query: self.validQuery, filter: self.filterPackage, page: self.validPage) { (result) in
            switch result {
            case .success(let resultModel):
                if let result = resultModel.results {
                    self.resultCount = result.count
                }
            case .failure(_):
                break
            }
            expectation.fulfill()
        }
        self.waitForExpectations(timeout: 7, handler: nil)
        XCTAssertTrue(self.resultCount > 0)
    }
    
    func testInvalidQueryPackage() {
        let expectation = self.expectation(description: "packageInvalidQuery")
        self.hotelPackageService.getHotelAndPackage(query: self.invalidQuery, filter: self.filterPackage, page: self.validPage) { (result) in
            switch result {
            case .success(_):
                break
            case .failure(_):
                self.serviceError = true
            }
            expectation.fulfill()
        }
        self.waitForExpectations(timeout: 7, handler: nil)
        XCTAssertTrue(self.serviceError)
    }
    
    func testInvalidPagePackage() {
        let expectation = self.expectation(description: "packageInvalidPage")
        self.hotelPackageService.getHotelAndPackage(query: self.invalidQuery, filter: self.filterPackage, page: self.invalidPage) { (result) in
            switch result {
            case .success(_):
                break
            case .failure(_):
                self.serviceError = true
            }
            expectation.fulfill()
        }
        self.waitForExpectations(timeout: 7, handler: nil)
        XCTAssertTrue(self.serviceError)
    }
    

}

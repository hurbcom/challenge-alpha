//
//  HomeViewModelTests.swift
//  Hoteis Buzios Tests
//
//  Created by Andre Dias on 03/10/20.
//  Copyright © 2020 Andre Dias. All rights reserved.
//
@testable import Hotéis_Búzios
import XCTest

class HomeViewModelTests: XCTestCase {
    
    var sut: HomeViewModel!
    var mockAPIService: MockApiService!
      
    override func setUp() {
        super.setUp()
        
        self.sut = HomeViewModel()
        self.mockAPIService = MockApiService()
        self.sut.getHotelsAndPackages(request: self.mockAPIService)
        
    }

    override func tearDown() {
        self.sut = nil
        self.mockAPIService = nil
        super.tearDown()
    }
      
      
    func testNumberOfSections() throws {
        XCTAssertEqual(self.sut.numberOfSections, Constants.numberOfSections)
    }
    
    func testFetchHotelsAndPackages() {
        self.sut.getHotelsAndPackages(request: self.mockAPIService)
        XCTAssertTrue(mockAPIService!.isFetchHotelsCalled)
    }
    

    func testFiveStarsHotelQuantity() throws {
        let fiveStarsHotelsArray = self.sut.getFiveStarsHotel()
        XCTAssertEqual(fiveStarsHotelsArray.count, 2)
    }
    
    func testFourStarsHotelQuantity() throws {
        let fiveStarsHotelsArray = self.sut.getFourStarsHotel()
        XCTAssertEqual(fiveStarsHotelsArray.count, 9)
    }
    
    func testThreeStarsHotelQuantity() throws {
        let fiveStarsHotelsArray = self.sut.getThreeStarsHotel()
        XCTAssertEqual(fiveStarsHotelsArray.count, 7)
    }
    
    func testPackagesQuantity() throws {
        let fiveStarsHotelsArray = self.sut.getPackages()
        XCTAssertEqual(fiveStarsHotelsArray.count, 2)
    }
}

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
        sut.getHotelsAndPackages(request: self.mockAPIService)
        XCTAssertTrue(mockAPIService!.isFetchHotelsCalled)
    }
    
    func testFiveStarsHotel() throws {
        
//        XCTAssertEqual(self.sut.tableView.numberOfSections, Constants.numberOfSections)
    }
//    
//    func testVCTableViewDelegate() throws {
//        XCTAssertTrue(self.sut.tableView.delegate is HomeViewController)
//    }
//      
//    func testVCTableViewNumberOfSections() throws {
//        XCTAssertEqual(self.sut.tableView.numberOfSections, Constants.numberOfSections)
//    }
//    
//    func testVCTableViewNumberOfRowsAtSectionZero() throws {
//        XCTAssertEqual(self.sut.tableView(self.sut.tableView, numberOfRowsInSection: 0), self.homeViewModel.numberOfFiveStarsHotel())
//    }
//    
//    func testVCTableViewNumberOfRowsAtSectionOne() throws {
//        XCTAssertEqual(self.sut.tableView(self.sut.tableView, numberOfRowsInSection: 1), self.homeViewModel.numberOfFourStarsHotel())
//    }
//    
//    func testVCTableViewNumberOfRowsAtSectionTwo() throws {
//        XCTAssertEqual(self.sut.tableView(self.sut.tableView, numberOfRowsInSection: 2), self.homeViewModel.numberOfThreeStarsHotel())
//    }
//    
//    func testVCTableViewNumberOfRowsAtSectionThree() throws {
//        XCTAssertEqual(self.sut.tableView(self.sut.tableView, numberOfRowsInSection: 3), self.homeViewModel.numberOfPackages())
//    }
//    
//    func testVCTableViewHotelHeightForRow() throws {
//        XCTAssertEqual(self.sut.tableView(self.sut.tableView, heightForRowAt: IndexPath(row: 0, section: 1)), Constants.cellHotelHeight)
//    }
//    
//    func testVCTableViewPackageHeightForRow() throws {
//        XCTAssertEqual(self.sut.tableView(self.sut.tableView, heightForRowAt: IndexPath(row: 0, section: 3)), Constants.cellPackageHeight)
//    }
}

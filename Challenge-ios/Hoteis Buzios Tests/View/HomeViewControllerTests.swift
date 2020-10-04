//
//  HomeViewControllerTests.swift
//  Hoteis Buzios Tests
//
//  Created by Andre Dias on 02/10/20.
//  Copyright © 2020 Andre Dias. All rights reserved.
//

@testable import Hotéis_Búzios
import XCTest

class HomeViewControllerTests: XCTestCase {
    
    var sut: HomeViewController!
    var homeViewModel: HomeViewModel!
      
    override func setUp() {
        super.setUp()
        
        self.sut = HomeViewController.instantiate()
        self.homeViewModel = HomeViewModel()
        self.sut.homeViewModel = self.homeViewModel
        self.homeViewModel.homeViewModelDelegate = self.sut
        let apiRequest = APIRequest()
        self.homeViewModel.getHotelsAndPackages(request: apiRequest)
         _ = self.sut.view
    }

    override func tearDown() {
        self.homeViewModel = nil
        self.sut = nil
        super.tearDown()
    }
      
      
    func testIfVCTableViewIsNotNil() throws {
        XCTAssertNotNil(self.sut.tableView)
    }
    
    func testVCTableViewDataSource() throws {
        XCTAssertTrue(self.sut.tableView.dataSource is HomeViewController)
    }
    
    func testVCTableViewDelegate() throws {
        XCTAssertTrue(self.sut.tableView.delegate is HomeViewController)
    }
      
    func testVCTableViewNumberOfSections() throws {
        XCTAssertEqual(self.sut.tableView.numberOfSections, Constants.numberOfSections)
    }
    
    func testVCTableViewNumberOfRowsAtSectionZero() throws {
        XCTAssertEqual(self.sut.tableView(self.sut.tableView, numberOfRowsInSection: 0), self.homeViewModel.numberOfFiveStarsHotel())
    }
    
    func testVCTableViewNumberOfRowsAtSectionOne() throws {
        XCTAssertEqual(self.sut.tableView(self.sut.tableView, numberOfRowsInSection: 1), self.homeViewModel.numberOfFourStarsHotel())
    }
    
    func testVCTableViewNumberOfRowsAtSectionTwo() throws {
        XCTAssertEqual(self.sut.tableView(self.sut.tableView, numberOfRowsInSection: 2), self.homeViewModel.numberOfThreeStarsHotel())
    }
    
    func testVCTableViewNumberOfRowsAtSectionThree() throws {
        XCTAssertEqual(self.sut.tableView(self.sut.tableView, numberOfRowsInSection: 3), self.homeViewModel.numberOfPackages())
    }
    
    func testVCTableViewHotelHeightForRow() throws {
        XCTAssertEqual(self.sut.tableView(self.sut.tableView, heightForRowAt: IndexPath(row: 0, section: 1)), Constants.cellHotelHeight)
    }
    
    func testVCTableViewPackageHeightForRow() throws {
        XCTAssertEqual(self.sut.tableView(self.sut.tableView, heightForRowAt: IndexPath(row: 0, section: 3)), Constants.cellPackageHeight)
    }
}

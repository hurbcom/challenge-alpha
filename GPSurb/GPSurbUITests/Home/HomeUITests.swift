//
//  HomeUITests.swift
//  GPSurbUITests
//
//  Created by Gilson Santos on 26/10/19.
//  Copyright © 2019 Gilson Santos. All rights reserved.
//

import XCTest

class HomeUITests: BaseUITests {
    
    func testSeeHomeScreen() {
        self.afterLaunchingTheApp()
        self.iSeeTheScreen(identifier: self.homeViewIdentifier)
    }
    
    func testSeeHotelAndPackageScreen() {
        self.afterLaunchingTheApp()
        self.iTouchTheView(identifier: self.homeCellIdentifier)
        self.iSeeTheScreen(identifier: self.hotelAndPackageViewIdentifier)
    }
    
}

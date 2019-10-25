//
//  HotelAndPackageBaseTests.swift
//  GPSurbTests
//
//  Created by Gilson Santos on 25/10/19.
//  Copyright © 2019 Gilson Santos. All rights reserved.
//

import XCTest
@testable import GPSurb

class HotelAndPackageBaseTests: XCTestCase {
    
    var validQuery = "rj"
    var invalidQuery = "x"
    var filterHotel = TypeFilter.hotel
    var filterPackage = TypeFilter.package
    var validPage = 1
    var invalidPage = -1
    var nextPage = 2
    var hotelPackageService = HotelPackageService()
    var hotelAndPackageViewControllerMock = HotelAndPackageViewControllerMock()
}

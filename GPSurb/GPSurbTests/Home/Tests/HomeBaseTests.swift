//
//  HomeBaseTest.swift
//  GPSurbTests
//
//  Created by Gilson Santos on 24/10/19.
//  Copyright © 2019 Gilson Santos. All rights reserved.
//

import XCTest
@testable import GPSurb

class HomeBaseTests: XCTestCase {
    public lazy var homeService = HomeService()
    public lazy var homeViewController = HomeViewControllerMock()
}

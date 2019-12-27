//
//  AmenitiesCellTest.swift
//  hurb-challenge-alphaTests
//
//  Created by Hannah  on 27/12/2019.
//  Copyright © 2019 Hannah . All rights reserved.
//

import Foundation

import XCTest
@testable import hurb_challenge_alpha

class AmenitiesCellTest: XCTestCase {
    
    func testNameAmenitiesCell() {
        let name = "Sala de Jogos"
        let amenitiesCellViewModel = AmenitiesCellViewModel(name: name)
        XCTAssertEqual(name, amenitiesCellViewModel.name)

    }
    
}

//
//  SectionHeaderTest.swift
//  hurb-challenge-alphaTests
//
//  Created by Hannah  on 27/12/2019.
//  Copyright © 2019 Hannah . All rights reserved.
//

import Foundation

import XCTest
@testable import hurb_challenge_alpha

class SectionHeaderTest: XCTestCase {

    func testHeaderTitle() {
        for index in 0..<6 {
         let sectionHeaderVM = SectionHeaderViewModel(stars: index)
                if index == 0 {
                    XCTAssertEqual(sectionHeaderVM.title, "PACOTES")
                } else {
                    XCTAssertEqual(sectionHeaderVM.title, "ACOMODAÇÕES")
                }
        }
       
    }
}

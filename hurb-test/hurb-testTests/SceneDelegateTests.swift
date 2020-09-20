//
//  SceneDelegateTests.swift
//  hurb-testTests
//
//  Created by Tulio Parreiras on 20/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import XCTest
@testable import hurb_test

class SceneDelegateTests: XCTestCase {
    
    func test_configureWindow_setsWindowAsKeyAndVisible() {
        let window = UIWindow()
        let sut = SceneDelegate()
        sut.window = window
        
        sut.configureWindow()
        
        XCTAssertTrue(window.isKeyWindow, "Expected window to be key window")
        XCTAssertFalse(window.isHidden, "Expected window to be visible")
    }

}

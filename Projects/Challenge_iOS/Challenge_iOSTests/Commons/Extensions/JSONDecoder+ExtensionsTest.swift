//
//  JSONDecoder+ExtensionsTest.swift
//  Challenge_iOSTests
//
//  Created by Helio Junior on 16/11/22.
//

import XCTest
@testable import Challenge_iOS

class JSONDecoder_ExtensionsTest: XCTestCase {
    
    var sut: JSONDecoder!

    func test_FailureParseObject() {
        let object = [:] as! [String:Any]
        let model = JSONDecoder.decode(to: SearchResultModel.self, from: object)
        XCTAssertNil(model)
    }
}

//
//  HurbTests.swift
//  HurbTests
//
//  Created by Alexandre Papanis on 08/08/19.
//  Copyright © 2019 Papanis. All rights reserved.
//

import XCTest
@testable import Hurb

class HurbTests: XCTestCase {

    override func setUp() {
        // Put setup code here. This method is called before the invocation of each test method in the class.
    }

    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
    }

    func testBuscarHoteis() {
        let promise = expectation(description: "Buscar hoteis pelo nome do lugar")
        let page = 1
        APIClient.searchHotels(by: Defines.DEFAULT_PLACE, page: page, completion: { result in
            switch result {
            case .success(let page):
                XCTAssert(page.results!.count > 0)
                promise.fulfill()
            case .failure(let error):
                print(error.localizedDescription)
                XCTFail()
            }
        })
        
        waitForExpectations(timeout: 10, handler: nil)
        
    }
    
    func testConvertDoubleToCurrencyString() {
        let numero: Double = 4.56
        let moeda = Utils.convertDoubleToCurrencyString(number: numero)
        XCTAssertEqual(moeda, "R$ 4,56", "Valor não foi convertido corretamente")
    }

}

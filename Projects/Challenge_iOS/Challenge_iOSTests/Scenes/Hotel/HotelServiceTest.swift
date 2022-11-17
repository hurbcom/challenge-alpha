//
//  HotelServiceTest.swift
//  Challenge_iOSTests
//
//  Created by Helio Junior on 15/11/22.
//

import XCTest
@testable import Challenge_iOS

class HotelServiceTest: XCTestCase {

    var sut: HotelService!

    override func setUpWithError() throws {
        sut = HotelService()
    }

    override func tearDownWithError() throws {
        sut = nil
    }

    func test_IntegrationGetSuggestions() {
        let promesse = expectation(description: "Espero receber sugestões da API da Hurb")
        
        sut.getSuggestionsFrom(text: "Rio de Janeiro") { response in
            defer {
                promesse.fulfill()
            }
            switch response {
            case .success:
                XCTAssertTrue(true)
            case .failure(let error):
                XCTFail(error.localizedDescription)
            }
        }
        
        wait(for: [promesse], timeout: 3)
    }
    
    func test_IntegrationSearchHotelResults() {
        let promesse = expectation(description: "Espero receber resultados da busca por Hotel da API da Hurb.")
        
        sut.findHotelFrom(query: "Rio de Janeiro", pagination: 1) { response in
            defer {
                promesse.fulfill()
            }
            switch response {
            case .success:
                XCTAssertTrue(true)
            case .failure(let error):
                XCTFail(error.localizedDescription)
            }
        }
        
        wait(for: [promesse], timeout: 3)
    }
}

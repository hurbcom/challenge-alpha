//
//  SearchServiceTest.swift
//  Challenge_iOSTests
//
//  Created by Helio Junior on 15/11/22.
//

import XCTest
@testable import Challenge_iOS

class SearchServiceTest: XCTestCase {
    
    var sut: SearchService!

    override func setUpWithError() throws {
        sut = SearchService()
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
    
    func test_IntegrationSearchResults() {
        let promesse = expectation(description: "Espero receber opções como resultado da busca da API da Hurb.")
        
        sut.fetchSearchFrom(query: "Rio de Janeiro", pagination: 1) { response in
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

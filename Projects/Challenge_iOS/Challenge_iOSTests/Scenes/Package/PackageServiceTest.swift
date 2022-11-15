//
//  PackageServiceTest.swift
//  Challenge_iOSTests
//
//  Created by Helio Junior on 15/11/22.
//

import XCTest
@testable import Challenge_iOS

class PackageServiceTest: XCTestCase {

    var sut: PackageService!

    override func setUpWithError() throws {
        sut = PackageService()
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
    
    func test_IntegrationSearchPackageResults() {
        let promesse = expectation(description: "Espero receber resultados da busca por Pacotes da API da Hurb.")
        
        sut.findPackageFrom(query: "Rio de Janeiro", pagination: 1) { response in
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

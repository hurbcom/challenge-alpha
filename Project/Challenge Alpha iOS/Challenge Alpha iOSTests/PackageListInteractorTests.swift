//
//  PackageListInteractorTests.swift
//  Challenge Alpha iOSTests
//
//  Created by Yuri Strack on 19/01/23.
//

import XCTest
import Combine
@testable import Challenge_Alpha_iOS

final class PackageListInteractorTests: XCTestCase {
    
    let query = "Rio de Janeiro, Brasil"
    var cancellables = Set<AnyCancellable>()
    
    override func setUp() {
        super.setUp()
        cancellables = []
    }

    func testExample() throws {
        let interactor = PackageListInteractor()
        var packages: [PackageResult]?
        var error: Error?
        let expectation = expectation(description: "Fetch Package List from API")
        
        interactor.getPackages(query: self.query)
            .sink { completion in
                switch completion {
                case .finished:
                    break
                case .failure(let encounteredError):
                    error = encounteredError
                }
                
                expectation.fulfill()
            } receiveValue: { receivedPackages in
                packages = receivedPackages
            }
            .store(in: &cancellables)
        
        waitForExpectations(timeout: 5)
        XCTAssertNil(error)
        XCTAssertNotNil(packages)
    }

}

//
//  UnitTests.swift
//  UnitTests
//
//  Created by Theo Mendes on 13/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

import XCTest
import RxSwift
@testable import Alpha

class UnitTests: XCTestCase {
    var provider: AlphaNetworkManager!
    var disposeBag: DisposeBag!
    var viewModel: BaseViewModel!

    override func setUp() {
        // Put setup code here. This method is called before the invocation of each test method in the class.
        provider = AlphaNetworkManager(state: .Unit)
        disposeBag = DisposeBag()
        viewModel = FeedViewModel(provider: provider)
    }

    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
        provider = nil
        disposeBag = nil
        viewModel = nil
    }

    func testNetworkLayer() {
        let expectation = self.expectation(description: "request")
        // This is an example of a functional test case.
        // Use XCTAssert and related functions to verify your tests produce the correct results.
        provider
            .search(query: "", page: 1)
            .map { $0.results }
            .asObservable()
            .subscribe(onNext: { items in
                XCTAssertEqual(items.count, 18)
                expectation.fulfill()
            }).disposed(by: disposeBag)
        
        self.waitForExpectations(timeout: 5.0, handler: nil)
    }
    
    /// In the stub should have only one Package
    func testPackage() {
        let expectation = self.expectation(description: "Package Request")
        provider
            .search(query: "", page: 1)
            .map { $0.results }
            .asObservable()
            .subscribe(onNext: { items in
                XCTAssertEqual(items.filter({ ($0.isPackage ?? false) }).count, 1)
                expectation.fulfill()
            }).disposed(by: disposeBag)
        self.waitForExpectations(timeout: 5.0, handler: nil)
    }

    // This tests the inputs and outputs of the view model.
    func testFeedViewModel() {
        guard let viewModel = viewModel as? FeedViewModel else { return }
        let expectation = self.expectation(description: "View Model test")
        
        let input = FeedViewModel.Input(headerRefresh: Observable.just(()))
        let output = viewModel.transform(input: input)
        
        output
            .feed
            .subscribe(onNext: { sections in
                XCTAssertEqual(sections.count, 4)
                expectation.fulfill()
            }).disposed(by: disposeBag)
        self.waitForExpectations(timeout: 5.0, handler: nil)
    }
    
    func testPerformanceExample() {
        // This is an example of a performance test case.
        measure {
            // Put the code you want to measure the time of here.
        }
    }

}

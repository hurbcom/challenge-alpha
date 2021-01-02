//
//  HurbTests.swift
//  HurbTests
//
//  Created by Arthur Givigir on 1/2/21.
//

import XCTest
import Combine
@testable import Hurb

class HurbTests: XCTestCase {
    
    private let interactor = HotelListInteractor()
    private var disposables: Set<AnyCancellable> = []
    
    var placeToBeTested: String = "buzios"
    
    override func setUpWithError() throws {
        // Put setup code here. This method is called before the invocation of each test method in the class.
    }

    override func tearDownWithError() throws {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
    }

    func testReturnFromHotelsUsingString() throws {
        
        let promise = expectation(description: "Result hotels from service")
        
        interactor
            .getHotelListBy(local: self.placeToBeTested, page: 1)
            .sink { result in
                
            } receiveValue: { resultHotellist in
                
                if let resultHotellist = resultHotellist, !resultHotellist.isEmpty {
                    promise.fulfill()
                    
                } else {
                    XCTFail("Returned empty hotels from service")
                }
            }
            .store(in: &disposables)
        
        wait(for: [promise], timeout: 5)
    }
    
    func testReturnFromPlacesUsingString() throws {
        
        let promise = expectation(description: "Result places from service")
        
        interactor
            .getPlaceListBy(typing: self.placeToBeTested)
            .sink { result in
                
            } receiveValue: { resultHotellist in
                
                if let resultHotellist = resultHotellist, !resultHotellist.isEmpty {
                    promise.fulfill()
                    
                } else {
                    XCTFail("Returned empty places from service")
                }
            }
            .store(in: &disposables)
        
        wait(for: [promise], timeout: 5)
    }

}

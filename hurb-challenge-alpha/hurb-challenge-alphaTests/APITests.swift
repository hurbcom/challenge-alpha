//
//  APITests.swift
//  hurb-challenge-alphaTests
//
//  Created by Hannah  on 27/12/2019.
//  Copyright © 2019 Hannah . All rights reserved.
//

import XCTest
@testable import hurb_challenge_alpha
import Moya

class APITests: XCTestCase {
    var provider: MoyaProvider<HurbAPI>!
    var networkManager: APIService!
    
    func customEndpointClosure(_ target: HurbAPI) -> Endpoint {
        return Endpoint(url: URL(target: target).absoluteString,
                        sampleResponseClosure: { .networkResponse(401, target.testSampleData) },
                        method: target.method,
                        task: target.task,
                        httpHeaderFields: target.headers)
    }
    
    func customEndpointClosureError(_ target: HurbAPI) -> Endpoint {
           let error = NSError(domain: "error", code: 1234, userInfo: nil)

           return Endpoint(url: URL(target: target).absoluteString,
                           sampleResponseClosure: { .networkError(error) },
                           method: target.method,
                           task: target.task,
                           httpHeaderFields: target.headers)
       }
    
    func customEndpointClosureEmptyData(_ target: HurbAPI) -> Endpoint {
        return Endpoint(url: URL(target: target).absoluteString,
                        sampleResponseClosure: { .networkResponse(401, Data()) },
                        method: target.method,
                        task: target.task,
                        httpHeaderFields: target.headers)
    }
    
    func testGetAccommodationsSuccessReturnsAccommodations() {
        
        provider = MoyaProvider<HurbAPI>(endpointClosure: customEndpointClosure, stubClosure: MoyaProvider.immediatelyStub)
        networkManager = APIService(provider: provider)
        
        let accommodationsExpectation = expectation(description: "accommodations")
        var accommodationsResponse: [Accommodation]?
        
        networkManager.getAccommodations(query: "buzios", page: 2) { (accommodations, _) in
            accommodationsResponse = accommodations
            accommodationsExpectation.fulfill()
        }
        
        waitForExpectations(timeout: 1) { (_) in
            XCTAssertNotNil(accommodationsResponse)
        }
    }
    
    func testGetAccommodationsWhenResponseErrorReturnsError() {
        provider = MoyaProvider<HurbAPI>(endpointClosure: customEndpointClosureError, stubClosure: MoyaProvider.immediatelyStub)
        networkManager = APIService(provider: provider)
        
      let errorExpectation = expectation(description: "error")
      var errorResponse: Error?
      networkManager.getAccommodations(query: "buzios", page: 2) { (_, error) in
          errorResponse = error
          errorExpectation.fulfill()
      }
      waitForExpectations(timeout: 1) { (_) in
        XCTAssertNotNil(errorResponse)
      }
    }
    
    func testGetAccommodationsWhenEmptyDataReturnsError() {
   
        provider = MoyaProvider<HurbAPI>(endpointClosure: customEndpointClosureEmptyData, stubClosure: MoyaProvider.immediatelyStub)
        networkManager = APIService(provider: provider)
        
      let errorExpectation = expectation(description: "error")
      var errorResponse: Error?
      networkManager.getAccommodations(query: "buzios", page: 2) { (_, error) in
            errorResponse = error
            errorExpectation.fulfill()
       }
      waitForExpectations(timeout: 1) { (_) in
        XCTAssertNotNil(errorResponse)
      }
    }
    
    func testGetSuggestionSuccessReturnsAccommodations() {
          
          provider = MoyaProvider<HurbAPI>(endpointClosure: customEndpointClosure, stubClosure: MoyaProvider.immediatelyStub)
          networkManager = APIService(provider: provider)
          
          let suggestionsExpectation = expectation(description: "suggestions")
          var suggestionsResponse: [Suggestion]?
        networkManager.getSuggestions(query: "buzios") { (suggestions, _) in
              suggestionsResponse = suggestions
              suggestionsExpectation.fulfill()
        }
          waitForExpectations(timeout: 1) { (_) in
              XCTAssertNotNil(suggestionsResponse)
          }
      }
    
    func testGetSuggestionWhenResponseErrorReturnsError() {
        provider = MoyaProvider<HurbAPI>(endpointClosure: customEndpointClosureError, stubClosure: MoyaProvider.immediatelyStub)
        networkManager = APIService(provider: provider)
        
      let errorExpectation = expectation(description: "error")
      var errorResponse: Error?
        
    networkManager.getSuggestions(query: "buzios") { (_, error) in
        errorResponse = error
        errorExpectation.fulfill()
    }
      waitForExpectations(timeout: 1) { (_) in
        XCTAssertNotNil(errorResponse)
      }
    }
    
    func testSuggestionsWhenEmptyDataReturnsError() {
    
         provider = MoyaProvider<HurbAPI>(endpointClosure: customEndpointClosureEmptyData, stubClosure: MoyaProvider.immediatelyStub)
         networkManager = APIService(provider: provider)
         
       let errorExpectation = expectation(description: "error")
       var errorResponse: Error?
        
        networkManager.getSuggestions(query: "buzios") { (_, error) in
               errorResponse = error
               errorExpectation.fulfill()
        }
     
       waitForExpectations(timeout: 1) { (_) in
         XCTAssertNotNil(errorResponse)
       }
     }
    
    func testPerformanceExample() {
        // This is an example of a performance test case.
        self.measure {
            // Put the code you want to measure the time of here.
        }
    }
    
}

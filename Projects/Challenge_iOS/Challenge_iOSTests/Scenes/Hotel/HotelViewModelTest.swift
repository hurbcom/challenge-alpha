//
//  HotelViewModelTest.swift
//  Challenge_iOSTests
//
//  Created by Helio Junior on 15/11/22.
//

import XCTest
@testable import Challenge_iOS

class HotelViewModelTest: XCTestCase {
    
    var sut: HotelViewModel!

    func test_SuccessGetSuggestions() {
        let serviceStub = HotelServiceSuccessStub()
        sut = HotelViewModel(service: serviceStub)
        
        sut.getSuggestionsFrom(text: "Hotel Plaza")
        
        XCTAssertEqual(sut.getSuggestionsResults()[2].text, "Royal Plaza Hotel")
    }

    func test_SuccessFetchSearch() {
        let serviceStub = HotelServiceSuccessStub()
        sut = HotelViewModel(service: serviceStub)
        
        sut.findHotelFrom(query: "Hotel Plaza")
        
        let result = sut.getSearchResults().first
        XCTAssertEqual(result?.name, "Imperial Plaza Hotel")
        XCTAssertEqual(result?.sku, "HT-VKLT-0-0-0-0-0-0-0-0-0")
        XCTAssertEqual(result?.isAvailable, true)
        XCTAssertEqual(result?.gallery.first?.url, "https://thumbcdn-z.hotelurbano.net/b1UA0AzQ5ztR0YyYvKsldRLCUPc=/origxorig/center/middle/filters:quality(70)/https://novo-hu.s3.amazonaws.com/reservas/ota/prod/hotel/519158/2016072814697157749_20180131144941.jpg")
    }
    
    func test_FailureFetchSearch() {
        let serviceStub = HotelServiceFailureStub()
        sut = HotelViewModel(service: serviceStub)
        
        sut.findHotelFrom(query: "Babababa")
        
        let results = sut.getSearchResults()
        XCTAssertEqual(results.count, 0)
    }
}

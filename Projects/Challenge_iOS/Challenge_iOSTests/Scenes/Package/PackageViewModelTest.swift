//
//  PackageViewModelTest.swift
//  Challenge_iOSTests
//
//  Created by Helio Junior on 15/11/22.
//

import XCTest
@testable import Challenge_iOS

class PackageViewModelTest: XCTestCase {
    
    var sut: PackageViewModel!

    func test_SuccessGetSuggestions() {
        let serviceStub = PackageServiceSuccessStub()
        sut = PackageViewModel(service: serviceStub)
        
        sut.getSuggestionsFrom(text: "Gramado")
        
        XCTAssertEqual(sut.getSuggestionsResults()[2].text, "Pacote Gramado - 2023")
    }
    
    func test_SuccessFetchSearch() {
        let serviceStub = PackageServiceSuccessStub()
        sut = PackageViewModel(service: serviceStub)
        
        sut.findPackageFrom(query: "Pacote Gramado")
        
        let result = sut.getSearchResults().first
        XCTAssertEqual(result?.name, "Imperial Plaza Hotel")
        XCTAssertEqual(result?.sku, "HT-VKLT-0-0-0-0-0-0-0-0-0")
        XCTAssertEqual(result?.isAvailable, true)
        XCTAssertEqual(result?.gallery.first?.url, "https://thumbcdn-z.hotelurbano.net/b1UA0AzQ5ztR0YyYvKsldRLCUPc=/origxorig/center/middle/filters:quality(70)/https://novo-hu.s3.amazonaws.com/reservas/ota/prod/hotel/519158/2016072814697157749_20180131144941.jpg")
    }
    
    func test_FailureFetchSearch() {
        let serviceStub = PackageServiceFailureStub()
        sut = PackageViewModel(service: serviceStub)
        
        sut.findPackageFrom(query: "Pacote Gramado")
        
        let results = sut.getSearchResults()
        XCTAssertEqual(results.count, 0)
    }
}

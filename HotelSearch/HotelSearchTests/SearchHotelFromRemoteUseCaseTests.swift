//
//  SearchHotelFromRemoteUseCaseTests.swift
//  HotelSearchTests
//
//  Created by Tulio Parreiras on 13/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import XCTest

final class RemoteHotelSearcher {
    
}

final class HTTPClientSpy {
    var requestedURLs = [URL]()
}

class SearchHotelFromRemoteUseCaseTests: XCTestCase {

    func test_init_doesNotRequestDataFromURL() {
        let client = HTTPClientSpy()
        let _ = RemoteHotelSearcher()
        
        XCTAssertTrue(client.requestedURLs.isEmpty)
    }

}

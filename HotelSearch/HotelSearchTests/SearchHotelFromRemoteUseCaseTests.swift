//
//  SearchHotelFromRemoteUseCaseTests.swift
//  HotelSearchTests
//
//  Created by Tulio Parreiras on 13/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import XCTest

final class RemoteHotelSearcher {
    
    private let url: URL
    private let client: HTTPClientSpy
    
    init(url: URL, client: HTTPClientSpy) {
        self.url = url
        self.client = client
    }
    
    func searchHotel(with searchText: String) {
        self.client.get(from: url)
    }
    
}

final class HTTPClientSpy {
    var requestedURLs = [URL]()
    
    func get(from url: URL) {
        self.requestedURLs.append(url)
    }
}

class SearchHotelFromRemoteUseCaseTests: XCTestCase {

    func test_init_doesNotRequestDataFromURL() {
        let client = HTTPClientSpy()
        let _ = RemoteHotelSearcher(url: URL(string: "https://any-url.com")!, client: client)
        
        XCTAssertTrue(client.requestedURLs.isEmpty)
    }
    
    func test_searchHotel_requestsDataFromURL() {
        let url = URL(string: "https://any-url.com")!
        let client = HTTPClientSpy()
        let sut = RemoteHotelSearcher(url: url, client: client)
        
        sut.searchHotel(with: "")
        
        XCTAssertEqual(client.requestedURLs, [url])
    }

}

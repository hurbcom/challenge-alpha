//
//  LoadImageFromRemoteUseCaseTests.swift
//  HotelSearchTests
//
//  Created by Tulio Parreiras on 19/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import XCTest

import HotelSearch

class LoadImageFromRemoteUseCaseTests: XCTestCase {

    func test_init_doesNotRequestDataFromURL() {
        let (_, client) = makeSUT()
        
        XCTAssertTrue(client.requestedURLs.isEmpty)
    }
    
    private func makeSUT() -> (sut: RemoteImageDataLoader, client: HTTPClientSpy) {
        let client = HTTPClientSpy()
        let sut = RemoteImageDataLoader(client: client)
        return (sut, client)
    }

}

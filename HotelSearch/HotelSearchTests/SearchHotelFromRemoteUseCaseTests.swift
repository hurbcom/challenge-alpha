//
//  SearchHotelFromRemoteUseCaseTests.swift
//  HotelSearchTests
//
//  Created by Tulio Parreiras on 13/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import XCTest

import HotelSearch

final class RemoteHotelSearcher {
    
    typealias Result = Swift.Result<[Hotel], Error>
    
    private let url: URL
    private let client: HTTPClientSpy
    
    public enum Error: Swift.Error {
        case invalidData
    }
    
    init(url: URL, client: HTTPClientSpy) {
        self.url = url
        self.client = client
    }
    
    func searchHotel(with searchText: String, competion: @escaping (Result) -> Void) {
        self.client.get(from: url) { result in
            competion(.failure(.invalidData))
        }
    }
    
}

final class HTTPClientSpy: HTTPClient {
    private var messages = [(url: URL, completion: (HTTPClient.Result) -> Void)]()
    
    var requestedURLs: [URL] {
        return messages.compactMap { $0.url }
    }
    
    func get(from url: URL, completion: @escaping (HTTPClient.Result) -> Void) {
        messages.append((url, completion))
    }
    
    func complete(withStatusCode code: Int, data: Data, at index: Int = 0) {
        let response = HTTPURLResponse(
            url: requestedURLs[index],
            statusCode: code,
            httpVersion: nil,
            headerFields: nil
        )!
        messages[index].completion(.success((data, response)))
    }
}

class SearchHotelFromRemoteUseCaseTests: XCTestCase {

    func test_init_doesNotRequestDataFromURL() {
        let (_, client) = makeSUT()
        
        XCTAssertTrue(client.requestedURLs.isEmpty)
    }
    
    func test_searchHotel_requestsDataFromURL() {
        let url = URL(string: "https://any-url.com")!
        let (sut, client) = makeSUT(url: url)
        
        sut.searchHotel(with: "") { _ in }
        
        XCTAssertEqual(client.requestedURLs, [url])
    }
    
    func test_searchHotelTwice_requestsDataFromURLTwice() {
        let url = URL(string: "https://any-url.com")!
        let (sut, client) = makeSUT(url: url)
        
        sut.searchHotel(with: "") { _ in }
        sut.searchHotel(with: "") { _ in }
        
        XCTAssertEqual(client.requestedURLs, [url, url])
    }
    
    func test_searchHotel_deliversErrorOnNon200HTTPResponse() {
        let (sut, client) = makeSUT()
        
        expect(sut, toCompleteWith: .failure(RemoteHotelSearcher.Error.invalidData), when: {
            let emptyListJson = makeHotelsJSON([])
            client.complete(withStatusCode: 199, data: emptyListJson)
        })
    }
    
    // MARK: Helpers
    
    private func makeSUT(url: URL = URL(string: "https://any-url.com")!) -> (sut: RemoteHotelSearcher, client: HTTPClientSpy) {
        let client = HTTPClientSpy()
        let sut = RemoteHotelSearcher(url: url, client: client)
        return (sut, client)
    }
    
    private func makeHotelsJSON(_ items: [[String: Any]]) -> Data {
        let json = ["result": items]
        return try! JSONSerialization.data(withJSONObject: json)
    }
    
    private func expect(_ sut: RemoteHotelSearcher, toCompleteWith expectedResult: RemoteHotelSearcher.Result, when action: () -> Void) {
        
        let exp = expectation(description: "Wait for search completion")
        
        sut.searchHotel(with: "") { receivedResult in
            switch (receivedResult, expectedResult) {
            case let (.success(receivedItems), .success(expectedItems)):
                XCTAssertEqual(receivedItems, expectedItems)
                
            case let (.failure(receivedError as NSError), .failure(expectedError as NSError)):
                XCTAssertEqual(receivedError, expectedError)
                
            default:
                XCTFail("Expected result \(expectedResult) got \(receivedResult) instead")
            }
            exp.fulfill()
        }
        
        action()
        
        wait(for: [exp], timeout: 1.0)
    }

}

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
    
    private let url: URL
    private let client: HTTPClientSpy
    
    public enum Error: Swift.Error {
        case invalidData
    }
    
    init(url: URL, client: HTTPClientSpy) {
        self.url = url
        self.client = client
    }
    
    func searchHotel(with searchText: String, competion: @escaping (Result<[Hotel], Error>) -> Void) {
        self.client.get(from: url) { result in
            competion(.failure(.invalidData))
        }
    }
    
}

final class HTTPClientSpy {
    private var messages = [(url: URL, completion: (Result<(Data, HTTPURLResponse), Error>) -> Void)]()
    
    var requestedURLs: [URL] {
        return messages.compactMap { $0.url }
    }
    
    func get(from url: URL, completion: @escaping (Result<(Data, HTTPURLResponse), Error>) -> Void) {
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
        
        let exp = expectation(description: "Wait for search completion")
        
        sut.searchHotel(with: "") { receivedResult in
            switch receivedResult {
            case .success:
                XCTFail("Expected to receive error, got \(receivedResult) instead")
            case .failure: break
            }
            exp.fulfill()
        }
        
        let emptyListJson = makeHotelsJSON([])
        client.complete(withStatusCode: 199, data: emptyListJson)
        
        wait(for: [exp], timeout: 1.0)
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

}

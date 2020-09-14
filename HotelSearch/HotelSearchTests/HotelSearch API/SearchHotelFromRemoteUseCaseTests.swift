//
//  SearchHotelFromRemoteUseCaseTests.swift
//  HotelSearchTests
//
//  Created by Tulio Parreiras on 13/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import XCTest

import HotelSearch

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
    
    func test_searchHotel_deliversErrorOnClientError() {
        let (sut, client) = makeSUT()
        
        expect(sut, toCompleteWith: .failure(RemoteHotelSearcher.Error.connectivity), when: {
            let clientError = NSError(domain: "Test", code: 0)
            client.complete(with: clientError)
        })
    }
    
    func test_searchHotel_deliversErrorOnNon200HTTPResponse() {
        let (sut, client) = makeSUT()
        
        let samples = [199, 201, 300, 400, 500]
        samples.enumerated().forEach { index, code in
            expect(sut, toCompleteWith: .failure(RemoteHotelSearcher.Error.invalidData), when: {
                let emptyListJson = makeItemsJSON([])
                client.complete(withStatusCode: code, data: emptyListJson, at: index)
            })
        }
    }
    
    func test_searchHotel_deliversErrorOn200HTTPResponseWithInvalidJSON() {
        let (sut, client) = makeSUT()
        
        expect(sut, toCompleteWith: .failure(RemoteHotelSearcher.Error.invalidData), when: {
            let invalidJSON = Data("invalid json".utf8)
            client.complete(withStatusCode: 200, data: invalidJSON)
        })
    }
    
    func test_searchHotel_deliversNoItemsOn200HTTPResponseWithEmptyJSONList() {
        let (sut, client) = makeSUT()
        
        expect(sut, toCompleteWith: .success([]), when: {
            let emptyListJson = makeItemsJSON([])
            client.complete(withStatusCode: 200, data: emptyListJson)
        })
    }
    
    func test_searchHotel_deliversItemsOn200HTTPResponseWithJSONItems() {
        let (sut, client) = makeSUT()
        
        let item1 = makeItem(category: "a category", description: "a description", id: 1, image: URL(string: "https://a-image-url.com")!, isHotel: true, name: "a name", smallDescription: "a small description", star: 1, url: URL(string: "https://a-url.com")!)
        
        let item2 = makeItem(category: "another category", description: "another description", id: 2, image: URL(string: "https://another-image-url.com")!, isHotel: false, name: "another name", smallDescription: "another small description", star: 2, url: URL(string: "https://another-url.com")!)
        
        let items = [item1.model, item2.model]
        
        expect(sut, toCompleteWith: .success(items), when: {
            let json = makeItemsJSON([item1.json, item2.json])
            client.complete(withStatusCode: 200, data: json)
        })
        
    }
    
    func test_searchHotel_deliversNoSearchResultAfterSUTInstanceHasBeenDeallocated() {
        let url = URL(string: "https://any-url.com")!
        let client = HTTPClientSpy()
        var sut: RemoteHotelSearcher? = RemoteHotelSearcher(url: url, client: client)
        
        var capturedSearchResults = [RemoteHotelSearcher.SearchResult]()
        sut?.searchHotel(with: "", completion: { capturedSearchResults.append($0) })
        
        sut = nil
        client.complete(withStatusCode: 200, data: makeItemsJSON([]))
        
        XCTAssertTrue(capturedSearchResults.isEmpty)
    }
    
    // MARK: Helpers
    
    private func makeSUT(url: URL = URL(string: "https://any-url.com")!) -> (sut: RemoteHotelSearcher, client: HTTPClientSpy) {
        let client = HTTPClientSpy()
        let sut = RemoteHotelSearcher(url: url, client: client)
        trackForMemoryLeaks(client)
        trackForMemoryLeaks(sut)
        return (sut, client)
    }
    
    private func trackForMemoryLeaks(_ instance: AnyObject, file: StaticString = #file, line: UInt = #line) {
        addTeardownBlock { [weak instance] in
            XCTAssertNil(instance, "Instance should have been deallocated. Potential memory leak.", file: file, line: line)
        }
    }
    
    private func expect(_ sut: RemoteHotelSearcher, toCompleteWith expectedSearchResult: RemoteHotelSearcher.SearchResult, when action: () -> Void, file: StaticString = #file, line: UInt = #line) {
        
        let exp = expectation(description: "Wait for search completion")
        
        sut.searchHotel(with: "") { receivedSearchResult in
            switch (receivedSearchResult, expectedSearchResult) {
            case let (.success(receivedItems), .success(expectedItems)):
                XCTAssertEqual(receivedItems, expectedItems, file: file, line: line)
                
            case let (.failure(receivedError as NSError), .failure(expectedError as NSError)):
                XCTAssertEqual(receivedError, expectedError, file: file, line: line)
                
            default:
                XCTFail("Expected SearchResult \(expectedSearchResult) got \(receivedSearchResult) instead", file: file, line: line)
            }
            exp.fulfill()
        }
        
        action()
        
        wait(for: [exp], timeout: 1.0)
    }
    
    private func makeItemsJSON(_ items: [[String: Any]]) -> Data {
        let json = ["result": items]
        return try! JSONSerialization.data(withJSONObject: json)
    }
    
    private func makeItem(
        amenities: [Amenity] = [Amenity(category: "a category", name: "a name")],
        category: String,
        description: String,
        gallery: [HotelImage] = [HotelImage(description: "a description", url: URL(string: "https://a-url.com")!)],
        id: Int,
        image: URL,
        isHotel: Bool,
        name: String,
        price: HotelPrice = HotelPrice(amount: 12.34, amountPerDay: 43.21, currency: "a currency"),
        quantityDescriptors: QuantityDescriptor = QuantityDescriptor(maxAdults: 5, maxChildren: 6, maxFreeChildrenAge: 7),
        smallDescription: String,
        star: Int,
        tags: [String] = [],
        url: URL) -> (model: Hotel, json: [String: Any]) {
        let item = Hotel(amenities: amenities, category: category, description: description, gallery: gallery, id: id, image: image, isHotel: isHotel, name: name, price: price, quantityDescriptors: quantityDescriptors, smallDescription: smallDescription, star: star, tags: tags, url: url)
        
        return (item, item.json)
    }

}

private extension Hotel {
    var json: [String: Any] {
        return [
            "amenities": amenities.map{ $0.json },
            "category": category,
            "description": description,
            "gallery": gallery.map{ $0.json },
            "id": id,
            "image": image.absoluteString,
            "isHotel": isHotel,
            "name": name,
            "price": price.json,
            "quantityDescriptors": quantityDescriptors.json,
            "smallDescription": smallDescription,
            "star": star,
            "tags": tags,
            "url": url.absoluteString
        ]
    }
}

private extension Amenity {
    var json: [String: Any] {
        return ["category": category,"name": name]
    }
}

private extension HotelImage {
    var json: [String: Any] {
        return ["description": description, "url": url.absoluteString]
    }
}

private extension HotelPrice {
    var json: [String: Any] {
        return ["amount": amount, "amountPerDay": amountPerDay, "currency": currency]
    }
}

private extension QuantityDescriptor {
    var json: [String: Any] {
        return ["maxAdults": maxAdults, "maxChildren": maxChildren, "maxFreeChildrenAge": maxFreeChildrenAge]
    }
}

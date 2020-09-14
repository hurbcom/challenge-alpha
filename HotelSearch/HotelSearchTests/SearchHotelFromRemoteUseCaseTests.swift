//
//  SearchHotelFromRemoteUseCaseTests.swift
//  HotelSearchTests
//
//  Created by Tulio Parreiras on 13/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import XCTest

import HotelSearch

final class HotelMapper {
    
    private init() { }
    
    static func map(_ data: Data, from response: HTTPURLResponse) throws -> [Hotel] {
        guard response.statusCode == 200, let root = try? JSONDecoder().decode(Root.self, from: data) else {
            throw RemoteHotelSearcher.Error.invalidData
        }
        return root.hotels.map { $0.item }
    }
    
    struct Root: Decodable {
        let hotels: [RemoteHotelItem]
        
        enum CodingKeys: String, CodingKey {
            case hotels = "result"
        }
    }
    
    struct RemoteHotelItem: Decodable {
        let amenities: [RemoteAmenityItem]
        let category: String
        let description: String
        let gallery: [RemoteHotelImageItem]
        let id: Int
        let image: URL
        let isHotel: Bool
        let name: String
        let price: RemoteHotelPriceItem
        let quantityDescriptors: RemoteQuantityDescriptorItem
        let smallDescription: String
        let star: Int
        let tags: [String]
        let url: URL
        
        var item: Hotel {
            return Hotel(amenities: amenities.map { $0.item },
                         category: category,
                         description: description,
                         gallery: gallery.compactMap { $0.item },
                         id: id,
                         image: image,
                         isHotel: isHotel,
                         name: name,
                         price: price.item,
                         quantityDescriptors: quantityDescriptors.item,
                         smallDescription: smallDescription,
                         star: star,
                         tags: tags,
                         url: url)
        }
    }

    struct RemoteAmenityItem: Decodable {
        let category: String
        let name: String
        
        var item: Amenity {
            return Amenity(category: category, name: name)
        }
    }

    struct RemoteHotelImageItem: Decodable {
        let description: String
        let url: URL
        
        var item: HotelImage {
            return HotelImage(description: description, url: url)
        }
    }

    struct RemoteHotelPriceItem: Decodable {
        let amount: Double
        let amountPerDay: Double
        let currency: String
        
        var item: HotelPrice {
            return HotelPrice(amount: amount, amountPerDay: amountPerDay, currency: currency)
        }
    }

    struct RemoteQuantityDescriptorItem: Decodable {
        let maxAdults: Int
        let maxChildren: Int
        let maxFreeChildrenAge: Int
        
        var item: QuantityDescriptor {
            return QuantityDescriptor(maxAdults: maxAdults, maxChildren: maxChildren, maxFreeChildrenAge: maxFreeChildrenAge)
        }
    }

    
}

final class RemoteHotelSearcher {
    
    typealias Result = Swift.Result<[Hotel], Swift.Error>
    
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
        self.client.get(from: url) { [weak self] result in
            guard self != nil else { return }
            switch result {
            case let .success((data, response)):
                competion(Result {
                    try HotelMapper.map(data, from: response)
                })
            case .failure:
                competion(.failure(RemoteHotelSearcher.Error.invalidData))
            }
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
    
    func test_searchHotel_deliversNoResultAfterSUTInstanceHasBeenDeallocated() {
        let url = URL(string: "https://any-url.com")!
        let client = HTTPClientSpy()
        var sut: RemoteHotelSearcher? = RemoteHotelSearcher(url: url, client: client)
        
        var capturedResults = [RemoteHotelSearcher.Result]()
        sut?.searchHotel(with: "", competion: { capturedResults.append($0) })
        
        sut = nil
        client.complete(withStatusCode: 200, data: makeItemsJSON([]))
        
        XCTAssertTrue(capturedResults.isEmpty)
    }
    
    // MARK: Helpers
    
    private func makeSUT(url: URL = URL(string: "https://any-url.com")!) -> (sut: RemoteHotelSearcher, client: HTTPClientSpy) {
        let client = HTTPClientSpy()
        let sut = RemoteHotelSearcher(url: url, client: client)
        return (sut, client)
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
    
    private func makeItemsJSON(_ items: [[String: Any]]) -> Data {
        let json = ["result": items]
        return try! JSONSerialization.data(withJSONObject: json)
    }
    
    private func makeItem(
        amenities: [Amenity] = [],
        category: String,
        description: String,
        gallery: [HotelImage] = [],
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

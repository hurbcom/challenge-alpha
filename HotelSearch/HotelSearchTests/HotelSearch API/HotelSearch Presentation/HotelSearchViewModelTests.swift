//
//  HotelSearchViewModelTests.swift
//  HotelSearchTests
//
//  Created by Tulio Parreiras on 19/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import XCTest

import HotelSearch

class HotelSearchViewModelTests: XCTestCase {

    func test_init_doesNotSendMessagesToView() {
        let (_, spy) = makeSUT()
        
        XCTAssertTrue(spy.messages.isEmpty)
    }
    
    func test_didStartSearchingHotel_startLoadingAndClearModels() {
        let (sut, spy) = makeSUT()
        sut.searchHotel()
        
        XCTAssertEqual(spy.messages, [
            .display(model: []),
            .display(loading: true)
        ])
    }
    
    func test_didFinishSearchingHotel_displayHotelsGrouppedByStars() {
        let (sut, spy) = makeSUT()
        let hotel1 = makeHotel(category: "a category", description: "a description", id: "1", name: "a name", stars: 5)
        let hotel2 = makeHotel(stars: 3)
        let hotel3 = makeHotel(stars: 2)
        let hotel4 = makeHotel(stars: nil)
        sut.searchHotel()
        spy.completeHotelSearchWith(.success([hotel4, hotel2, hotel3, hotel1]))
        
        XCTAssertEqual(spy.messages, [
            .display(model: []),
            .display(loading: true),
            .display(model: [
                [HotelViewModel(hotel: hotel1)],
                [HotelViewModel(hotel: hotel2)],
                [HotelViewModel(hotel: hotel3)],
                [HotelViewModel(hotel: hotel4)]
            ]),
            .display(loading: false)
        ])
    }
    
    func test_didFinishSearchingHotelWithError_displaysErrorMessageAndStopsLoading() {
        let (sut, spy) = makeSUT()
        
        sut.searchHotel()
        let error = NSError(domain: "", code: 0)
        spy.completeHotelSearchWith(.failure(error))
        
        XCTAssertEqual(spy.messages, [
            .display(model: []),
            .display(loading: true),
            .display(error: error.localizedDescription),
            .display(loading: false)
        ])
    }
    
    func test_imageLoad_doesNotSendMessagesToViewOnEmptyHotels() {
        let (sut, spy) = makeSUT()
        
        _ = sut.loadImage(at: 0, section: 0)
        XCTAssertTrue(spy.messages.isEmpty)
    }
    
    func test_didFinishLoadingImage_displayImageDataForIndexAndSection() {
        let (sut, spy) = makeSUT()
        
        let (hotel1, hotel2) = anyHotels()
        sut.searchHotel()
        spy.completeHotelSearchWith(.success([hotel1, hotel2]))
        let index = 0
        let section = 1
        _ = sut.loadImage(at: index, section: section)
        let data = Data("any data".utf8)
        spy.completeImageLoadWith(.success(data))
        
        XCTAssertEqual(spy.messages, [
            .display(model: []),
            .display(loading: true),
            .display(model: [
                [HotelViewModel(hotel: hotel1)],
                [HotelViewModel(hotel: hotel2)]
            ]),
            .display(loading: false),
            .display(imageLoading: true, index: index, section: section),
            .display(imageData: data, index: index, section: section),
            .display(imageLoading: false, index: index, section: section)
        ])
    }
    
    func test_didFinishLoadingImageWithError_stopsLoading() {
        let (sut, spy) = makeSUT()
        
        let (hotel1, hotel2) = anyHotels()
        sut.searchHotel()
        spy.completeHotelSearchWith(.success([hotel1, hotel2]))
        let index = 0
        let section = 1
        _ = sut.loadImage(at: index, section: section)
        spy.completeImageLoadWith(.failure(NSError(domain: "", code: 0)))
        
        XCTAssertEqual(spy.messages, [
            .display(model: []),
            .display(loading: true),
            .display(model: [
                [HotelViewModel(hotel: hotel1)],
                [HotelViewModel(hotel: hotel2)]
            ]),
            .display(loading: false),
            .display(imageLoading: true, index: index, section: section),
            .display(imageLoading: false, index: index, section: section)
        ])
    }
    
    // MARK: - Helpers
    
    private func makeSUT(file: StaticString = #file, line: UInt = #line) -> (sut: HotelSearchViewModel, spy: ViewSpy) {
        let spy = ViewSpy()
        let sut = HotelSearchViewModel(hotelSearcher: spy, imageDataLoader: spy)
        sut.hotelSearchView = spy
        trackForMemoryLeaks(sut, file: file, line: line)
        trackForMemoryLeaks(spy, file: file, line: line)
        return (sut, spy)
    }
    
    private func makeHotel(category: String? = nil, description: String? = nil, id: String? = nil, image: URL? = nil, name: String? = nil, stars: Int? = nil) -> Hotel {
        return Hotel(address: nil, amenities: nil, category: category, description: description, gallery: nil, id: id, image: image, isHotel: nil, name: name, price: nil, quantityDescriptors: nil, smallDescription: nil, stars: stars, tags: nil, url: nil)
    }
    
    private func anyHotels() -> (hotel1: Hotel, hotel2: Hotel) {
        let hotel1 = makeHotel(category: "a category", description: "a description", id: "1", image: URL(string: "https://a-url.com")!, name: "a name", stars: 5)
        let hotel2 = makeHotel(image: URL(string: "https://any-url.com")!, stars: 3)
        return (hotel1, hotel2)
    }

}

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
    
    func test_didFinishSearchingHotel_displayHotelsAndStopLoading() {
        let (sut, spy) = makeSUT()
        let hotel = makeHotel(category: "a category", description: "a description", id: "1", name: "a name", stars: 5)
        sut.didFinishSearchingHotels(with: [hotel])
        
        XCTAssertEqual(spy.messages, [
            .display(model: [[HotelViewModel(hotel: hotel)]]),
            .display(loading: false)
        ])
    }
    
    func test_didFinishSearchingHotel_displayHotelsGrouppedByStars() {
        let (sut, spy) = makeSUT()
        let hotel1 = makeHotel(category: "a category", description: "a description", id: "1", name: "a name", stars: 5)
        let hotel2 = makeHotel(stars: 3)
        let hotel3 = makeHotel(stars: 2)
        let hotel4 = makeHotel(stars: nil)
        sut.didFinishSearchingHotels(with: [hotel4, hotel2, hotel3, hotel1])
        
        XCTAssertEqual(spy.messages, [
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
        
        let error = NSError(domain: "", code: 0)
        sut.didFinishSearchingHotels(with: error)
        
        XCTAssertEqual(spy.messages, [
            .display(error: error.localizedDescription),
            .display(loading: false)
        ])
    }
    
    // MARK: - Helpers
    
    private func makeSUT() -> (sut: HotelSearchViewModel, spy: ViewSpy) {
        let spy = ViewSpy()
        let sut = HotelSearchViewModel(hotelSearcher: spy, imageDataLoader: spy)
        sut.hotelSearchView = spy
        return (sut, spy)
    }
    
    private func makeHotel(category: String? = nil, description: String? = nil, id: String? = nil, name: String? = nil, stars: Int? = nil) -> Hotel {
        return Hotel(address: nil, amenities: nil, category: category, description: description, gallery: nil, id: id, image: nil, isHotel: nil, name: name, price: nil, quantityDescriptors: nil, smallDescription: nil, stars: stars, tags: nil, url: nil)
    }
    
    final class ViewSpy: HotelSearchView, HotelSearcher, ImageDataLoader {
        
        // MARK: - Hotel Search View
        
        enum Message: Hashable {
            case display(model: [[HotelViewModel]])
            case display(error: String)
            case display(loading: Bool)
            case display(imageData: Data)
            case display(imageLoading: Bool)
        }
        
        private(set) var messages = Set<Message>()
        
        func display(_ model: [[HotelViewModel]]) {
            messages.insert(.display(model: model))
        }
        
        func displayError(_ error: String) {
            messages.insert(.display(error: error))
        }
        
        func displayLoading(_ isLoading: Bool) {
            messages.insert(.display(loading: isLoading))
        }
        
        func displayImageData(_ data: Data, for index: Int, section: Int) {
            messages.insert(.display(imageData: data))
        }
        
        func displayImageLoading(_ isLoading: Bool, for index: Int, section: Int) {
            messages.insert(.display(imageLoading: isLoading))
        }
        
        // MARK: - Hotel Searcher
        
        func searchHotel(with searchText: String, completion: @escaping (SearchResult) -> Void) {
            
        }
        
        // MARK: - Image Data Loader
        
        private struct MockTask: ImageDataLoaderTask {
            func cancel() { }
        }
        
        func loadImageData(from url: URL, completion: @escaping (ImageDataLoader.Result) -> Void) -> ImageDataLoaderTask {
            return MockTask()
        }
        
    }

}

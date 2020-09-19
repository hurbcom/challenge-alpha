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
        let view = ViewSpy()
        let _ = HotelSearchViewModel(hotelSearcher: view, imageDataLoader: view)
        
        XCTAssertTrue(view.messages.isEmpty)
    }
    
    // MARK: Helpers
    
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

//
//  HotelSearchUIIntegrationTests.swift
//  hurb-testTests
//
//  Created by Tulio Parreiras on 19/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import XCTest

import HotelSearch
import HotelSearchiOS
@testable import hurb_test

class HotelSearchUIIntegrationTests: XCTestCase {

    func test_searchHotelActions_requestSearchFromLoader() {
        let spy = Spy()
        let sut = HotelSearchUIComposer.hotelSearchComposedWith(hotelSearcher: spy, imageDataLoader: spy)
        sut.loadViewIfNeeded()
        
        XCTAssertEqual(spy.searchHotelCallCount, 0)
        sut.simulateHotelSearch()
        XCTAssertEqual(spy.searchHotelCallCount, 1)
    }
    
    // MARK: - 
    
    private class Spy: HotelSearcher, ImageDataLoader {
        
        private var searchCompletions = [(HotelSearcher.SearchResult) -> Void]()
        var searchHotelCallCount: Int {
            return self.searchCompletions.count
        }
        
        // MARK: - Hotel Searcher
        
        func searchHotel(with searchText: String, completion: @escaping (SearchResult) -> Void) {
            self.searchCompletions.append(completion)
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

extension HotelSearchViewController {
    
    func simulateHotelSearch() {
        self.btnSearch.sendActions(for: .touchUpInside)
    }
    
}

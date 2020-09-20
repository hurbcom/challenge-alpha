//
//  Spy.swift
//  hurb-testTests
//
//  Created by Tulio Parreiras on 20/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import Foundation

import HotelSearch

class Spy: HotelSearcher, ImageDataLoader {
    
    private var searchCompletions = [(HotelSearcher.SearchResult) -> Void]()
    var searchHotelCallCount: Int {
        return self.searchCompletions.count
    }
    
    // MARK: - Hotel Searcher
    
    func searchHotel(with searchText: String, completion: @escaping (HotelSearcher.SearchResult) -> Void) {
        self.searchCompletions.append(completion)
    }
    
    func completeHotelSearch(with hotels: [Hotel] = [], at index: Int = 0) {
        self.searchCompletions[index](.success(hotels))
    }
    
    // MARK: - Image Data Loader
    
    private var imageRequests = [(url: URL, completion: (ImageDataLoader.Result) -> Void)]()
    
    var loadedImageURLs: [URL] {
        return self.imageRequests.map { $0.url }
    }
    var cancelledImageURLs = [URL]()
    
    private struct MockTask: ImageDataLoaderTask {
        var completion: (() -> Void)
        func cancel() { self.completion() }
    }
    
    func loadImageData(from url: URL, completion: @escaping (ImageDataLoader.Result) -> Void) -> ImageDataLoaderTask {
        self.imageRequests.append((url, completion))
        return MockTask { [weak self] in self?.cancelledImageURLs.append(url) }
    }
    
    func completeImageLoading(with imageData: Data = Data(), at index: Int = 0) {
        imageRequests[index].completion(.success(imageData))
    }
    
    func completeImageLoadingWithError(at index: Int = 0) {
        let error = NSError(domain: "an error", code: 0)
        imageRequests[index].completion(.failure(error))
    }
    
}

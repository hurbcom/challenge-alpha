//
//  ViewSpy.swift
//  HotelSearchTests
//
//  Created by Tulio Parreiras on 19/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import Foundation

import HotelSearch

final class ViewSpy: HotelSearchView, HotelSearcher, ImageDataLoader {
    
    // MARK: - Hotel Search View
    
    enum Message: Hashable {
        case display(model: [[HotelViewModel]])
        case display(error: String)
        case display(loading: Bool)
        case display(imageData: Data, index: Int, section: Int)
        case display(imageLoading: Bool, index: Int, section: Int)
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
        messages.insert(.display(imageData: data, index: index, section: section))
    }
    
    func displayImageLoading(_ isLoading: Bool, for index: Int, section: Int) {
        messages.insert(.display(imageLoading: isLoading, index: index, section: section))
    }
    
    // MARK: - Hotel Searcher
    
    private var searchCompletions = [(HotelSearcher.SearchResult) -> Void]()
    
    func searchHotel(with searchText: String, completion: @escaping (SearchResult) -> Void) {
        self.searchCompletions.append(completion)
    }
    
    func completeHotelSearchWith(_ result: HotelSearcher.SearchResult, at index: Int = 0) {
        self.searchCompletions[index](result)
    }
    
    // MARK: - Image Data Loader
    
    private var imageLoadCompletions = [(ImageDataLoader.Result) -> Void]()
    
    private struct MockTask: ImageDataLoaderTask {
        func cancel() { }
    }
    
    func loadImageData(from url: URL, completion: @escaping (ImageDataLoader.Result) -> Void) -> ImageDataLoaderTask {
        self.imageLoadCompletions.append(completion)
        return MockTask()
    }
    
    func completeImageLoadWith(_ result: ImageDataLoader.Result, at index: Int = 0) {
        self.imageLoadCompletions[index](result)
    }
    
}

//
//  HotelSearchViewModel.swift
//  HotelSearch
//
//  Created by Tulio Parreiras on 16/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import Foundation

final public class HotelSearchViewModel {
    
    // MARK: - Properties
    
    weak public var hotelSearchView: HotelSearchView?
    public var text: String = ""
    
    public var imagesData = [[Int: Int]: Data]()
    
    private var hotels = [[Hotel]]()
    
    private let hotelSearcher: HotelSearcher
    private let imageDataLoader: ImageDataLoader
    private let searchSuffix = "&page="
    private var currentPage = 1
    
    // MARK: - Life Cycle
    
    public init(hotelSearcher: HotelSearcher, imageDataLoader: ImageDataLoader) {
        self.hotelSearcher = hotelSearcher
        self.imageDataLoader = imageDataLoader
    }
    
    // MARK: - Hotel Search
    
    public func searchHotel() {
        self.didStartSearchingHotel()
        let searchText = self.text + self.searchSuffix + String(describing: self.currentPage)
        self.hotelSearcher.searchHotel(with: searchText.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed) ?? searchText) { [weak self] result in
            guard let self = self else { return }
            switch result {
            case let .success(hotels):
                self.didFinishSearchingHotels(with: hotels)
            case let .failure(error):
                self.didFinishSearchingHotels(with: error)
            }
        }
    }
    
    private func didStartSearchingHotel() {
        self.cleanPreviousHotelsStates()
        self.hotelSearchView?.displayLoading(true)
    }
    
    private func didFinishSearchingHotels(with hotels: [Hotel]) {
        self.mapHotels(hotels)
        self.hotelSearchView?.displayLoading(false)
    }
    
    private func mapHotels(_ hotels: [Hotel]) {
        let sortedHotels = hotels.sorted(by: { ($0.stars ?? -1) > ($1.stars ?? -1)})
        let mappedHotels = sortedHotels.map { hotel in return sortedHotels.filter { $0.stars == hotel.stars} }
        let mappedHotelsWithoutDuplications = NSOrderedSet(array: mappedHotels).array as! [[Hotel]]
        self.hotels = mappedHotelsWithoutDuplications
        let viewModel = self.hotels.compactMap { $0.compactMap { HotelViewModel(hotel: $0) }}
        self.hotelSearchView?.display(viewModel)
    }
    
    private func didFinishSearchingHotels(with error: Error) {
        self.hotelSearchView?.displayError(error.localizedDescription)
        self.hotelSearchView?.displayLoading(false)
    }
    
    private func cleanPreviousHotelsStates() {
        self.hotels.removeAll()
        self.imagesData.removeAll()
        self.hotelSearchView?.display([])
    }
    
    // MARK: - Image Load
    
    public func loadImage(at index: Int, section: Int) -> ImageDataLoaderTask? {
        guard let url = self.getURL(forImageAt: index, section: section) else { return nil }
        self.didStartLoadingImage(at: index, section: section)
        return self.imageDataLoader.loadImageData(from: url) { [weak self] result in
            guard let self = self else { return }
            switch result {
            case let .success(data):
                self.didFinishLoadingImage(with: data, at: index, section: section)
            case let .failure(error):
                self.didFinishLoadingImage(with: error, at: index, section: section)
            }
        }
    }
    
    private func getURL(forImageAt index: Int, section: Int) -> URL? {
        guard self.hotels.count > section else { return nil }
        guard self.imagesData[[section: index]] == nil else { return nil }
        let hotel = self.hotels[section][index]
        guard let url = hotel.image ?? hotel.gallery?.first?.url else { return nil }
        return url
    }
    
    private func didStartLoadingImage(at index: Int, section: Int) {
        self.hotelSearchView?.displayImageLoading(true, for: index, section: section)
    }
    
    private func didFinishLoadingImage(with data: Data, at index: Int, section: Int) {
        self.imagesData[[section: index]] = data
        self.hotelSearchView?.displayImageData(data, for: index, section: section)
        self.hotelSearchView?.displayImageLoading(false, for: index, section: section)
    }
    
    private func didFinishLoadingImage(with error: Error, at index: Int, section: Int) {
        self.hotelSearchView?.displayImageLoading(false, for: index, section: section)
    }
    
}

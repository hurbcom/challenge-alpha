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
    
    public var imagesData = [Int: Data]()
    
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
        let searchText = self.text + self.searchSuffix + String(describing: self.currentPage)
        self.cleanPreviousHotelsStates()
        self.hotelSearchView?.displayLoading(true)
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
        guard self.hotels.count > index else { return nil }
        guard self.imagesData[index] == nil else { return nil }
        let hotel = self.hotels[section][index]
        guard let url = hotel.image ?? hotel.gallery?.first?.url else { return nil }
        self.hotelSearchView?.displayImageLoading(true, for: index, section: section)
        return self.imageDataLoader.loadImageData(from: url) { [weak self] result in
            DispatchQueue.main.async {
                guard let self = self else { return }
                switch result {
                case let .success(data):
                    self.imagesData[index] = data
                    self.hotelSearchView?.displayImageData(data, for: index, section: section)
                case let .failure(error):
                    print(error)
                }
                self.hotelSearchView?.displayImageLoading(false, for: index, section: section)
            }
        }
    }
    
}

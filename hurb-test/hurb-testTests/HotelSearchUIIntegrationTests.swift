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
        let (sut, spy) = makeSUT()
        sut.loadViewIfNeeded()
        
        XCTAssertEqual(spy.searchHotelCallCount, 0)
        sut.simulateHotelSearch()
        XCTAssertEqual(spy.searchHotelCallCount, 1)
    }
    
    func test_loadingHotelIndicator_isVisibleWhileSearchingHotels() {
        let (sut, spy) = makeSUT()
        sut.loadViewIfNeeded()
        XCTAssertFalse(sut.isShowingLoadingIndicator)
        
        sut.simulateHotelSearch()
        XCTAssertTrue(sut.isShowingLoadingIndicator)
        
        spy.completeHotelSearch()
        XCTAssertFalse(sut.isShowingLoadingIndicator)
    }
    
    func test_searchHotelCompletion_rendersSuccessfullyLoadedHotels() {
        let hotel0 = makeItem(name: "a name", stars: 4)
        let hotel1 = makeItem(name: "another name", stars: 4)
        let hotel2 = makeItem(address: Address(city: "city", country: nil, state: "state", street: nil, zipcode: nil), name: "a new name", stars: 4)
        let hotel3 = makeItem(amenities: [Amenity(category: nil, name: "amenity"), Amenity(category: nil, name: "a amenity")], stars: 3)
        let (sut, spy) = makeSUT()
        
        sut.loadViewIfNeeded()
        assertThat(sut, isRendering: [])
        
        sut.simulateHotelSearch()
        spy.completeHotelSearch(with: [hotel0])
        assertThat(sut, isRendering: [[hotel0]])
        
        sut.simulateHotelSearch()
        spy.completeHotelSearch(with: [hotel0, hotel1, hotel2, hotel3], at: 1)
        assertThat(sut, isRendering: [[hotel0, hotel1, hotel2], [hotel3]])
    }

    func test_searchHotelCompletion_rendersSuccessfullyLoadedEmptyHotelsAfterNonEmptyHotels() {
        let hotel0 = makeItem(stars: 3)
        let hotel1 = makeItem(stars: 3)
        let (sut, spy) = makeSUT()
        
        sut.loadViewIfNeeded()
        assertThat(sut, isRendering: [])

        sut.simulateHotelSearch()
        spy.completeHotelSearch(with: [hotel0, hotel1])
        assertThat(sut, isRendering: [[hotel0, hotel1]])
        
        sut.simulateHotelSearch()
        spy.completeHotelSearch(with: [], at: 1)
        assertThat(sut, isRendering: [])
    }

    func test_hotelCell_loadsImageURLWhenVisible() {
        let hotel0 = makeItem(image: URL(string: "http://url-0.com")!, stars: 5)
        let hotel1 = makeItem(image: URL(string: "http://url-1.com")!, stars: 5)
        let (sut, spy) = makeSUT()
        
        sut.loadViewIfNeeded()
        sut.simulateHotelSearch()
        spy.completeHotelSearch(with: [hotel0, hotel1])
        
        XCTAssertEqual(spy.loadedImageURLs, [], "Expected no image URL requests until views become visible")

        sut.simulateHotelCellVisible(at: 0, section: 0)
        XCTAssertEqual(spy.loadedImageURLs, [hotel0.image], "Expected first image URL request once first view becomes visible")
        
        sut.simulateHotelCellVisible(at: 1, section: 0)
        XCTAssertEqual(spy.loadedImageURLs, [hotel0.image, hotel1.image], "Expected second image URL request once second view also becomes visible")
    }
    
    // MARK: - Helpers
    
    private func makeSUT(file: StaticString = #file, line: UInt = #line) -> (sut: HotelSearchViewController, spy: Spy) {
        let spy = Spy()
        let sut = HotelSearchUIComposer.hotelSearchComposedWith(hotelSearcher: spy, imageDataLoader: spy)
        trackForMemoryLeaks(sut, file: file, line: line)
        trackForMemoryLeaks(spy, file: file, line: line)
        return (sut, spy)
    }
    
    private func makeItem(
        address: Address = Address(city: "a city", country: "a country", state: "a state", street: "a street", zipcode: "a zipcode"),
        amenities: [Amenity] = [Amenity(category: "a category", name: "a name")],
        category: String? = nil,
        description: String? = nil,
        gallery: [HotelImage] = [HotelImage(description: "a description", url: URL(string: "https://a-url.com")!)],
        id: String? = nil,
        image: URL? = nil,
        isHotel: Bool? = nil,
        name: String? = nil,
        price: HotelPrice = HotelPrice(amount: 12.34, amountPerDay: 43.21, currency: "a currency"),
        quantityDescriptors: QuantityDescriptor = QuantityDescriptor(maxAdults: 5, maxChildren: 6, maxFreeChildrenAge: 7),
        smallDescription: String? = nil,
        stars: Int,
        tags: [String] = [],
        url: URL? = nil) -> Hotel {
        return Hotel(address: address, amenities: amenities, category: category, description: description, gallery: gallery, id: id, image: image, isHotel: isHotel, name: name, price: price, quantityDescriptors: quantityDescriptors, smallDescription: smallDescription, stars: stars, tags: tags, url: url)
    }

    func assertThat(_ sut: HotelSearchViewController, isRendering hotels: [[Hotel]], file: StaticString = #file, line: UInt = #line) {
        sut.view.enforceLayoutCycle()
        
        hotels.enumerated().forEach { section, hotelArr in
            XCTAssertEqual(hotelArr.count, sut.numberOfRenderedHotelCells(inSection: section))
            hotelArr.enumerated().forEach { index, hotel in
                assertThat(sut, hasViewConfiguredFor: hotel, at: index, section: section)
            }
        }
    }
    
    func assertThat(_ sut: HotelSearchViewController, hasViewConfiguredFor hotel: Hotel, at index: Int, section: Int, file: StaticString = #file, line: UInt = #line) {
        let view = sut.hotelCell(at: index, section: section)
        
        guard let cell = view as? HotelCell else {
            return XCTFail("Expected \(HotelCell.self) instance, got \(String(describing: view)) instead", file: file, line: line)
        }
        
        let shouldNameBeVisible = hotel.name != nil
        let shouldLocationBeVisible = hotel.address?.city != nil || hotel.address?.state != nil
        let shouldAmenitiesBeVisible = (hotel.amenities?.count ?? 0) > 0
        let shouldPriceBeVisible = hotel.price?.amountPerDay != nil
        XCTAssertEqual(cell.isShowingName, shouldNameBeVisible, file: file, line: line)
        XCTAssertEqual(cell.isShowingLocation, shouldLocationBeVisible, file: file, line: line)
        XCTAssertEqual(cell.isShowingAmenities, shouldAmenitiesBeVisible, file: file, line: line)
        XCTAssertEqual(cell.isShowingPrice, shouldPriceBeVisible, file: file, line: line)
    }
    
    private class Spy: HotelSearcher, ImageDataLoader {
        
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
        
        var loadedImageURLs = [URL]()
        
        private struct MockTask: ImageDataLoaderTask {
            func cancel() { }
        }
        
        func loadImageData(from url: URL, completion: @escaping (ImageDataLoader.Result) -> Void) -> ImageDataLoaderTask {
            self.loadedImageURLs.append(url)
            return MockTask()
        }
        
    }

}

extension HotelSearchViewController {
    
    var isShowingLoadingIndicator: Bool {
        return self.spinner.isAnimating
    }
    
    func simulateHotelSearch() {
        self.btnSearch.sendActions(for: .touchUpInside)
    }
    
    func numberOfRenderedHotelCells(inSection section: Int = 0) -> Int {
        return tableView.numberOfRows(inSection: section)
    }
    
    func hotelCell(at row: Int, section: Int) -> UITableViewCell? {
        guard numberOfRenderedHotelCells(inSection: section) > row else {
            return nil
        }
        let ds = tableView.dataSource
        let index = IndexPath(row: row, section: section)
        return ds?.tableView(tableView, cellForRowAt: index)
    }
    
    @discardableResult
    func simulateHotelCellVisible(at index: Int, section: Int) -> HotelCell? {
        return hotelCell(at: index, section: section) as? HotelCell
    }
    
}

extension HotelCell {
    
    var isShowingName: Bool {
        return self.lblName.text != nil
    }
    var isShowingLocation: Bool {
        return self.lblLocation.text != nil
    }
    var isShowingAmenities: Bool {
        return self.lblAmenities.text != nil
    }
    var isShowingPrice: Bool {
        return self.lblPrice.text != nil
    }
    
}

extension UIView {
    func enforceLayoutCycle() {
        layoutIfNeeded()
        RunLoop.current.run(until: Date())
    }
}

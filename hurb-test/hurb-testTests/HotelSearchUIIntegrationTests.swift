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
    
    func test_hotelCell_cancelsImageLoadingWhenNotVisibleAnymore() {
        let hotel0 = makeItem(image: URL(string: "http://url-0.com")!, stars: 5)
        let hotel1 = makeItem(image: URL(string: "http://url-1.com")!, stars: 5)
        let (sut, spy) = makeSUT()
        
        sut.loadViewIfNeeded()
        sut.simulateHotelSearch()
        spy.completeHotelSearch(with: [hotel0, hotel1])
        XCTAssertEqual(spy.cancelledImageURLs, [], "Expected no cancelled image URL requests until image is not visible")
        
        sut.simulateHotelCellNotVisible(at: 0, section: 0)
        XCTAssertEqual(spy.cancelledImageURLs, [hotel0.image], "Expected one cancelled image URL request once first image is not visible anymore")
        
        sut.simulateHotelCellNotVisible(at: 1, section: 0)
        XCTAssertEqual(spy.cancelledImageURLs, [hotel0.image, hotel1.image], "Expected two cancelled image URL requests once second image is also not visible anymore")
    }
    
    func test_hotelCellImageLoadingIndicator_isVisibleWhileLoadingImage() {
        let (sut, spy) = makeSUT()
        
        sut.loadViewIfNeeded()
        sut.simulateHotelSearch()
        spy.completeHotelSearch(with: [makeItem(), makeItem()])
        
        let view0 = sut.simulateHotelCellVisible(at: 0, section: 0)
        let view1 = sut.simulateHotelCellVisible(at: 1, section: 0)
        XCTAssertEqual(view0?.isShowingImageLoadingIndicator, true, "Expected loading indicator for first view while loading first image")
        XCTAssertEqual(view1?.isShowingImageLoadingIndicator, true, "Expected loading indicator for second view while loading second image")
        
        spy.completeImageLoading(at: 0)
        XCTAssertEqual(view0?.isShowingImageLoadingIndicator, false, "Expected no loading indicator for first view once first image loading completes successfully")
        XCTAssertEqual(view1?.isShowingImageLoadingIndicator, true, "Expected no loading indicator state change for second view once first image loading completes successfully")
        
        spy.completeImageLoadingWithError(at: 1)
        XCTAssertEqual(view0?.isShowingImageLoadingIndicator, false, "Expected no loading indicator state change for first view once second image loading completes with error")
        XCTAssertEqual(view1?.isShowingImageLoadingIndicator, false, "Expected no loading indicator for second view once second image loading completes with error")
    }
    
    func test_hotelCell_rendersImageLoadedFromURL() {
        let (sut, spy) = makeSUT()
        
        sut.loadViewIfNeeded()
        sut.simulateHotelSearch()
        spy.completeHotelSearch(with: [makeItem(), makeItem()])
        
        let view0 = sut.simulateHotelCellVisible(at: 0, section: 0)
        let view1 = sut.simulateHotelCellVisible(at: 1, section: 0)
        XCTAssertEqual(view0?.renderedImage, .none, "Expected no image for first view while loading first image")
        XCTAssertEqual(view1?.renderedImage, .none, "Expected no image for second view while loading second image")
        
        let imageData0 = UIImage.make(withColor: .red).pngData()!
        spy.completeImageLoading(with: imageData0, at: 0)
        XCTAssertEqual(view0?.renderedImage, imageData0, "Expected image for first view once first image loading completes successfully")
        XCTAssertEqual(view1?.renderedImage, .none, "Expected no image state change for second view once first image loading completes successfully")
        
        let imageData1 = UIImage.make(withColor: .blue).pngData()!
        spy.completeImageLoading(with: imageData1, at: 1)
        XCTAssertEqual(view0?.renderedImage, imageData0, "Expected no image state change for first view once second image loading completes successfully")
        XCTAssertEqual(view1?.renderedImage, imageData1, "Expected image for second view once second image loading completes successfully")
    }

    func test_hotelCell_preloadsImageURLWhenNearVisible() {
        let hotel0 = makeItem(image: URL(string: "http://url-0.com")!, stars: 5)
        let hotel1 = makeItem(image: URL(string: "http://url-1.com")!, stars: 5)
        let (sut, spy) = makeSUT()
        
        sut.loadViewIfNeeded()
        sut.simulateHotelSearch()
        spy.completeHotelSearch(with: [hotel0, hotel1])
        XCTAssertEqual(spy.loadedImageURLs, [], "Expected no image URL requests until image is near visible")
        
        sut.simulateHotelCellNearVisible(at: 0, section: 0)
        XCTAssertEqual(spy.loadedImageURLs, [hotel0.image], "Expected first image URL request once first image is near visible")
        
        sut.simulateHotelCellNearVisible(at: 1, section: 0)
        XCTAssertEqual(spy.loadedImageURLs, [hotel0.image, hotel1.image], "Expected second image URL request once second image is near visible")
    }
    
    func test_hotelCell_cancelsImageURLPreloadingWhenNotNearVisibleAnymore() {
        let hotel0 = makeItem(image: URL(string: "http://url-0.com")!, stars: 5)
        let hotel1 = makeItem(image: URL(string: "http://url-1.com")!, stars: 5)
        let (sut, spy) = makeSUT()
        
        sut.loadViewIfNeeded()
        sut.simulateHotelSearch()
        spy.completeHotelSearch(with: [hotel0, hotel1])
        XCTAssertEqual(spy.cancelledImageURLs, [], "Expected no cancelled image URL requests until image is not near visible")
        
        sut.simulateHotelCellNotNearVisible(at: 0, section: 0)
        XCTAssertEqual(spy.cancelledImageURLs, [hotel0.image], "Expected first cancelled image URL request once first image is not near visible anymore")
        
        sut.simulateHotelCellNotNearVisible(at: 1, section: 0)
        XCTAssertEqual(spy.cancelledImageURLs, [hotel0.image, hotel1.image], "Expected second cancelled image URL request once second image is not near visible anymore")
    }
    
    func test_hotelCell_doesNotRenderLoadedImageWhenNotVisibleAnymore() {
        let (sut, spy) = makeSUT()
        sut.loadViewIfNeeded()
        sut.simulateHotelSearch()
        spy.completeHotelSearch(with: [makeItem()])

        let view = sut.simulateHotelCellNotVisible(at: 0, section: 0)
        spy.completeImageLoading(with: self.anyImageData())
        
        XCTAssertNil(view?.renderedImage, "Expected no rendered image when an image load finishes after the view is not visible anymore")
    }
    
    func test_searchHotelCompletion_dispatchesFromBackgroundToMainThread() {
        let (sut, spy) = makeSUT()
        sut.loadViewIfNeeded()
        sut.simulateHotelSearch()

        let exp = expectation(description: "Wait for background queue")
        DispatchQueue.global().async {
            spy.completeHotelSearch(at: 0)
            exp.fulfill()
        }
        wait(for: [exp], timeout: 1.0)
    }
    
    func test_loadImageDataCompletion_dispatchesFromBackgroundToMainThread() {
        let (sut, spy) = makeSUT()
        sut.loadViewIfNeeded()
        sut.simulateHotelSearch()
        spy.completeHotelSearch(with: [makeItem()])
        _ = sut.simulateHotelCellVisible(at: 0, section: 0)
        
        let exp = expectation(description: "Wait for background queue")
        DispatchQueue.global().async {
            spy.completeImageLoading(with: self.anyImageData(), at: 0)
            exp.fulfill()
        }
        wait(for: [exp], timeout: 1.0)
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
        stars: Int = 5,
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

    private func anyImageData() -> Data {
        return UIImage.make(withColor: .red).pngData()!
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
    func simulateHotelCellVisible(at row: Int, section: Int) -> HotelCell? {
        return hotelCell(at: row, section: section) as? HotelCell
    }
    
    @discardableResult
    func simulateHotelCellNotVisible(at row: Int, section: Int) -> HotelCell? {
        let view = simulateHotelCellVisible(at: row, section: section)
        
        let delegate = tableView.delegate
        let index = IndexPath(row: row, section: section)
        delegate?.tableView?(tableView, didEndDisplaying: view!, forRowAt: index)
        
        return view
    }
    
    func simulateHotelCellNearVisible(at row: Int, section: Int) {
        let ds = tableView.prefetchDataSource
        let index = IndexPath(row: row, section: section)
        ds?.tableView(tableView, prefetchRowsAt: [index])
    }
    
    func simulateHotelCellNotNearVisible(at row: Int, section: Int) {
        simulateHotelCellNearVisible(at: row, section: section)
        
        let ds = tableView.prefetchDataSource
        let index = IndexPath(row: row, section: section)
        ds?.tableView?(tableView, cancelPrefetchingForRowsAt: [index])
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
    
    var isShowingImageLoadingIndicator: Bool {
        return self.imageContainer.isShimmering
    }
    
    var renderedImage: Data? {
        return self.imvBackground.image?.pngData()
    }
    
}

extension UIView {
    func enforceLayoutCycle() {
        layoutIfNeeded()
        RunLoop.current.run(until: Date())
    }
}


extension UIImage {
    static func make(withColor color: UIColor) -> UIImage {
        let rect = CGRect(x: 0, y: 0, width: 1, height: 1)
        UIGraphicsBeginImageContext(rect.size)
        let context = UIGraphicsGetCurrentContext()!
        context.setFillColor(color.cgColor)
        context.fill(rect)
        let img = UIGraphicsGetImageFromCurrentImageContext()
        UIGraphicsEndImageContext()
        return img!
    }
}

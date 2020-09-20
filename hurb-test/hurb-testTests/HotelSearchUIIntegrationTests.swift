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
    
    func test_searchHotelActions_requestSearchAfterTextInputReturnEvent() {
        let (sut, spy) = makeSUT()
        sut.loadViewIfNeeded()
        
        XCTAssertEqual(spy.searchHotelCallCount, 0)
        sut.simulateTextInputReturn()
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
    
    func test_searchHotelCompletion_rendersSuccessfullyTableHeaders() {
        let hotel0 = makeItem(name: "a name", stars: 4)
        let hotel1 = makeItem(name: "another name", stars: 4)
        let hotel2 = makeItem(address: Address(city: "city", country: nil, state: "state", street: nil, zipcode: nil), name: "a new name", stars: 4)
        let hotel3 = makeItem(amenities: [Amenity(category: nil, name: "amenity"), Amenity(category: nil, name: "a amenity")], stars: 3)
        let hotel4 = makeItem(amenities: [Amenity(category: nil, name: "amenity"), Amenity(category: nil, name: "a amenity")], stars: nil)
        let (sut, spy) = makeSUT()
        
        sut.loadViewIfNeeded()
        assertThat(sut, isRendering: [])
        
        sut.simulateHotelSearch()
        spy.completeHotelSearch(with: [hotel0])
        assertThat(sut, isRenderingHeadersFor: [[hotel0]])
        
        sut.simulateHotelSearch()
        spy.completeHotelSearch(with: [hotel0, hotel1, hotel2, hotel3, hotel4], at: 1)
        assertThat(sut, isRenderingHeadersFor: [[hotel0, hotel1, hotel2], [hotel3], [hotel4]])
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
        stars: Int? = 5,
        tags: [String] = [],
        url: URL? = nil) -> Hotel {
        return Hotel(address: address, amenities: amenities, category: category, description: description, gallery: gallery, id: id, image: image, isHotel: isHotel, name: name, price: price, quantityDescriptors: quantityDescriptors, smallDescription: smallDescription, stars: stars, tags: tags, url: url)
    }

    private func anyImageData() -> Data {
        return UIImage.make(withColor: .red).pngData()!
    }

}

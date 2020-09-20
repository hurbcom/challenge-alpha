//
//  HotelSearchUIIntegrationTests+Assertions.swift
//  hurb-testTests
//
//  Created by Tulio Parreiras on 20/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import Foundation
import XCTest

import HotelSearch
import HotelSearchiOS

extension HotelSearchUIIntegrationTests {

    func assertThat(_ sut: HotelSearchViewController, isRendering hotels: [[Hotel]], file: StaticString = #file, line: UInt = #line) {
        sut.view.enforceLayoutCycle()
        
        hotels.enumerated().forEach { section, hotelArr in
            XCTAssertEqual(hotelArr.count, sut.numberOfRenderedHotelCells(inSection: section))
            hotelArr.enumerated().forEach { index, hotel in
                assertThat(sut, hasViewConfiguredFor: hotel, at: index, section: section, file: file, line: line)
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
    
    func assertThat(_ sut: HotelSearchViewController, isRenderingHeadersFor hotels: [[Hotel]], file: StaticString = #file, line: UInt = #line) {
        sut.view.enforceLayoutCycle()
        
        hotels.enumerated().forEach { section, hotelArr in
            assertThat(sut, hasHeaderConfiguredFor: hotelArr, at: section, file: file, line: line)
        }
    }
    
    func assertThat(_ sut: HotelSearchViewController, hasHeaderConfiguredFor hotels: [Hotel], at section: Int, file: StaticString = #file, line: UInt = #line) {
        let header = sut.headerView(at: section)
        
        guard let view = header as? HeaderView else {
            return XCTFail("Expected \(HeaderView.self) instance, got \(String(describing: header)) instead", file: file, line: line)
        }
        
        hotels.forEach { hotel in
            XCTAssertEqual(hotel.stars ?? 0, view.starsCount)
            let shouldHaveStars = (hotel.stars ?? 0) > 0
            XCTAssertEqual(shouldHaveStars, view.isShowingStars)
            XCTAssertNotEqual(shouldHaveStars, view.isShowingNoneMessage)
        }
    }
    
}

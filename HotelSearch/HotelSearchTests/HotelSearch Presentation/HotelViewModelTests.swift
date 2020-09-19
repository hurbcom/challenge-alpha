//
//  HotelViewModelTests.swift
//  HotelSearchTests
//
//  Created by Tulio Parreiras on 19/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import XCTest

import HotelSearch

class HotelViewModelTests: XCTestCase {

    func test_hotelViewModel_doesFormatDataAsExpected() {
        let address = Address(city: "a city", country: "a country", state: "a state", street: "a street", zipcode: "a zipcode")
        let amenities: [Amenity] = (0 ..< 6).map { Amenity(category: "category \($0)", name: "amenity \($0)") }
        let price = HotelPrice(amount: 12.34, amountPerDay: 12.34, currency: nil)
        let hotel = Hotel(address: address, amenities: amenities, category: nil, description: nil, gallery: nil, id: nil, image: nil, isHotel: nil, name: "a name", price: price, quantityDescriptors: nil, smallDescription: nil, stars: 5, tags: nil, url: nil)
        let sut = HotelViewModel(hotel: hotel)
        
        XCTAssertEqual(sut.stars, 5)
        XCTAssertEqual(sut.name, "a name")
        XCTAssertEqual(sut.location, "a city - a state")
        XCTAssertEqual(sut.amenities, "amenity 0 | amenity 1 | amenity 2")
        XCTAssertEqual(sut.price?.contains("12,34"), true)
    }

}

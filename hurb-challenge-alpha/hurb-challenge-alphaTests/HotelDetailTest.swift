//
//  HotelDetailTest.swift
//  hurb-challenge-alphaTests
//
//  Created by Hannah  on 27/12/2019.
//  Copyright © 2019 Hannah . All rights reserved.
//

import Foundation

import XCTest
@testable import hurb_challenge_alpha

class HotelDetailTest: XCTestCase {
    
    var hotel: Accommodation!
    var hotelDetailVM: HotelDetailViewModel!
    
    override func setUp() {
        hotel = DataContants.sharedInstance.hotelModelTest
        hotelDetailVM = HotelDetailViewModel(hotel: hotel)
    }
    
    func testNameHotelDetail() {
        XCTAssertEqual(hotel.name, hotelDetailVM.name)
    }
    
    func testCityHotelDetail() {
        XCTAssertEqual(hotel.address?.city, hotelDetailVM.city)
    }
    func testStateHotelDetail() {
        XCTAssertEqual(hotel.address?.state, hotelDetailVM.state)
    }
    
    func testStreetHotelDetail() {
        XCTAssertEqual(hotel.address?.street, hotelDetailVM.street)
    }
    
    func testDescriptionHotelDetail() {
        XCTAssertEqual(hotel.smallDescription, hotelDetailVM.smallDescription)
    }
    
    func testGetImageHotelDetail() {
        if let image = hotel.image {
            XCTAssertEqual(image, hotelDetailVM.urlImage)
        } else {
            if !hotel.gallery!.isEmpty {
                if let image = hotel.gallery?.first?.url {
                    XCTAssertEqual(image, hotelDetailVM.urlImage)
                }
            }
        }
    }
}

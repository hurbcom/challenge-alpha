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
    var hotelCellVM: HotelDetailViewModel!
    
    override func setUp() {
        hotel = DataContants.sharedInstance.hotelModelTest
        hotelCellVM = HotelDetailViewModel(hotel: hotel)
    }
    
    func testNameHotelDetail() {
        XCTAssertEqual(hotel.name, hotelCellVM.name)
    }
    
    func testCityHotelDetail() {
        XCTAssertEqual(hotel.address?.city, hotelCellVM.city)
    }
    func testStateHotelDetail() {
        XCTAssertEqual(hotel.address?.state, hotelCellVM.state)
    }
    
    func testStreetHotelDetail() {
        XCTAssertEqual(hotel.address?.street, hotelCellVM.street)
    }
    
    func testDescriptionHotelDetail() {
        XCTAssertEqual(hotel.smallDescription, hotelCellVM.smallDescription)
    }
    
    func testGetImageHotelDetail() {
        if let image = hotel.image {
            XCTAssertEqual(image, hotelCellVM.urlImage)
        } else {
            if !hotel.gallery!.isEmpty {
                if let image = hotel.gallery?.first?.url {
                    XCTAssertEqual(image, hotelCellVM.urlImage)
                }
            }
        }
    }
}

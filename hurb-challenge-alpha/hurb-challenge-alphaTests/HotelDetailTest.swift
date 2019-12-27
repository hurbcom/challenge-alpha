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
    
    func testDescriptionHotelDetail() {
        XCTAssertEqual(hotel.smallDescription, hotelDetailVM.smallDescription)
    }
    func testAddressHotelDetail() {
     var adress = String()
            if let street = self.hotel.address?.street {
                adress += " " + street
            }
            if let city = self.hotel.address?.city {
                adress += " - " + city
            }
            if let state = self.hotel.address?.state {
                adress += ", " + state
            }
            XCTAssertEqual(adress, hotelDetailVM.adress)
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

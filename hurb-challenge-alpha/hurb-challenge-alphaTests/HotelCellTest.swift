    //
    //  HotelCellTest.swift
    //  hurb-challenge-alphaTests
    //
    //  Created by Hannah  on 27/12/2019.
    //  Copyright © 2019 Hannah . All rights reserved.
    //
    import XCTest
    @testable import hurb_challenge_alpha
    
    class HotelCellTest: XCTestCase {
        
        var hotel: Accommodation!
        var hotelCellVM: HotelCellViewModel!
        
        override func setUp() {
            hotel = DataContants.sharedInstance.hotelModelTest
            hotelCellVM = HotelCellViewModel(hotel: hotel)
        }
        
        func testNameHotelCell() {
            XCTAssertEqual(hotel.name, hotelCellVM.name)
        }
        
        func testCityHotelCell() {
            XCTAssertEqual(hotel.address?.city, hotelCellVM.city)
        }
        
        func testPriceHotelCell() {
            XCTAssertEqual("R$" + String(Int((hotel.price?.amount)!)), hotelCellVM.price)
        }
        
        func testCancellationHotelCell() {
            XCTAssertEqual(hotel.huFreeCancellation, hotelCellVM.huFreeCancellation)
        }
        
        func testGetImageHotelCell() {
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
        
        func testDiscountHotelCell() {
            if let price = self.hotel.price {
                let oldPrice = price.oldPrice
                let currentPrice = price.amount
                if let oldPrice = oldPrice {
                    if let currentPrice = currentPrice {
                        if oldPrice > currentPrice {
                            let fractionalProgress = ((oldPrice - currentPrice) / oldPrice) * 100
                            let formatString = "- " + String(Int(fractionalProgress)) + "%"
                            XCTAssertEqual(formatString, hotelCellVM.discount)
                        }
                    }
                }
            }
        }
    }

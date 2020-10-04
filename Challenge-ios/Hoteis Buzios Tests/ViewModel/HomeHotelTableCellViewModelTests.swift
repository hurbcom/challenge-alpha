//
//  HomeHotelTableCellViewModelTests.swift
//  Hoteis Buzios Tests
//
//  Created by Andre Dias on 04/10/20.
//  Copyright © 2020 Andre Dias. All rights reserved.
//


@testable import Hotéis_Búzios
import XCTest

class HomeHotelTableCellViewModelTests: XCTestCase {
    
    var sut: HomeHotelTableCellViewModel!
    var hotelModel: HotelsResults!
      
    override func setUp() {
        super.setUp()
        
        if let hotelResult = StubGenerator().stubHotelsResults().first {
            self.sut = HomeHotelTableCellViewModel(hotelResult)
            self.hotelModel = hotelResult
        } else {
            XCTFail()
        }
    }

    override func tearDown() {
        self.sut = nil
        self.hotelModel = nil
        super.tearDown()
    }
      
      
    func testHotelImageURL() throws {
        XCTAssertNotNil(self.sut.hotelImageURL)
        XCTAssertEqual(self.sut.hotelImageURL!.absoluteString, "https://novo-hu.s3.amazonaws.com/reservas/ota/prod/hotel/2312/buzios-beach-resort-001_20200305164809.jpg" )
    }
    
    func testHotelName() throws {
        XCTAssert(!self.sut.hotelName.isEmpty)
        XCTAssertEqual(self.sut.hotelName, "Buzios Beach Resort")
    }
    
    func testHotelAddress() throws {
        XCTAssertEqual(self.sut.hotelAddress, "\(self.hotelModel.address!.city) / \(self.hotelModel.address!.state)")
    }
    
    func testHotelStars() throws {
        XCTAssertEqual(self.sut.stars, 4)
    }
    
    func testeHotelPrice() throws {
        if let hotelPriceFormatted = GenericSingleton.shared.currencyFormatter.string(from: (self.hotelModel.price?.currentPrice ?? 0) as NSNumber) {
            XCTAssertEqual(self.sut.hotelPrice, hotelPriceFormatted)
        } else {
            XCTFail()
        }
        
    }
    
    func testHotelAmenities() throws {
        var ametiesString = ""
        for (index, element) in self.hotelModel.amenities.enumerated() {
            if index == 3 {
                break
            } else {
                ametiesString.append("\(element.name)\n")
            }
        }
        XCTAssertEqual(self.sut.amenities, ametiesString)
    }
    
}


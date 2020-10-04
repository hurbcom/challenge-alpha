//
//  HotelsResultsTests.swift
//  Hoteis Buzios Tests
//
//  Created by Andre Dias on 02/10/20.
//  Copyright © 2020 Andre Dias. All rights reserved.
//
@testable import Hotéis_Búzios
import XCTest

class HotelsResultsTests: XCTestCase {
    
    var sut: [HotelsResults]!
    
    override func setUp() {
      super.setUp()
        self.sut = StubGenerator().stubHotelsResults()
    }

    override func tearDown() {
      sut = nil
      super.tearDown()
    }
    
    
    func testHotelsResultsReponse() throws {
        if let hotel = self.sut.first {
            XCTAssertTrue(hotel.isHotel)
            XCTAssertEqual(hotel.sku, "OMN-5252-0-0-0-0-0-0")
            XCTAssertEqual(hotel.smallDescription, "O Buzios Beach Resort está situado em Armação dos Búzios, Rio de Janeiro.")
            XCTAssertEqual(hotel.id, "AT2312")
            XCTAssertEqual(hotel.huFreeCancellation, true)
            XCTAssertEqual(hotel.name, "Buzios Beach Resort")
            XCTAssertEqual(hotel.stars, 4)
        }
    }
    
    func testHotelsPrice() throws {
        if let hotel = self.sut.first {
            if let hotelPrice = hotel.price {
                XCTAssertEqual(hotelPrice.amount, 598.89)
                XCTAssertEqual(hotelPrice.oldPrice, 598.89)
                XCTAssertEqual(hotelPrice.totalPrice, 598.89)
                XCTAssertEqual(hotelPrice.feeExtra, 0.0)
                XCTAssertEqual(hotelPrice.originalAmountPerDay, 598.89)
                XCTAssertEqual(hotelPrice.amountPerDay, 598.89)
                XCTAssertEqual(hotelPrice.currentPrice, 598.89)
            }
        }
    }
    
    func testHotelAmenities() throws {
        if let hotel = self.sut.first {
            if let firstAmenity = hotel.amenities.first {
                XCTAssertEqual(firstAmenity.name, "Sala de tv")
                XCTAssertEqual(firstAmenity.category, "Áreas comuns")
            }
           
            let secondAmenity = hotel.amenities[1]
            XCTAssertEqual(secondAmenity.name, "Sala de Jogos")
            XCTAssertEqual(secondAmenity.category, "Áreas comuns")
           
            let thirdAmenity = hotel.amenities[2]
            XCTAssertEqual(thirdAmenity.name, "Restaurante")
            XCTAssertEqual(thirdAmenity.category, "Comida / Bebida")
        }
    }
    
    func testHotelAddress() throws {
        if let hotel = self.sut.first {
            if let hotelAddress = hotel.address {
                XCTAssertEqual(hotelAddress.zipcode, "28950000")
                XCTAssertEqual(hotelAddress.street, "Avenida Dos Tucuns, S/N  Praia de Tucuns")
                XCTAssertEqual(hotelAddress.neighborhood, "Armação")
                XCTAssertEqual(hotelAddress.streetName, "Avenida Dos Tucuns, S/N  Praia de Tucuns")
                XCTAssertEqual(hotelAddress.address, "Avenida Dos Tucuns, S/N  Praia de Tucuns")
                XCTAssertEqual(hotelAddress.fullAddress, "Avenida Dos Tucuns, S/N  Praia de Tucuns")
                XCTAssertEqual(hotelAddress.state, "Rio de Janeiro")
                XCTAssertEqual(hotelAddress.city, "Armação dos Búzios")
              
                if let geoLocation = hotelAddress.geoLocation {
                    XCTAssertEqual(geoLocation.lat, -22.790146)
                    XCTAssertEqual(geoLocation.lon, -41.926743)
                }
            }
        }
    }
                  
}

//
//  PackageResultsTests.swift
//  Hoteis Buzios Tests
//
//  Created by Andre Dias on 02/10/20.
//  Copyright © 2020 Andre Dias. All rights reserved.
//

import XCTest

@testable import Hotéis_Búzios
import XCTest

class PackageResultsTests: XCTestCase {
    
    var sut: PackageResults!
        
    override func setUp() {
        super.setUp()
        self.sut = StubGenerator().stubPackageResults()
    }

    override func tearDown() {
        sut = nil
        super.tearDown()
    }
    
    
    func testPackageResultsReponse() throws {
        XCTAssertTrue(self.sut.isPackage)
        XCTAssertEqual(self.sut.sku, "LGPKG-872453-0")
        XCTAssertEqual(self.sut.smallDescription, "Aéreo + Hospedagem + Opção de Transfer")
        XCTAssertEqual(self.sut.id, "872453")
        XCTAssertEqual(self.sut.name, "Pacote Búzios - 2021")
        XCTAssertEqual(self.sut.category, "hospedagem")
    }
    
    func testHotelsPrice() throws {
        if let packagePrice = self.sut.price {
            XCTAssertEqual(packagePrice.oldPrice, 1298)
            XCTAssertEqual(packagePrice.currentPrice, 1298)
            XCTAssertEqual(packagePrice.sku, "LGPKG-872453-0")
            XCTAssertEqual(packagePrice.originalAmount, 1298)
            XCTAssertEqual(packagePrice.amountPerDay, 649)
            XCTAssertEqual(packagePrice.amount, 1298)
            
        }
    }
    
    func testHotelAmenities() throws {
        if let amenities = self.sut.amenities {
            if let firstAmenity = amenities.first {
                XCTAssertEqual(firstAmenity.name, "Apartamento")
                XCTAssertEqual(firstAmenity.category, "Acomodações")
            }
           
            let secondAmenity = amenities[1]
            XCTAssertEqual(secondAmenity.name, "Café da manhã")
            XCTAssertEqual(secondAmenity.category, "Alimentação")
           
            let thirdAmenity = amenities[2]
            XCTAssertEqual(thirdAmenity.name, "Passagem Aérea")
            XCTAssertEqual(thirdAmenity.category, "Passagem aérea")
        }
    }
    
    func testHotelAddress() throws {
        if let address = self.sut.address {
            XCTAssertEqual(address.state, "Rio de Janeiro")
            XCTAssertEqual(address.city, "Búzios")
              
            if let geoLocation = address.geoLocation {
                XCTAssertEqual(geoLocation.lat, -22.958415)
                XCTAssertEqual(geoLocation.lon, -43.303063)
            }
        }
    }
}

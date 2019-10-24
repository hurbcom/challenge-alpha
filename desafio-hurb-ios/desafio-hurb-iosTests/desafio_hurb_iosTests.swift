//
//  desafio_hurb_iosTests.swift
//  desafio-hurb-iosTests
//
//  Created by Vinícius Barcelos on 23/10/19.
//  Copyright © 2019 Vinícius Barcelos. All rights reserved.
//

import XCTest
@testable import desafio_hurb_ios

class desafio_hurb_iosTests: XCTestCase {
    
    func testHotelStarsLabel() {
        let hotelWithOneStar = Hotel(stars: 1)
        let hotelWithFourStars = Hotel(stars: 4)
        let package = Hotel(stars: nil)
        
        XCTAssertEqual(hotelWithOneStar.hotelStars(), "⭐")
        XCTAssertEqual(hotelWithFourStars.hotelStars(), "⭐⭐⭐⭐")
        XCTAssertEqual(package.hotelStars(), "PACOTE")
    }
    
    func testHotelFullAddress() {
        let hotelWithStreet = Hotel(address: Address(city: "Rio de Janeiro", state: "RJ", street: "Avenida das Américas"))
        let hotelWithoutStreet = Hotel(address: Address(city: "São Paulo", state: "SP", street: nil))
        
        XCTAssertEqual(hotelWithStreet.fullAdress(), "Avenida das Américas - Rio de Janeiro - RJ")
        XCTAssertEqual(hotelWithoutStreet.fullAdress(), "São Paulo - SP")
    }

}


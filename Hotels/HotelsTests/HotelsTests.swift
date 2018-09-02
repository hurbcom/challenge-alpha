//
//  HotelsTests.swift
//  HotelsTests
//
//  Created by Adolfho Athyla on 24/08/2018.
//  Copyright © 2018 a7hyla. All rights reserved.
//

import XCTest
@testable import Hotels

class HotelsTests: XCTestCase {
    
    var hotels = [Hotel]()
    
    override func setUp() {
        super.setUp()
        hotels = [
            Hotel(id: "1", isHotel: true, isPackage: false, name: "Hotel Fortaleza 5", stars: 4),
            Hotel(id: "2", isHotel: true, isPackage: false, name: "Hotel Mirante Parque", stars: 5),
            Hotel(id: "3", isHotel: false, isPackage: true, name: "Pacote Canoa Quebrada", stars: nil),
            Hotel(id: "4", isHotel: true, isPackage: false, name: "Hotel Parque Luz", stars: 3),
            Hotel(id: "5", isHotel: false, isPackage: true, name: "Pacote Jeri", stars: nil),
            Hotel(id: "6", isHotel: true, isPackage: false, name: "Hotel Praia", stars: 4),
            Hotel(id: "7", isHotel: true, isPackage: false, name: "Hotel Rio Mar", stars: 5)
        ]
    }
    
    override func tearDown() {
        // Put teardown code here. This method is called after the invocation of each test method in the class.
        super.tearDown()
    }
    
    func testPerformanceExample() {
        // This is an example of a performance test case.
        self.measure {
            // Put the code you want to measure the time of here.
        }
    }
    
    func testMethodGetOnlyHotels() {
        /*
         > Foram criados 7 itens: 5 hotéis e 2 pacotes
         > Resultado esperado: 5 hotéis
        */
        let hotels = Hotel.getHotels(hotels: self.hotels)
        XCTAssertEqual(hotels.count, 5)
    }
    
    func testMethodGetOnlyPackages() {
        /*
         > Foram criados 7 itens: 5 hotéis e 2 pacotes
         > Resultado esperado: 2 pacotes
        */
        let packages = Hotel.getPackages(hotels: self.hotels)
        XCTAssertEqual(packages.count, 2)
    }
    
    func testMethodExistsPackage() {
        /*
         > Foram criados 2 pacotes de viagem
         > Resultado esperado: True
        */
        let exists = Hotel.existsPackage(hotels: self.hotels)
        XCTAssertEqual(exists, true)
    }
    
    func testMethodGetHotelsWith3Stars() {
        /*
         > Foi criado apenas 1 hotél com 3 estrelas
         > Resultado esperado: 1 hotél
        */
        let hotels = Hotel.getHotelsWith(stars: 3, hotels: self.hotels)
        XCTAssertEqual(hotels.count, 1)
    }
    
    func testMethodGetHotelsWith4Stars() {
        /*
         > Foram criados 2 hotéis com 4 estrelas
         > Resultado esperado: 2 hotéis
        */
        let hotels = Hotel.getHotelsWith(stars: 4, hotels: self.hotels)
        XCTAssertEqual(hotels.count, 2)
    }
    
    func testMethodgetHotelsWith5Stars() {
        /*
         > Foram criados 2 hotéis com 5 estrelas
         > Resultado esperado: 2 hotéis
        */
        let hotels = Hotel.getHotelsWith(stars: 5, hotels: self.hotels)
        XCTAssertEqual(hotels.count, 2)
    }
    
    func testMethodGetStarsThatContainHotels() {
        /*
         > Foram criados hotéis com 3, 4 e 5 estrelas
         > Resultado esperado: [5, 4, 3]
        */
        let stars = Hotel.getStarsThatContainHotels(hotels: self.hotels)
        XCTAssertEqual(stars, [5, 4, 3])
    }
    
}

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
    
    func testPackageResultsReponse() throws {
        guard let url = Bundle(for: type(of: self)).url(forResource: "PackageResultsJsonResponse", withExtension: "json")
            else { fatalError("Can't find search.json file") }
        do {
            let data = try Data(contentsOf: url)
            let decoder = JSONDecoder()
            let packageArray = try decoder.decode(PackageResults.self, from: data)
            XCTAssertTrue(packageArray.isPackage)
            
//            if let hotel = hotelsArray.first {
//                XCTAssertTrue(packageArray.isPackage)
//                XCTAssertEqual(hotel.sku, "OMN-5252-0-0-0-0-0-0")
//                XCTAssertEqual(hotel.smallDescription, "O Buzios Beach Resort está situado em Armação dos Búzios, Rio de Janeiro.")
//                XCTAssertEqual(hotel.id, "AT2312")
//                XCTAssertEqual(hotel.huFreeCancellation, true)
//                XCTAssertEqual(hotel.name, "Buzios Beach Resort")
//                XCTAssertEqual(hotel.stars, 4)
//
//
//                if let hotelPrice = hotel.price {
//                    XCTAssertEqual(hotelPrice.amount, 598.89)
//                    XCTAssertEqual(hotelPrice.oldPrice, 598.89)
//                    XCTAssertEqual(hotelPrice.totalPrice, 598.89)
//                    XCTAssertEqual(hotelPrice.feeExtra, 0.0)
//                    XCTAssertEqual(hotelPrice.originalAmountPerDay, 598.89)
//                    XCTAssertEqual(hotelPrice.amountPerDay, 598.89)
//                    XCTAssertEqual(hotelPrice.currentPrice, 598.89)
//
//                }
//
//                if let firstAmenity = hotel.amenities.first {
//                    XCTAssertEqual(firstAmenity.name, "Sala de tv")
//                    XCTAssertEqual(firstAmenity.category, "Áreas comuns")
//                }
//
//                let secondAmenity = hotel.amenities[1]
//                XCTAssertEqual(secondAmenity.name, "Sala de Jogos")
//                XCTAssertEqual(secondAmenity.category, "Áreas comuns")
//
//                let thirdAmenity = hotel.amenities[2]
//                XCTAssertEqual(thirdAmenity.name, "Restaurante")
//                    XCTAssertEqual(thirdAmenity.category, "Comida / Bebida")
//
//
//                if let hotelAddress = hotel.address {
//                    XCTAssertEqual(hotelAddress.zipcode, "28950000")
//                    XCTAssertEqual(hotelAddress.street, "Avenida Dos Tucuns, S/N  Praia de Tucuns")
//                    XCTAssertEqual(hotelAddress.neighborhood, "Armação")
//                    XCTAssertEqual(hotelAddress.streetName, "Avenida Dos Tucuns, S/N  Praia de Tucuns")
//                    XCTAssertEqual(hotelAddress.address, "Avenida Dos Tucuns, S/N  Praia de Tucuns")
//                    XCTAssertEqual(hotelAddress.fullAddress, "Avenida Dos Tucuns, S/N  Praia de Tucuns")
//                    XCTAssertEqual(hotelAddress.state, "Rio de Janeiro")
//                    XCTAssertEqual(hotelAddress.city, "Armação dos Búzios")
//
//                    if let geoLocation = hotelAddress.geoLocation {
//                        XCTAssertEqual(geoLocation.lat, -22.790146)
//                        XCTAssertEqual(geoLocation.lon, -41.926743)
//                    }
//                }
//
//
//            }
            

        } catch {
            print("error:\(error)")
        }

    }
}

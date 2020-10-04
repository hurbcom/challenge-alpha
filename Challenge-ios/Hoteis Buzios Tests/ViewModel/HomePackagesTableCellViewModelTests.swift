//
//  HomePackagesTableCellViewModelTests.swift
//  Hoteis Buzios Tests
//
//  Created by Andre Dias on 04/10/20.
//  Copyright © 2020 Andre Dias. All rights reserved.
//


@testable import Hotéis_Búzios
import XCTest

class HomePackagesTableCellViewModelTests: XCTestCase {
    
    var sut: HomePackagesTableCellViewModel!
    var packageModel: PackageResults!
      
    override func setUp() {
        super.setUp()
        
        if let packagesResult = StubGenerator().stubPackageResults().first {
            self.sut = HomePackagesTableCellViewModel(packagesResult)
            self.packageModel = packagesResult
        } else {
            XCTFail()
        }
    }

    override func tearDown() {
        self.sut = nil
        self.packageModel = nil
        super.tearDown()
    }
      
      
    func testPackageImageURL() throws {
        XCTAssertNotNil(self.sut.packageImageURL)
        XCTAssertEqual(self.sut.packageImageURL!.absoluteString, "https://s3.amazonaws.com/legado-prod/prod/ofertas/imagens/2020/03/26/19/49/armacao_dos_buzios_capa.jpg")
    }
    
    func testPackageName() throws {
        XCTAssert(!self.sut.packageName.isEmpty)
        XCTAssertEqual(self.sut.packageName, "Pacote Búzios - 2021")
    }
    
    func testPackageSmallDescription() throws {
        XCTAssert(!self.sut.smallDescription.isEmpty)
        XCTAssertEqual(self.sut.smallDescription, "Aéreo + Hospedagem + Opção de Transfer")
    }
    
    func testPackageAddress() throws {
        XCTAssertEqual(self.sut.packageDestination, "\(self.packageModel.address!.city) / \(self.packageModel.address!.state)")
    }
    
    func testePackagePrice() throws {
        if let hotelPriceFormatted = GenericSingleton.shared.currencyFormatter.string(from: (self.packageModel.price?.currentPrice ?? 0) as NSNumber) {
            XCTAssertEqual(self.sut.packagePrice, hotelPriceFormatted)
        } else {
            XCTFail()
        }
        
    }
    
    func testPackageAmenities() throws {
        var ametiesString = ""
        for (index, element) in self.packageModel.amenities!.enumerated() {
            if index == 3 {
                XCTFail()
                break
            } else {
                ametiesString.append("\(element.name)\n")
            }
        }
        XCTAssertEqual(self.sut.amenities, ametiesString)
    }
    
    func testPackageQuantityDescriptor() throws {
        var nightsString = ""
        var maxPeopleString = ""
           
        if let quantityDescriptor = self.packageModel.quantityDescriptors {
            if quantityDescriptor.nights == 1 {
                nightsString = "\(quantityDescriptor.nights) diária"
            } else {
                nightsString = "\(quantityDescriptor.nights) diárias"
            }
            if quantityDescriptor.maxPeople == 1 {
                maxPeopleString = "\(quantityDescriptor.maxPeople) pessoa"
            } else {
                maxPeopleString = "\(quantityDescriptor.maxPeople) pessoas"
            }
            XCTAssertEqual(self.sut.quantityDescriptors, "\(nightsString), \(maxPeopleString)")
        } else {
            XCTFail()
        }
    }
    
    

    
}



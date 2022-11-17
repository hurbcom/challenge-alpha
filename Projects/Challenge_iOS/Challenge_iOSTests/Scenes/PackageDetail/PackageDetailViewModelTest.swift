//
//  PackageDetailViewModelTest.swift
//  Challenge_iOSTests
//
//  Created by Helio Junior on 16/11/22.
//

import XCTest
@testable import Challenge_iOS

class PackageDetailViewModelTest: XCTestCase {

    var sut: PackageDetailViewModel!
    
    override func setUpWithError() throws {
        let model = searchResulPackageModelMock
        sut = PackageDetailViewModel(model: model)
    }

    override func tearDownWithError() throws {
        sut = nil
    }

    func test_AllReturnValues() {
        XCTAssertEqual(sut.getName(), "Pacote de Viagem - CDesign Hotel (Rio de Janeiro) - 2023")
        XCTAssertEqual(sut.getValue(), "1299")
        XCTAssertEqual(sut.getAddress(), "Rio de Janeiro")
        XCTAssertEqual(sut.getPeriod(), "Oct/22 e Sep/23")
        
        let coordinate = sut.getCoordinate()
        XCTAssertEqual(coordinate?.lat, -22.913885042711016)
        XCTAssertEqual(coordinate?.lon, -43.726179230004568)
        
        XCTAssertEqual(sut.getImagesURL().count, 2)
        XCTAssertEqual(sut.getAmenities().count, 3)
    }
}

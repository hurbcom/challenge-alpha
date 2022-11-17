//
//  PackageDetailViewControllerTest.swift
//  Challenge_iOSTests
//
//  Created by Helio Junior on 16/11/22.
//

import XCTest
@testable import Challenge_iOS

class PackageDetailViewControllerTest: XCTestCase {

    var sut: PackageDetailViewController!
    
    override func setUpWithError() throws {
        let model = searchResulPackageModelMock
        sut = PackageDetailViewController(model: model)
        _ = sut.view
    }

    override func tearDownWithError() throws {
        sut = nil
    }

    func test_WhenStarted_ThenLoadViews() {
        
        XCTAssertEqual(sut.labelName.text, "Pacote de Viagem - CDesign Hotel (Rio de Janeiro) - 2023")
        XCTAssertEqual(sut.labelValue.text, "1299")
        XCTAssertEqual(sut.labelAddress.text, "Rio de Janeiro")
    }
    
    func test_WhenStarted_ThenCheckIfCollectionViewsRenderCells() {
        XCTAssertEqual(sut.collectionViewGallery.numberOfItems(inSection: 0), 2)
        XCTAssertEqual(sut.collectionViewAmenities.numberOfItems(inSection: 0), 3)
    }
    
    func test_WhenStarted__ThenRenderAndIdentifierImagensCells() {
        let indexPath = IndexPath(row: 0, section: 0)
        let collectionView = sut.collectionViewGallery!

        if let cell = sut.collectionViewGallery
            .dataSource?
            .collectionView(collectionView, cellForItemAt: indexPath) as? ImageCollectionViewCell {

            XCTAssertNotNil(cell.imageView)
        } else {
            XCTFail()
        }
    }
    
    func test_WhenStarted_ThenRenderAndIdentifierAmenitiesCells() {
        let indexPath = IndexPath(row: 0, section: 0)
        let collectionView = sut.collectionViewAmenities!

        if let cell = sut.collectionViewAmenities
            .dataSource?
            .collectionView(collectionView, cellForItemAt: indexPath) as? PackageAmenitieCollectionViewCell {

            XCTAssertEqual(cell.labelAmenitie.text, "Hospedagem")
        }
    }
}

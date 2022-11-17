//
//  CardPackageTableViewCellTest.swift
//  Challenge_iOSTests
//
//  Created by Helio Junior on 16/11/22.
//

import XCTest
@testable import Challenge_iOS

class CardPackageTableViewCellTest: XCTestCase {

    var sut: CardPackageTableViewCell!

    override func setUpWithError() throws {
        sut = CardPackageTableViewCell.fromNib()
        _ = sut
    }

    override func tearDownWithError() throws {
        sut = nil
    }
    
    func test_RenderCell() {
        sut.setupWith(model: searchResulPackageModelMock)
        XCTAssertEqual(sut.labelName.text, "Pacote de Viagem - CDesign Hotel (Rio de Janeiro) - 2023")
        XCTAssertEqual(sut.labelValue.text, "R$ 1.299")
        XCTAssertEqual(sut.labelAddress.text, "Rio de Janeiro, Brasil")
        XCTAssertEqual(sut.labelDailys.text, "1 diária")
        XCTAssertEqual(sut.labelPersons.text, "2 pessoas")
    }
    
    func test_RenderImagensCells() {
        sut.setupWith(model: searchResulPackageModelMock)
        let indexPath = IndexPath(row: 0, section: 0)
        let collectionView = sut.collectionViewGallery!
        
        if let cell = sut.collectionViewGallery
            .dataSource?
            .collectionView(collectionView, cellForItemAt: indexPath) as? ImageCollectionViewCell {

            XCTAssertNotNil(cell.imageView)
        }
    }
}

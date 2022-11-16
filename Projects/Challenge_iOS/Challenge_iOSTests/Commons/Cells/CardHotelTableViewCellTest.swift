//
//  CardHotelTableViewCellTest.swift
//  Challenge_iOSTests
//
//  Created by Helio Junior on 15/11/22.
//

import XCTest
@testable import Challenge_iOS

class CardHotelTableViewCellTest: XCTestCase {

    var sut: CardHotelTableViewCell!

    override func setUpWithError() throws {
        sut = CardHotelTableViewCell.fromNib()
        _ = sut
    }

    override func tearDownWithError() throws {
        sut = nil
    }
    
    func test_RenderCell() {
        sut.setupWith(model: searchResulHotelModelMock)
        
        XCTAssertEqual(sut.labelName.text, "Imperial Plaza Hotel")
        XCTAssertEqual(sut.labelValue.text, "R$ 158")
        XCTAssertEqual(sut.labelAddress.text, "Brasília, Brasil")
        XCTAssertEqual(sut.labelStars.text, "Hotel 4 estrelas")
        XCTAssertEqual(sut.labelSecondAmenitie.text, "Estacionamento")
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

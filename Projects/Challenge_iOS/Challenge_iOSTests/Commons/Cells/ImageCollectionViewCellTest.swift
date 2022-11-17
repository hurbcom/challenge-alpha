//
//  ImageCollectionViewCellTest.swift
//  Challenge_iOSTests
//
//  Created by Helio Junior on 15/11/22.
//

import XCTest
@testable import Challenge_iOS

class ImageCollectionViewCellTest: XCTestCase {
    
    var sut: ImageCollectionViewCell!

    override func setUpWithError() throws {
        sut = ImageCollectionViewCell.fromNib()
        _ = sut.imageView
    }

    override func tearDownWithError() throws {
        sut = nil
    }
    
    func test_inflateImageOnCell() {
        let promesse = expectation(description: "Espero que a imageView seja inflada com uma imagem a partir da URL.")
        
        sut.setup(with: "https://thumbcdn-z.hotelurbano.net/b1UA0AzQ5ztR0YyYvKsldRLCUPc=/origxorig/center/middle/filters:quality(70)/https://novo-hu.s3.amazonaws.com/reservas/ota/prod/hotel/519158/2016072814697157749_20180131144941.jpg")
        
        DispatchQueue.main.asyncAfter(deadline: .now() + 2) {
            XCTAssertNotNil(self.sut.imageView.image)
            promesse.fulfill()
        }
        wait(for: [promesse], timeout: 3)
    }
}

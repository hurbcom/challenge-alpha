//
//  Hurb_iOS_ChallengeTests.swift
//  Hurb_iOS_ChallengeTests
//
//  Created by Gáudio Ney on 12/05/22.
//

import XCTest
@testable import Hurb_iOS_Challenge

class Hurb_iOS_ChallengeTests: XCTestCase {
    
    var sut: HomeViewModel?
    var mock: HotelResult?

    override func setUp() {
        sut = HomeViewModel()
        mock = HotelResult(sku: "0101", isHotel: true, category: ResultCategory.hotel,
                           smallDescription: "Description", amenities: [ResultAmenity.init(name: "TV a cabo", category: AmenityCategory.entretenimentoEServiçosParaFamílias)],
                           id: "10100111", price: nil, huFreeCancellation: true,
                           image: "http://media.omnibees.com/Images/8006/RoomTypes/339196.jpg",
                           name: "Nome do Hotel", url: "https://www.hurb.com/hoteis/gramado/pousada-brisa-de-gramado-OMN-9241", resultDescription: "result", stars: 1000000, gallery: [Gallery(galleryDescription: "", url: "http://media.omnibees.com/Images/8006/RoomTypes/339196.jpg")],
                           address: nil, tags: nil, quantityDescriptors: nil, featuredItem: nil)
    }

    override func tearDown() {
        sut = nil
    }

    /// Test ViewModel Free Cancelation Text.
    func testViewModelFreeCancelationReturn() {
        /// Given:
        sut?.hotelResult = mock
        
        /// When:
        let isFreeCancelationTextCorrect = sut?.freeCancelingText

        /// Then:
        XCTAssertEqual(isFreeCancelationTextCorrect, "Cancelamento gratuito",
                       "The imput text is not a String type or something is wrong.")
    }
    
    /// Test Hotel Stars Text.
    func testViewModelHotelStarsReturn() {
        /// Given:
        sut?.hotelResult = mock
        
        /// When:
        let hotelStars = sut?.hotelStars
        /// Then:
        XCTAssertEqual(hotelStars, "★ 1000000.0",
                       "The imput text is not a String type or something is wrong.")
    }
    
    /// Test the HTTPS correctin for the URLs from image Gallery.
    func testViewModelHotelImagesURLStrigForHTTPS() {
        /// Given:
        sut?.hotelResult = mock
        
        /// When:
        let imageURL = sut?.hotelsImage
        
        /// Then:
        XCTAssertEqual(imageURL, ["https://media.omnibees.com/Images/8006/RoomTypes/339196.jpg"],
                       "The imput text is not a Array of Strings type or something is wrong.")
    }
}

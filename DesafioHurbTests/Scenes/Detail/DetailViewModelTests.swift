//
//  DetailViewModelTests.swift
//  DesafioHurb
//
//  Created by Edson Aparecido Guido on 19/09/20.
//  Copyright © 2020 Edson Aparecido Guido. All rights reserved.
//

@testable import DesafioHurb
import RxCocoa
import RxSwift
import RxTest
import XCTest

final class DetailViewModelTests: XCTestCase {
    private var sut: DetailViewModel!
    private var disposeBag: DisposeBag!
    private var interactor: DetailInteractorMock!
    
    private var hotelDisplay: TestableObserver<HotelDetailDisplay>!
    private var selectedImage: TestableObserver<URL?>!
    
    private var hotelMock: Hotel!
    
    override func setUp() {
        super.setUp()
        setupData()
        setupDetailViewModel()
    }
    
    private func setupData() {
        hotelMock = Hotel(sku: "",
                          isHotel: false,
                          category: "",
                          smallDescription: "",
                          amenities: [Amenity(name: "", category: "")],
                          id: "1234",
                          price: Price(amount: 200.0,
                                       priceOldPrice: 300.0,
                                       currency: "",
                                       currencyOriginal: "",
                                       gain: 0,
                                       feeExtraOriginal: 0,
                                       gainOriginal: 0,
                                       tariffPolicies: [""],
                                       priceCurrentPrice: 0,
                                       totalPrice: 0,
                                       feeExtra: 0,
                                       sku: "",
                                       taxes: nil,
                                       originalAmountPerDay: 0,
                                       amountPerDay: 0,
                                       oldPrice: 0,
                                       currentPrice: 0,
                                       originalAmount: 0),
                          huFreeCancellation: false,
                          image: "",
                          name: "",
                          url: "",
                          resultDescription: "",
                          stars: 0,
                          gallery: [Gallery(galleryDescription: "", url: "https://www.google.com/", roomID: "")],
                          address: Address(zipcode: "",
                                           addressFullAddress: "",
                                           street: "",
                                           addressStreetName: "",
                                           streetName: "",
                                           address: "",
                                           fullAddress: "",
                                           neighborhood: "",
                                           idAtlasNeighborhood: "",
                                           idNeighborhood: 0,
                                           city: "",
                                           idAtlasCity: 0,
                                           idCity: 0,
                                           state: "",
                                           idAtlasState: 0,
                                           idState: 0,
                                           country: "",
                                           idAtlasCountry: 0,
                                           idCountry: 0,
                                           geoLocation: GeoLocation(lat: 25.000000, lon: 28.000000)),
                          quantityDescriptors: QuantityDescriptors(maxChildren: 0,
                                                                   maxAdults: 0,
                                                                   maxFreeChildrenAge: 0,
                                                                   nights: 0,
                                                                   maxPeople: 0),
                          featuredItem: FeaturedItem(amenities: [""],
                                                     name: "",
                                                     image: "",
                                                     featuredItemDescription: "",
                                                     hasInternet: false,
                                                     hasParking: false),
                          isPackage: false,
                          startDate: "",
                          endDate: "",
                          hasAvailability: true)
    }
    
    private func setupDetailViewModel() {
        disposeBag = DisposeBag()
        interactor = DetailInteractorMock()
        sut = DetailViewModel(interactor: interactor, hotel: hotelMock)
        
        let testScheduler = TestScheduler(initialClock: 0)
        
        hotelDisplay = testScheduler.createObserver(HotelDetailDisplay.self)
        sut.output.hotelDisplay.drive(hotelDisplay).disposed(by: disposeBag)
        
        selectedImage = testScheduler.createObserver(URL?.self)
        sut.output.selectedImage.drive(selectedImage).disposed(by: disposeBag)
        
        testScheduler.start()
    }
    
    func test_ShouldEmit_HotelDisplay_When_FetchHotel() {
        
        let expected = HotelDetailDisplay(hotel: hotelMock)
        
        sut.input.fetchHotel.onNext(())
        
        XCTAssertEqual(
            hotelDisplay.events.compactMap { $0.value.element },
            [expected]
        )
    }
    
    func test_ShouldEmit_SelectedImage_When_SelectImage() {
        
        let expected = URL(string: hotelMock.gallery.first!.url)
        
        sut.input.fetchHotel.onNext(())
        sut.input.selectImage.onNext(0)
        
        XCTAssertEqual(
            selectedImage.events.compactMap { $0.value.element },
            [expected]
        )
    }
}

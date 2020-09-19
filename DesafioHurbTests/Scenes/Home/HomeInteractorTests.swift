//
//  HomeInteractorTests.swift
//  DesafioHurb
//
//  Created by Edson Aparecido Guido on 15/09/20.
//  Copyright © 2020 Edson Aparecido Guido. All rights reserved.
//

@testable import DesafioHurb
import RxCocoa
import RxSwift
import RxTest
import XCTest

final class HomeInteractorTests: XCTestCase {
    private var sut: HomeInteractor!
    private var disposeBag: DisposeBag!
    private var fetchHotelsUseCaseMock: FetchHotelsUseCaseMock!
    
    override func setUp() {
        super.setUp()
        setupHomeInteractor()
    }
    
    private func setupHomeInteractor() {
        disposeBag = DisposeBag()
        fetchHotelsUseCaseMock = FetchHotelsUseCaseMock()
        sut = HomeInteractor(fetchHotelsUseCase: fetchHotelsUseCaseMock)
    }
    
    func test_FetchHotels() {
        
        let expected = FetchHotelsResponse(
            meta: Meta(count: 0,
                       offset: 0,
                       query: "",
                       warning: "",
                       countWithAvailability: 8,
                       countWithAvailabilityInPage: 5,
                       countHotel: 0,
                       countPackage: 0,
                       countTicket: 0,
                       countBustrip: 0,
                       countDisney: 0),
            filters: Filters(
                amenities: [Filter(term: "", filter: "", count: 1)],
                attributes: [Filter(term: "", filter: "", count: 1)],
                countries: [Filter(term: "", filter: "", count: 1)],
                cities: [Filter(term: "", filter: "", count: 1)],
                departureCities: [Filter(term: "", filter: "", count: 1)],
                duration: [Filter(term: "", filter: "", count: 1)],
                food: [Filter(term: "", filter: "", count: 1)],
                people: [Filter(term: "", filter: "", count: 1)],
                prices: [PriceElement(min: 0, maxExclusive: 0, filter: "", count: 0)],
                priceInterval: PriceInterval(min: 0, max: 0, filterPattern: ""),
                productType: [Filter(term: "", filter: "", count: 1)],
                regulation: [Filter(term: "", filter: "", count: 1)],
                rooms: [Filter(term: "", filter: "", count: 1)],
                stars: [Filter(term: "", filter: "", count: 1)],
                states: [Filter(term: "", filter: "", count: 1)]),
            results: [
                Hotel(sku: "",
                      isHotel: false,
                      category: "",
                      smallDescription: "",
                      amenities: [Amenity(name: "", category: "")],
                      id: "",
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
                      gallery: [Gallery(galleryDescription: "", url: "", roomID: "")],
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
            ],
            pagination: Pagination(count: 0,
                                   previousPage: "",
                                   firstPage: "",
                                   nextPage: "",
                                   lastPage: ""))
        
        fetchHotelsUseCaseMock.requestReturnValue = expected
        
        sut.fetchHotels(page: 10)
            .subscribe(
                onSuccess: { value in
                    XCTAssertEqual(
                        value,
                        expected
                    )
            },
                onError: { _ in
                    XCTFail()
            })
            .disposed(by: disposeBag)
        
    }
}

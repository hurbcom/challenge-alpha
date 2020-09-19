//
//  HomeViewModelTests.swift
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

final class HomeViewModelTests: XCTestCase {
    private var sut: HomeViewModel!
    private var disposeBag: DisposeBag!
    private var interactor: HomeInteractorMock!
    
    private var error: TestableObserver<Error>!
    private var hotelsPackages: TestableObserver<HotelsPackagesDisplay>!
    private var selectedHotel: TestableObserver<Hotel>!
    
    private var fetchHotelsResponseMock: FetchHotelsResponse!
    
    override func setUp() {
        super.setUp()
        setupTestData()
        setupHomeViewModel()
    }
    
    private func setupTestData() {
        fetchHotelsResponseMock = FetchHotelsResponse(
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
    }
    
    private func setupHomeViewModel() {
        disposeBag = DisposeBag()
        interactor = HomeInteractorMock()
        sut = HomeViewModel(interactor: interactor)
        
        let testScheduler = TestScheduler(initialClock: 0)
        
        error = testScheduler.createObserver(Error.self)
        sut.output.error.drive(error).disposed(by: disposeBag)
        
        hotelsPackages = testScheduler.createObserver(HotelsPackagesDisplay.self)
        sut.output.hotelsPackages.drive(hotelsPackages).disposed(by: disposeBag)
        
        selectedHotel = testScheduler.createObserver(Hotel.self)
        sut.output.selectedHotel.drive(selectedHotel).disposed(by: disposeBag)
        
        testScheduler.start()
    }
    
    func test_ShouldEmit_HotelsPackages_When_FetchData() {
        
        let expected = HotelsPackagesDisplay(data: fetchHotelsResponseMock.results)
        
        interactor.requestReturnValue = fetchHotelsResponseMock
            
        sut.input.fetchData.onNext(())
        
        XCTAssertEqual(
            hotelsPackages.events.compactMap { $0.value.element },
            [expected]
        )
    }
    
    func test_ShouldEmit_Error_When_FetchData() {
        
        let expected = NSError(domain: "", code: 0, userInfo: nil)
        
        interactor.errorReturn = expected
            
        sut.input.fetchData.onNext(())
        
        XCTAssertEqual(
            error.events.compactMap { $0.value.element }.last as NSError?,
            expected
        )
    }
    
    func test_ShouldEmit_SelectedHotel_When_SelectHotel() {
        
        let expected = fetchHotelsResponseMock.results.first!
        
        interactor.requestReturnValue = fetchHotelsResponseMock
            
        sut.input.fetchData.onNext(())
        
        sut.input.selectHotel.onNext("1234")
        
        XCTAssertEqual(
            selectedHotel.events.compactMap { $0.value.element },
            [expected]
        )
    }
}

//
//  HomeViewModel.swift
//  DesafioHurb
//
//  Created by Edson Aparecido Guido on 04/09/20.
//  Copyright (c) 2020 Edson Aparecido Guido. All rights reserved.
//

import RxCocoa
import RxSwift

protocol HomeViewModelInput: AnyObject {
    var fetchData: PublishSubject<Void> { get }
    var fetchNextPage: PublishSubject<Void> { get }
    var selectHotel: PublishSubject<String> { get }
}

protocol HomeViewModelOutput: AnyObject {
    var error: Driver<Error> { get }
    var hotelsPackages: Driver<HotelsPackagesDisplay> { get }
    var selectedHotel: Driver<Hotel> { get }
}

protocol HomeViewModelType: AnyObject {
    var input: HomeViewModelInput { get }
    var output: HomeViewModelOutput { get }
}

final class HomeViewModel: HomeViewModelType, HomeViewModelInput, HomeViewModelOutput {
    
    let error: Driver<Error>
    let hotelsPackages: Driver<HotelsPackagesDisplay>
    let selectedHotel: Driver<Hotel>
    
    init(interactor: HomeInteractable) {
        let errorTracker = ErrorTracker()
        
        error = errorTracker.asDriver()
        
        let triggerNextPage = fetchNextPage.asObservable()
        
        let hotelsResponse = fetchData.asDriver(onErrorJustReturn: ())
            .flatMap { _ in
                fetchHotels(
                    interactor: interactor,
                    errorTracker: errorTracker,
                    trigger: triggerNextPage
                )
        }
        
        hotelsPackages = hotelsResponse
            .map { data in
                let packages = data.filter { ($0.isPackage ?? false) }.map(HotelDisplay.init)
                let hotels = data.filter { !($0.isPackage ?? false) }
                let hotelsOne = hotels.filter { ($0.stars ?? 1 == 1) }.map(HotelDisplay.init)
                let hotelsTwo = hotels.filter { ($0.stars ?? 1 == 2) }.map(HotelDisplay.init)
                let hotelsThree = hotels.filter { ($0.stars ?? 1 == 3) }.map(HotelDisplay.init)
                let hotelsFour = hotels.filter { ($0.stars ?? 1 == 4) }.map(HotelDisplay.init)
                let hotelsFive = hotels.filter { ($0.stars ?? 1 == 5) }.map(HotelDisplay.init)
                return HotelsPackagesDisplay(count: data.count,
                                             packages: packages,
                                             hotelsOneStar: hotelsOne,
                                             hotelsTwoStar: hotelsTwo,
                                             hotelsThreeStar: hotelsThree,
                                             hotelsFourStar: hotelsFour,
                                             hotelsFiveStar: hotelsFive)
        }
        
        selectedHotel = selectHotel.asDriver(onErrorDriveWith: Driver.empty())
            .withLatestFrom(hotelsResponse) { (id: $0, fetchHotelsResponse:$1) }
            .map { id, hotelsResponse in
                hotelsResponse.first(where: { $0.id == id })
        }
        .compactMap { $0 }
        
    }
    
    let fetchData: PublishSubject<Void> = PublishSubject()
    let fetchNextPage: PublishSubject<Void> = PublishSubject()
    let selectHotel: PublishSubject<String> = PublishSubject()
    let selectFilter: PublishSubject<Void> = PublishSubject()
    
    var input: HomeViewModelInput { return self }
    var output: HomeViewModelOutput { return self }
    
}

private func fetchHotels(
    interactor: HomeInteractable,
    errorTracker: ErrorTracker,
    trigger: Observable<Void>
) -> Driver<[Hotel]> {
    let make = { (previousResult: FetchHotelsResponse?) -> Observable<FetchHotelsResponse> in
        
        let nextPage = (previousResult?.pagination.nextPageNumber ?? 1)
        
        return interactor.fetchHotels(page: nextPage)
            .asObservable()
    }
    
    let hasNext = { (fetchHotelsResponse: FetchHotelsResponse) -> Bool in
        fetchHotelsResponse.pagination.nextPageNumber ?? 0 <= fetchHotelsResponse.pagination.lastPageNumber ?? 0
    }
    
    return Observable.page(make: make, while: hasNext, when: trigger)
        .map { $0.results.sorted(by: { ($0.stars ?? 0) > ($1.stars ?? 0) }) }
        .scan([], accumulator: +)
        .trackError(errorTracker)
        .asDriver(onErrorDriveWith: Driver.empty())
}

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
    var selectHotel: PublishSubject<Int> { get }
}

protocol HomeViewModelOutput: AnyObject {
    var error: Driver<Error> { get }
    var hotels: Driver<[HotelDisplay]> { get }
    var selectedHotel: Driver<Hotel> { get }
}

protocol HomeViewModelType: AnyObject {
    var input: HomeViewModelInput { get }
    var output: HomeViewModelOutput { get }
}

final class HomeViewModel: HomeViewModelType, HomeViewModelInput, HomeViewModelOutput {
    let error: Driver<Error>
    let hotels: Driver<[HotelDisplay]>
    let selectedHotel: Driver<Hotel>
    
    init(interactor: HomeInteractable) {
        let errorTracker = PublishSubject<Error>()
        
        error = errorTracker.asDriver(onErrorDriveWith: Driver.empty())
        
        let fetchHotelsResponse = fetchData
            .asDriver(onErrorJustReturn: ())
            .flatMap { _ in
                interactor.fetchHotels()
                    .asDriver(onErrorRecover: { err in
                        errorTracker.onNext(err)
                        return Driver.empty()
                    })
        }
        
        hotels = fetchHotelsResponse
            .map { $0.results.map(HotelDisplay.init) }
        
        selectedHotel = selectHotel.asDriver(onErrorDriveWith: Driver.empty())
            .withLatestFrom(fetchHotelsResponse) { (index: $0, fetchHotelsResponse:$1) }
            .map { index, fetchHotelsResponse in
                fetchHotelsResponse.results[index]
        }
        
    }
    
    let fetchData: PublishSubject<Void> = PublishSubject()
    let selectHotel: PublishSubject<Int> = PublishSubject()
    
    var input: HomeViewModelInput { return self }
    var output: HomeViewModelOutput { return self }
    
}

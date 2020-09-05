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
}

protocol HomeViewModelOutput: AnyObject {
    var error: Driver<Error> { get }
    var hotels: Driver<[Hotel]> { get }
}

protocol HomeViewModelType: AnyObject {
    var input: HomeViewModelInput { get }
    var output: HomeViewModelOutput { get }
}

final class HomeViewModel: HomeViewModelType, HomeViewModelInput, HomeViewModelOutput {
    let error: Driver<Error>
    let hotels: Driver<[Hotel]>
    
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
            .map { $0.results }
    }
    
    let fetchData: PublishSubject<Void> = PublishSubject()
    
    var input: HomeViewModelInput { return self }
    var output: HomeViewModelOutput { return self }
    
}

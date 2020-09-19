//
//  FetchHotelsUseCaseMock.swift
//  DesafioHurbTests
//
//  Created by Edson Aparecido Guido on 16/09/20.
//  Copyright © 2020 Edson Aparecido Guido. All rights reserved.
//

@testable import DesafioHurb
import RxSwift

class FetchHotelsUseCaseMock: FetchHotelsUseCase {

    var requestReceivedPage: Int?
    var requestReturnValue: FetchHotelsResponse!
    
    func fetchHotels(page: Int) -> Single<FetchHotelsResponse> {
        requestReceivedPage = page
        return .just(requestReturnValue)
    }
}

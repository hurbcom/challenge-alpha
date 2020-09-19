//
//  HomeInteractorMock.swift
//  DesafioHurbTests
//
//  Created by Edson Aparecido Guido on 16/09/20.
//  Copyright © 2020 Edson Aparecido Guido. All rights reserved.
//

@testable import DesafioHurb
import RxSwift

class HomeInteractorMock: HomeInteractable {

    var requestReceivedPage: Int?
    var requestReturnValue: FetchHotelsResponse?
    var errorReturn: NSError?
    
    func fetchHotels(page: Int) -> Single<FetchHotelsResponse> {
        requestReceivedPage = page
        if let error = errorReturn {
            return.error(error)
        }
        return .just(requestReturnValue!)
    }
}

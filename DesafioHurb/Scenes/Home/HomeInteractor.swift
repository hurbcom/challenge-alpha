//
//  HomeInteractor.swift
//  DesafioHurb
//
//  Created by Edson Aparecido Guido on 04/09/20.
//  Copyright (c) 2020 Edson Aparecido Guido. All rights reserved.
//

import RxSwift

protocol HomeInteractable: AnyObject, FetchHotelsUseCase {}

final class HomeInteractor: HomeInteractable {
    
    private let fetchHotelsUseCase: FetchHotelsUseCase
    
    init(fetchHotelsUseCase: FetchHotelsUseCase) {
        self.fetchHotelsUseCase = fetchHotelsUseCase
    }
    
    func fetchHotels(page: Int) -> Single<FetchHotelsResponse> {
        fetchHotelsUseCase.fetchHotels(page: page)
    }

}

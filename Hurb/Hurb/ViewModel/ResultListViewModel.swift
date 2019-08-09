//
//  ResultListViewModel.swift
//  Hurb
//
//  Created by Alexandre Papanis on 08/08/19.
//  Copyright © 2019 Papanis. All rights reserved.
//

import RxSwift
import RxCocoa

class ResultListViewModel {
    //MARK: - Variables
    private var hotelList = BehaviorRelay<[Hotel]>(value: [])
    private var place: String
    private var page = 0
    
    //MARK: - RXObservable
    var hotelsObservable: Observable<[Hotel]> {
        return self.hotelList.asObservable()
    }
    
    //MARK: - Subscript
    subscript(index: Int) -> HotelViewModel {
        if index > count - 5 {
            fetchMoreHotels(place: place)
        }
        return HotelViewModel(hotelList.value[index])
    }
    
    var count: Int {
        return hotelList.value.count
    }
    
    //MARK: - Init
    init(place: String) {
        self.place = place
        fetchMoreHotels(place: place)
    }
    
    //MARK: - Fatch more hotels
    private func fetchMoreHotels(place: String) {
        page = page + 1
        APIClient.searchHotels(by: place, page: page) { [unowned self] result in
            switch result {
            case .success(let page):
                self.hotelList.accept(self.hotelList.value + page.results!)
            case .failure(let error):
                print(error.localizedDescription)
            }
        }
    }
}

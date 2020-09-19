//
//  MainQueueDispatchDecorator.swift
//  hurb-test
//
//  Created by Tulio Parreiras on 19/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import Foundation

import HotelSearch

final class MainQueueDispatchDecorator<T> {
    private let decoratee: T
    
    init(decoratee: T) {
        self.decoratee = decoratee
    }
    
    func dispatch(completion: @escaping () -> Void) {
        guard Thread.isMainThread else {
            return DispatchQueue.main.async(execute: completion)
        }
        completion()
    }
}

extension MainQueueDispatchDecorator: HotelSearcher where T == HotelSearcher {
    
    func searchHotel(with searchText: String, completion: @escaping (SearchResult) -> Void) {
        decoratee.searchHotel(with: searchText) { [weak self] result in
            self?.dispatch { completion(result) }
        }
    }
    
}

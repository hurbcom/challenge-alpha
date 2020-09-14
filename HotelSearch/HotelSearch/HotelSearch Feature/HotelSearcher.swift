//
//  HotelSearcher.swift
//  HotelSearch
//
//  Created by Tulio Parreiras on 13/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import Foundation

public protocol HotelSearcher {
    typealias SearchResult = Swift.Result<[Hotel], Error>
    
    func searchHotel(with searchText: String, completion: @escaping (SearchResult) -> Void)
}

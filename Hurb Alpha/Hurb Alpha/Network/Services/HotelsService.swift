//
//  HotelsService.swift
//  Hurb Alpha
//
//  Created by Thiago Augusto on 24/07/20.
//  Copyright © 2020 objectivesev. All rights reserved.
//

import Foundation
import PromiseKit

protocol IHotelService {
    typealias HotelsHandler = (NetworkResult<QueryResult, NetworkError, Int>) -> Void
    
    func getHotels(query: String, page: Int, handler: @escaping HotelsHandler)
}

class HotelsService: NetworkBaseService, IHotelService {
    func getHotels(query: String, page: Int, handler: @escaping HotelsHandler) {
        let path = ""
        let parameters: [String: Any] = ["q": query, "page": page]
        let service = NetworkService(api: .hurbApi, path: path, parameters: parameters)
        NetworkDispatch.shared.get(service, handler: handler)
    }
}

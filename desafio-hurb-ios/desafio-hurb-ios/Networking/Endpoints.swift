//
//  Endpoints.swift
//  desafio-hurb-ios
//
//  Created by Vinícius Barcelos on 21/10/19.
//  Copyright © 2019 Vinícius Barcelos. All rights reserved.
//

import Foundation

enum RequestMethod: String {
    case get = "GET"
}

protocol Endpoint {
    var endpoint: String {get}
    var method: RequestMethod {get}
    var parameters: Data? {get}
}

class hurbSearch: Endpoint {
    var endpoint: String
    var method: RequestMethod = .get
    var parameters: Data? = nil
    var searchParameter: String
    
    init(searchParameter: String) {
        self.searchParameter = searchParameter
        self.endpoint = "search/api?q=\(searchParameter)&page=1"
    }
}

//class PicPayTransaction: Endpoint {
//    var endpoint: String = "transaction/"
//    var method: RequestMethod = .post
//    var parameters: Data?
//
//    init(with payment: Payment) {
//        self.parameters = payment.asData()
//    }
//}

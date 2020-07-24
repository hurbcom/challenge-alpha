//
//  NetworkService.swift
//  Hurb Alpha
//
//  Created by Thiago Augusto on 24/07/20.
//  Copyright © 2020 objectivesev. All rights reserved.
//

import Foundation

enum NetworkResult<T, NetworkError, Int> {
    case success(T, Int)
    case failure(NetworkError, Int)
}

enum NetworkError: Error {
    case undefined
    case withError(error: Error)
    case withDescription(description: String)

    func message() -> String {
        let undefined = "Algo de inesperado aconteceu e isso foi notificado!"
        switch self {
        case .undefined:
            return undefined
        case .withDescription(let description):
            return description
        case .withError(let error):
            return error.localizedDescription
        }
    }
    
    func code() -> Int {
        let undefined = -1
        switch self {
        case .withError(let error):
            return (error as NSError).code
        default:
            return undefined
        }
    }
    
}

enum NetworkResponse {
    case success(data: Data, code: Int)
    case failure(error: Error, code: Int)
}

enum HTTPMethod: String {
    case options = "OPTIONS"
    case get     = "GET"
    case head    = "HEAD"
    case post    = "POST"
    case put     = "PUT"
    case patch   = "PATCH"
    case delete  = "DELETE"
    case trace   = "TRACE"
    case connect = "CONNECT"
}

enum Service: String {
    case hurbApi  = "search"
}

struct NetworkService {
    
    var api: Service = .hurbApi
    var base: String = ""
    var path: String = ""
    var url: URL = URL(fileURLWithPath: "")
    var parameters: [String : Any]? = nil
    
    init(parameters: [String : Any]? = nil) {
        self.parameters = parameters
    }
    
    init(api: Service, path: String, parameters: [String : Any]? = nil) {
        switch api {
        case .hurbApi:
            self.base = Environment.getValue(forKey: .apiURL)
        }
        
        self.api = api
        self.path = path
        self.parameters = parameters
        
        if let url = URL(string: self.base) {
            self.url = url.appendingPathComponent(api.rawValue).appendingPathComponent(self.path)
        }
    }
}

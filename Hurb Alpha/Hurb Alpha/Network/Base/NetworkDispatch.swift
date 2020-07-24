//
//  NetworkDispatch.swift
//  Hurb Alpha
//
//  Created by Thiago Augusto on 24/07/20.
//  Copyright © 2020 objectivesev. All rights reserved.
//

import Foundation
import ObjectMapper
import PromiseKit

class NetworkDispatch: NetworkBaseService {
    
    static let shared = NetworkDispatch()
    typealias MappableHandler <T: Mappable> = (NetworkResult<T, NetworkError, Int>) -> Void
        
    func post<T: Mappable>(_ service: NetworkService, handler: @escaping MappableHandler<T>) {
        Network.post(service).resume { (result) in
            switch result {
            case .success(let data, let code):
               handler(self.handleModelObject(data: data, code: code))
                
            case .failure(let error, let code):
                handler(self.handleError(error: error, code: code))
            }
        }
    }

    func get<T: Mappable>(_ service: NetworkService, handler: @escaping MappableHandler<T>) {
        Network.get(service).resume { (result) in
            switch result {
            case .success(let data, let code):
                    handler(self.handleModelObject(data: data, code: code))
            case .failure(let error, let code):
                handler(self.handleError(error: error, code: code))
            }
        }
    }
    

    func put<T: Mappable>(_ service: NetworkService, handler: @escaping MappableHandler<T>) {
        Network.put(service).resume { (result) in
            switch result {
            case .success(let data, let code):
                handler(self.handleModelObject(data: data, code: code))
                
            case .failure(let error, let code):
                handler(self.handleError(error: error, code: code))
            }
        }
    }

    func delete<T: Mappable>(_ service: NetworkService, handler: @escaping MappableHandler<T>) {
        Network.delete(service).resume { (result) in
            switch result {
            case .success(let data, let code):
                handler(self.handleModelObject(data: data, code: code))
                
            case .failure(let error, let code):
                handler(self.handleError(error: error, code: code))
            }
        }
    }
}

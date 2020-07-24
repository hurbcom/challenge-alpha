//
//  NetworkBaseService.swift
//  Hurb Alpha
//
//  Created by Thiago Augusto on 24/07/20.
//  Copyright © 2020 objectivesev. All rights reserved.
//

import Foundation
import ObjectMapper

protocol NetworkBaseService {
    func handleError<T>(error: Error, code: Int) -> NetworkResult<T, NetworkError, Int>
    func handleModelObject<T: Mappable>(data: Data, key: String?, code: Int) -> NetworkResult<T, NetworkError, Int>
}

extension NetworkBaseService {
    
    func handleError<T>(error: Error, code: Int) -> NetworkResult<T, NetworkError, Int> {
        Log.shared.show(error: error)
        return NetworkResult.failure(NetworkError.withError(error: error), code)
    }
    
    func handleModelObject<T>(data: Data, key: String? = nil, code: Int) -> NetworkResult<T, NetworkError, Int> where T : Mappable {
        if let key = key {
            if let JSON = data.modelObject(key: key), let model = Mapper<T>().map(JSON: JSON) {
                return NetworkResult.success(model, code)
            } else {
                return NetworkResult.failure(NetworkError.undefined, code)
            }
        } else {
            if let JSON = data.modelObject(), let model = Mapper<T>().map(JSON: JSON) {
                return NetworkResult.success(model, code)
            } else {
                return NetworkResult.failure(NetworkError.undefined, code)
            }
        }
    }
}

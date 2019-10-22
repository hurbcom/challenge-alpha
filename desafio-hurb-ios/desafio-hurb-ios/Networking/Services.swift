//
//  Services.swift
//  desafio-hurb-ios
//
//  Created by Vinícius Barcelos on 21/10/19.
//  Copyright © 2019 Vinícius Barcelos. All rights reserved.
//

import Foundation

class NetworkingServices {
    
    static func getHotels(with query: String, requestResult: @escaping (Result<[Hotel], NetworkingError>)->()) {
        Networking.hurbAPICall(with: hurbSearch(searchParameter: query)) { (result) in
            switch result {
            case .success(let data):
                do {
                    let apiResponse = try JSONDecoder().decode(APIResponse.self, from: data)
                    requestResult(.success(apiResponse.results))
                } catch {
                    requestResult(.failure(NetworkingError.decodingError))
                }
            case .failure(let error):
                requestResult(.failure(error))
            }
        }
    }
    
}

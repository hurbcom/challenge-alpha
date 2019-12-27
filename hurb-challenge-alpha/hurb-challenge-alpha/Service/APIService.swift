//
//  APIService.swift
//  hurb-challenge-alpha
//
//  Created by Hannah  on 26/12/2019.
//  Copyright © 2019 Hannah . All rights reserved.
//

import Moya

///class responsible for managing API requests
class APIService {
    
    var provider = MoyaProvider<HurbAPI>(plugins: [NetworkLoggerPlugin(verbose: true)])
    
    // MARK: - Methods
    
    ///  Function responsible for HurbAPI search for hotels and packages and handling received data
    /// - Parameter query: Name of the place to be searched
    /// - Parameter page: page number to be returned
    /// - Parameter completion: returned type (request result or error)
    func getAccommodations(query: String, page: Int, completion: @escaping ([Accommodation]?, Error?) -> Void) {
        
        provider.request(.getHotels(query: query, page: page)) { (response) in
            switch response.result {
            case .failure(let error):
                completion(nil, error)
            case .success(let value):
                do {
                    let hotels = try JSONDecoder().decode(Accommodations.self, from: value.data)
                    completion(hotels.results, nil)
                    
                } catch let error {
                    completion(nil, error)
                }
            }
        }
    }
    // MARK: - Methods
    
    ///  Function responsible for HurbAPI search suggestion handling received data
    /// - Parameter query: Name of the place to be searched
    /// - Parameter completion: returned type (request result or error)
    func getSuggestions(query: String, completion: @escaping ([Suggestion]?, Error?) -> Void) {
        
        provider.request(.getSuggestion(query: query)) { (response) in
            switch response.result {
            case .failure(let error):
                completion(nil, error)
            case .success(let value):
                do {
                    let result = try JSONDecoder().decode(Suggestions.self, from: value.data)
                    completion(result.suggestions, nil)
                    
                } catch let error {
                    completion(nil, error)
                }
            }
        }
    }
}

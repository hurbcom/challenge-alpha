//
//  NetworkAdapter.swift
//  Challange HURB - Alpha
//
//  Created by Luiz Fernando Cunha Duarte on 29/10/19.
//  Copyright © 2019 Luiz Fernando Cunha Duarte. All rights reserved.
//

import Moya

/// Struct responsible to make the request to the API
struct NetworkAdapter {
    // MARK: - Properties
    private let provider: MoyaProvider<HurbAPI>
    
    // MARK: - Initializers
    init(state: NetworkAdapterState) {
        switch state {
        case .test:
            provider = MoyaProvider<HurbAPI>(stubClosure: MoyaProvider.immediatelyStub)
        case .production:
            provider = MoyaProvider<HurbAPI>(plugins: [NetworkLoggerPlugin()])
        }
    }
    
    // MARK: - Methods
    
    /// Function responsible to make the query to the HurbAPI and handle the data received
    /// - Parameter query: Name of the place to be searched
    /// - Parameter page: Page from which the results will be returned
    /// - Parameter completion: A Result type returned from the query which can or an APIReponse or an Error
    func getAPIRespose(query: String, page: Int, completion: @escaping (Result<APIResponse, Error>) -> Void) {
        provider.request(.search(query: query, page: page)) { result in
            switch result {
            case .failure(let error):
                completion(.failure(error))
            case .success(let value):
                let decoder = JSONDecoder()
                do {
                    let apiResponse = try decoder.decode(APIResponse.self, from: value.data)
                    completion(.success(apiResponse))
                } catch let error {
                    completion(.failure(error))
                }
            }
        }
    }
}

/// The state of the networkAdapter
enum NetworkAdapterState {
    case test, production
}

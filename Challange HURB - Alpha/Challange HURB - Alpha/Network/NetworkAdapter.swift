//
//  NetworkAdapter.swift
//  Challange HURB - Alpha
//
//  Created by Luiz Fernando Cunha Duarte on 29/10/19.
//  Copyright © 2019 Luiz Fernando Cunha Duarte. All rights reserved.
//

import Moya

struct NetworkAdapter {
    private let provider = MoyaProvider<HurbAPI>()
    
    func getAPIRespose(completion: @escaping (APIResponse?, Error?) -> Void) {
        provider.request(.search(query: "buzios", page: 1)) { result in
            switch result {
            case .failure(let error):
                completion(nil, error)
            case .success(let value):
                let decoder = JSONDecoder()
                do {
                    let apiResponse = try decoder.decode(APIResponse.self, from: value.data)
                    completion(apiResponse, nil)
                } catch let error {
                    completion(nil, error)
                }
            }
        }
    }
    
    
}

//
//  SearchLocationWorker.swift
//  Hurb-iOS-Challenge
//
//  Created by Rômulo Monteiro on 17/01/23.
//  Copyright (c) 2023 ___ORGANIZATIONNAME___. All rights reserved.
//
//  This file was generated by the Clean Swift Xcode Templates so
//  you can apply clean architecture to your iOS and Mac projects,
//  see http://clean-swift.com
//

import UIKit
import HUGraphQL

protocol SearchLocationWorkerProtocol {
    
    func searchLocations(term: String, limit: Int, completion: @escaping (Result<[Location]?, ServiceError>) -> Void)
}

class SearchLocationWorker: SearchLocationWorkerProtocol {
    
    func searchLocations(term: String, limit: Int, completion: @escaping (Result<[Location]?, ServiceError>) -> Void) {
        
        let query: HUGraphQL.SuggestionsQuery = HUGraphQL.SuggestionsQuery(
            q: term,
            limit: limit,
            productType: nil,
            l10n: .init(pos: "br", locale: "pt", currency: "BRL")
        )
        
        let graphQL: HUGService = HUGService(enableLog: true)
        graphQL.client.fetch(query: query) { result in
            
            switch result {
                    
            case .success(let value):

                    if let listObject = value.data?.suggestions?.results.compactMap({ $0.resultMap }),
                       let jsonData: Data = try? JSONSerialization.data(withJSONObject: listObject, options: .prettyPrinted),
                       let decoded: [Location] = try? JSONDecoder().decode([Location].self, from: jsonData) {

                        completion(.success(decoded))

                    } else {

                        completion(.failure(.emptyReturn))
                    }
                break
                    
            case .failure(let error):
                    completion(.failure(.unknown(error.localizedDescription)))
                    break
            }
        }
    }
}

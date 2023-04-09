//
//  TravelService.swift
//  iTravel_iOS_Challenge
//
//  Created by convidado on 08/04/23.
//

import Foundation
import HUGraphQL

class TravelService{
    
    var service = HUGService(enableLog: false)

    func searchPackage(local:String = "Rio de Janeiro", completion: @escaping (Result<HUGraphQL.SearchPackageQuery.Data, Error>) -> Void){
        
        let query = HUGraphQL.SearchPackageQuery(q: local, filters: nil, pagination: .init(
            page: 1,
            limit: 20,
            sort: .price,
            sortOrder: .desc), l10n: .init(pos: "br", locale: "pt", currency: "BRL"))
        
        service.client.fetch(query: query) { res in
            switch res {
            case .failure(let error):
                completion(.failure(error))
                // error
            case .success(let graphQLValue):
                completion(.success(graphQLValue.data!))
                //success
                
            }
            
        }

    }
    
    func performSearch() {
        
        
        let query = HUGraphQL.SearchQuery(q: "Rio de Janeiro", pagination: .init(
            page: 1,
            limit: 1,
            sort: .price,
            sortOrder: .desc))
        
        service.client.fetch(query: query) { res in
            switch res {
            case .failure(let error):
                print(error)
                // error
            case .success(let graphQLValue):
                print(graphQLValue.data?.jsonObject)
                //success
                
            }
            
        }
    }
}

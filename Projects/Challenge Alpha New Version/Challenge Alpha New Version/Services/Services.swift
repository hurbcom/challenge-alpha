//
//  Services.swift
//  Challenge Alpha New Version
//
//  Created by Rafael Oliveira on 12/04/23.
//

import Foundation
import HUGraphQL

class Services {
    let service = HUGService(enableLog: false)
    static let servicesNetwork = Services()
    func performSearchFetch(query:String, pagination:HUGraphQL.SearchInputPagination? = nil, completion:@escaping([HUGraphQL.SearchQuery.Data.Search.Result])->()){
        let q = HUGraphQL.SearchQuery(q: query, pagination: pagination)
        service.client.fetch(query: q ){ result in
            switch result {
            case .failure(let error):
                print(error.localizedDescription)
                completion([])
            case .success(let Graphresult):
                guard let result = Graphresult.data?.search?.results  else {
                    completion([])
                    return}
                completion(result)}
        }}
    
    
    func performHotelSearch(query:String, pagination: HUGraphQL.SearchInputPagination? = nil, completion:@escaping( [HUGraphQL.SearchHotelQuery.Data.SearchHotel.Result])->()) {
        let query = HUGraphQL.SearchHotelQuery(
            q: query,
            filters: nil,
            pagination: pagination,
            l10n: .init(pos: "br", locale: "pt", currency: "BRL"),
            checkin: Date(),
            checkout: nil,
            rooms: nil)
        
        service.client.fetch(query: query) { res in
            switch res {
            case .failure(let error):
                print("tipo de erro: \n \(error.localizedDescription)")
                completion([])
            case .success(let graphQLValue):
                guard let _data = graphQLValue.data?.searchHotel?.results else {
                    completion([])
                    return}
                completion(_data)
            }
        }
    }
    
    internal func performPackageSearch(query: String, pagination: HUGraphQL.SearchInputPagination? = nil, completion: @escaping([HUGraphQL.SearchPackageQuery.Data.SearchPackage.Result])->()) {
        
        let query = HUGraphQL.SearchPackageQuery(q: query, filters: nil, pagination: pagination, l10n: .init(pos: "br", locale: "pt", currency: "BRL"))
        service.client.fetch(query: query) { res in
            switch res {
            case .failure(let error):
                completion([])
            case .success(let graphQLValue):
                guard let _data = graphQLValue.data?.searchPackage?.results else {
                    completion([])
                    return}
                completion(_data)
            }
        }
    }
}

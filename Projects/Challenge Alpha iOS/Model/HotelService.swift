//
//  Service.swift
//  Challenge Alpha iOS (iOS)
//
//  Created by Kleiton Mendes on 18/01/23.
//

import UIKit
import HUGraphQL

protocol HotelsServiceDelegate {
  func didFetchHotels(result: [HotelService])
  func errorOnFetchHotels(error: Error)
}

struct HotelService {
    
    typealias ResultAPI = HUGraphQL.SearchQuery.Data.Search.Result?
    
    static let shared = HotelService()
    
    let hug = HUGService(enableLog: true)
    
    func request(search informations: String, completion: @escaping ([ResultAPI]?) -> Void ) {
        
        let query = HUGraphQL.SearchQuery(q: informations)
        hug.client.fetch(query: query) { result in
            switch result {
            case .success(let sucess):
                DispatchQueue.main.async {
                    completion(sucess.data?.search?.results)
                }
            case .failure(let failure):
                print(failure.localizedDescription)
            }
        }
    }
}

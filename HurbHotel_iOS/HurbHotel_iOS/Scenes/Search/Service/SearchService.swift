//
//  SearchService.swift
//  HurbHotel_iOS
//
//  Created by Helio Junior on 22/07/20.
//  Copyright © 2020 HelioTecnologia. All rights reserved.
//

import Foundation

class SearchService {
    
    func search(_ term: String?, page: String, success: @escaping (SearchResult) -> (), failure: @escaping (String) -> ()) {
        guard let term = term, let url = URL(string: String(format: "%@/search/api?q=%@&page=%@", BASE_URL, term, page)) else {
            failure("URL inválida!")
            return
        }
        Network.get(url: url) { (data, error) in
            if let error = error {
                failure(error.localizedDescription)
            }

            do {
                guard let data = data else { return }
                let result = try JSONDecoder().decode(SearchResult.self, from: data)
                success(result)
            } catch let err {
                failure(err.localizedDescription)
            }
        }
    }
    
    func getSuggestions(_ term: String?, success: @escaping (Suggestions) -> (), failure: @escaping (String) -> ()) {
        guard let term = term, let url = URL(string: String(format: "%@/search/api/suggestion?q=%@", BASE_URL, term)) else {
            failure("URL inválida!")
            return
        }
        Network.get(url: url) { (data, error) in
            if let error = error {
                failure(error.localizedDescription)
            }

            do {
                guard let data = data else { return }
                let result = try JSONDecoder().decode(Suggestions.self, from: data)
                success(result)
            } catch let err {
                failure(err.localizedDescription)
            }
        }
    }
}

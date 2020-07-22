//
//  SearchService.swift
//  HurbHotel_iOS
//
//  Created by Helio Junior on 22/07/20.
//  Copyright © 2020 HelioTecnologia. All rights reserved.
//

import Foundation

class SearchService {
    
    func search(_ term: String, page: String, success: @escaping (String) -> (), failure: @escaping (String) -> ()) {
        
        guard let url = URL(string: String(format: "%@/search/api?q=%@&page=%@", BASE_URL, term, page)) else {
            failure("URL inválida!")
            return
        }
        Network.get(url: url) { (data, error) in
            
            if let error = error {
                failure(error.localizedDescription)
            }
            
            
        }
    }
}

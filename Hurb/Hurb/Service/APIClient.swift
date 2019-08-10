//
//  APIClient.swift
//  Hurb
//
//  Created by Alexandre Papanis on 08/08/19.
//  Copyright © 2019 Papanis. All rights reserved.
//

import Foundation
import Alamofire

class APIClient {
    
    //MARK: searchHotels by place request
    static func searchHotels(by place: String, page: Int, completion:@escaping (Result<Page>)->Void) {
        
        Alamofire.request(APIRouter.getHotels(by: place, page: page))
            .responseJSON() { response in
                guard let data = response.data else { return }
                guard response.result.isSuccess else { return }
                
                var page: Page?
                
                do {
                    page = try JSONDecoder().decode(Page.self, from: data)
                }
                catch {
                    let result = Result<Page>.failure(error)
                    completion(result)
                    return
                }
                
                let result = Result<Page>.success(page!)
                completion(result)
        }
    }
    
    //MARK: searchSuggestions by place request
    static func searchSuggestions(by place: String, completion:@escaping (Result<ResultSuggestion>)->Void) {
        
        Alamofire.request(APIRouter.getSuggestions(by: place))
            .responseJSON() { response in
                guard let data = response.data else { return }
                guard response.result.isSuccess else { return }
                
                var resultSuggestion: ResultSuggestion?
                
                do {
                    resultSuggestion = try JSONDecoder().decode(ResultSuggestion.self, from: data)
                }
                catch {
                    let result = Result<ResultSuggestion>.failure(error)
                    completion(result)
                    return
                }
                
                let result = Result<ResultSuggestion>.success(resultSuggestion!)
                completion(result)
        }
    }
}

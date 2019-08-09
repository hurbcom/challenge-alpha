//
//  APIRouter.swift
//  Hurb
//
//  Created by Alexandre Papanis on 08/08/19.
//  Copyright © 2019 Papanis. All rights reserved.
//

import Foundation
import Alamofire

class APIRouter {
    static func getHotels(by place: String, page: Int) -> URLRequestConvertible {
        
        var urlComponents = URLComponents(string: Defines.BASE_URL)!
        
        urlComponents.queryItems = [
            URLQueryItem(name: "q", value: place.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed)),
            URLQueryItem(name: "sort", value: "stars"),
            URLQueryItem(name: "page", value: "\(page)")
        ]
        
        var request = URLRequest(url: urlComponents.url!)
        request.httpMethod = "GET"
        request.timeoutInterval = 20
        request.setValue("application/json", forHTTPHeaderField: "Content-Type")
        
        return request as URLRequestConvertible
    }
    
}

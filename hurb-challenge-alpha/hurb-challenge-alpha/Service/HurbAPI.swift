//
//  HurbAPI.swift
//  hurb-challenge-alpha
//
//  Created by Hannah  on 26/12/2019.
//  Copyright © 2019 Hannah . All rights reserved.
//

import Moya

enum HurbAPI {
    case getSuggestion(query: String)
    case getHotels(query: String, page: Int)
}

extension HurbAPI: TargetType {
    var path: String {
        switch self {
        case .getSuggestion:
            return "/search/api/suggestion"
        case .getHotels:
            return "/search/api"
        }
    }
    
    var method: Moya.Method {
        return .get
    }
    
    var sampleData: Data {
        return Data()
    }
    
    var task: Task {
        switch self {
        case .getHotels(let query, let page):
            return .requestParameters(parameters: ["q": query, "page": page ], encoding: URLEncoding.default)
        case .getSuggestion(let query):
            return .requestParameters(parameters: ["q": query], encoding: URLEncoding.default)
        }
    }
    
    var headers: [String: String]? {
        return ["Content-type": "application/json"]
    }
    var baseURL: URL {
        return URL(string: "https://www.hurb.com")!
    }
}



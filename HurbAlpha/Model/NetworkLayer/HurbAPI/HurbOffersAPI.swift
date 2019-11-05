//
//  HurbOffersAPI.swift
//  HurbAlpha
//
//  Created by Rodrigo Bukowitz on 05/11/19.
//  Copyright © 2019 Rodrigo Bukowitz. All rights reserved.
//

import Moya

/// This enum represent all the cases from API that handles with all the HurbOffers, that will assist us to get information in this application
enum HurbOffersAPI {
    case getOffers(query: String, page: Int)
}

/// This extension describes all the info for the Target Type , in order to use Moya
extension HurbOffersAPI: TargetType {
    
    var baseURL: URL {
        return URL(string: "https://www.hurb.com")!
    }
    
    var path: String {
        switch self {
        case .getOffers: return "/search/api"
        }
    }
    
    var method: Method {
        switch self {
        case .getOffers: return .get
        }
    }
    
    var sampleData: Data {
        return Data()
    }
    
    var parameters: [String: Any]? {
        var params: [String: Any] = [:]
        switch self {
        case .getOffers(let query, let page):
            params["q"] = query
            params["page"] = page
        }
        return params
    }
    
    var parameterEncoding: ParameterEncoding {
        return URLEncoding.default
    }
    
    var task: Task {
        switch self {
        case .getOffers:
            if let parameters = parameters {
                return .requestParameters(
                    parameters: parameters,
                    encoding: parameterEncoding)
            }
            return .requestPlain
        }
    }
    
    var headers: [String : String]? {
        return ["Content-type": "application/json"]
    }
    
}

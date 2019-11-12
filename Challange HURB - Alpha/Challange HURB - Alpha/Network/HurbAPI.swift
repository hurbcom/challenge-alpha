//
//  HurbAPI.swift
//  Challange HURB - Alpha
//
//  Created by Luiz Fernando Cunha Duarte on 29/10/19.
//  Copyright © 2019 Luiz Fernando Cunha Duarte. All rights reserved.
//

import Foundation
import Moya

/// Responsible to prepare the query to API
enum HurbAPI {
    case search(query: String, page: Int)
}

extension HurbAPI: TargetType {
    
    var headers: [String : String]? {
        return ["Content-type": "application/json"]
    }
    
    var baseURL: URL {
        // swiftlint:disable:next force_unwrapping
        return URL(string: "https://www.hurb.com")!
    }
    
    var path: String {
        switch self {
        case .search:
            return "/search/api"
        }
    }
    
    var method: Moya.Method {
        switch self {
        case .search: return .get
        }
    }
    
    var parameters: [String: Any]? {
        switch self {
        case .search(let query, let page):
            var parameters = [String: Any]()
            parameters["q"] = query
            parameters["page"] = page
            return parameters
        }
    }
    
    var parameterEncoding: ParameterEncoding {
        return URLEncoding.default
    }
    
    var sampleData: Data {
        var dataUrl: URL?
        switch self {
        case .search: dataUrl = Bundle.main.url(forResource: "HurbSample", withExtension: "json")
        }
        if let url = dataUrl, let data = try? Data(contentsOf: url) {
            debugPrint(data)
            return data
        }
        return Data()
    }
    
    var task: Task {
        switch self {
        case .search:
            if let parameters = parameters {
                return .requestParameters(parameters: parameters,
                encoding: parameterEncoding)
            }
            return .requestPlain
        }
    }
}

//
//  HurbAPI.swift
//  Alpha
//
//  Created by Theo Mendes on 20/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//
import Foundation
import Moya

enum HurbAPI {
    case search(query: String, page: Int)
}

extension HurbAPI: TargetType {

    var baseURL: URL {
        // swiftlint:disable:next force_unwrapping
        return URL(string: Environment.apiBaseURL)!
    }

    var path: String {
        switch self {
        case .search: return "/search/api"
        }
    }

    var method: Moya.Method {
        switch self {
        case .search: return .get
        }
    }

    var parameters: [String: Any]? {
        var params: [String: Any] = [:]
        switch self {
        case .search(let query, let page):
            params["q"] = query
            params["page"] = page
        }
        return params
    }

    public var parameterEncoding: ParameterEncoding {
        return URLEncoding.default
    }

    var task: Task {
        switch self {
        case .search:
            if let parameters = parameters {
                return .requestParameters(
                    parameters: parameters,
                    encoding: parameterEncoding)
            }
            return .requestPlain
        }
    }

    var headers: [String: String]? {
        return ["Content-type": "application/json"]
    }

    var sampleData: Data {
        var dataUrl: URL?
        switch self {
        case .search:
            dataUrl = Bundle.main.url(forResource: "Hurb", withExtension: "json")
        }
        if let url = dataUrl, let data = try? Data(contentsOf: url) {
            return data
        }
        return Data()
    }
}

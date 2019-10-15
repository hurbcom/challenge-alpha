//
//  APIRouter.swift
//  Alpha
//
//  Created by Theo Mendes on 15/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

import Alamofire

enum APIRouter: URLRequestConvertible {
    case search(parameters: SearchParams)

    private var method: HTTPMethod {
        switch self {
        case .search:
            return .get
        }
    }

    private var path: String {
        switch self {
        case .search:
            return "/search/api"
        }
    }

    func asURLRequest() throws -> URLRequest {
        let baseURL = try Environment.apiBaseURL.asURL()

        var urlRequest = URLRequest(url: baseURL.appendingPathComponent(path))

        urlRequest.httpMethod = method.rawValue

        urlRequest.setValue(
            NetworkConstants.ContentType.json.rawValue,
            forHTTPHeaderField: NetworkConstants.HTTPHeaderField.acceptType.rawValue)

        urlRequest.setValue(
            NetworkConstants.ContentType.json.rawValue,
            forHTTPHeaderField: NetworkConstants.HTTPHeaderField.contentType.rawValue)

        switch self {
        case .search(let parameters):
            urlRequest = try URLEncodedFormParameterEncoder.default.encode(parameters, into: urlRequest)
        }

        return urlRequest
    }
}

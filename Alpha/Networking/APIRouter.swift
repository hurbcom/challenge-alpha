//
//  APIRouter.swift
//  Alpha
//
//  Created by Theo Mendes on 15/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

import Alamofire

/// A Router for the API's endpoints
enum APIRouter: URLRequestConvertible {
    /// A search case
    case search(parameters: SearchParams)

    /// The methods for each of the cases
    private var method: HTTPMethod {
        switch self {
        case .search:
            return .get
        }
    }
    /// The paths of the API for each of the cases
    private var path: String {
        switch self {
        case .search:
            return "/search/api"
        }
    }

    /**
    Create the URL Request based in the method and path

    - Returns: A new `URLRequest`.
    */

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

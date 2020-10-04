//
//  APIRequest.swift
//  Challenge-ios
//
//  Created by Andre Dias on 29/09/20.
//  Copyright © 2020 Andre Dias. All rights reserved.

enum APIError: Error {
    case noInternet
    case HTTPError(statusCode: Int)
    case serverError(message: String)
    
    var description: String {
        switch self {
        case .noInternet:
            return "Sem conexão de internet"
        case .HTTPError(let statusCode):
            return "A chamada falhou com o códgio HTTP \(statusCode)."
        case .serverError(let message):
            return "Falha no servidor: \"\(message)\"."
        }
    }
}
protocol APIServiceProtocol {
    func getHotelsAndPackages(completion: @escaping (_ hotels: [HotelsResults]?, _ packages: [PackageResults]?, _ error: APIError?) ->())
}

class APIRequest: APIServiceProtocol {
    
    private let httpService = HttpService()
    
    // MARK: - Methods Services
    func getHotelsAndPackages(completion: @escaping (_ hotels: [HotelsResults]?, _ packages: [PackageResults]?, _ error: APIError?) ->()) {
        
        self.httpService.doGet(url: (BaseAPI.shared.baseURL)) { (hotels, packages, error)  in
            if error == nil {
                if let hotels = hotels, let packages = packages {
                   completion(hotels, packages, nil)
                }
            } else {
                completion(nil, nil, error)
            }
        }
    }
}


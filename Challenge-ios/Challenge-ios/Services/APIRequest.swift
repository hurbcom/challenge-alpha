//
//  APIRequest.swift
//  Challenge-ios
//
//  Created by Andre Dias on 29/09/20.
//  Copyright © 2020 Andre Dias. All rights reserved.
//
protocol APIServiceProtocol {
    func getHotelsAndPackages(completion: @escaping (_ hotels: [HotelsResults]?, _ packages: [PackageResults]?, _ error: String?) ->()) 
}

class APIRequest: APIServiceProtocol {
    
    private let httpService = HttpService()
    
    static let shared: APIRequest = {
        return APIRequest()
    }()
    
    // MARK: - Methods Services
//    ( complete: @escaping ( _ success: Bool, _ photos: [Photo], _ error: APIError? )->() )
    func getHotelsAndPackages(completion: @escaping (_ hotels: [HotelsResults]?, _ packages: [PackageResults]?, _ error: String?) ->()) {
        
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


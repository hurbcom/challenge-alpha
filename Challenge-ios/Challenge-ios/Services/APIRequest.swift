//
//  APIRequest.swift
//  Challenge-ios
//
//  Created by Andre Dias on 29/09/20.
//  Copyright © 2020 Andre Dias. All rights reserved.
//

class APIRequest: HttpService {
    
    static let shared: APIRequest = {
        return APIRequest()
    }()
    
    // MARK: - Methods Services
    func getBaseHotels(atPage page: Int, completion: (([HotelsResults], [PackageResults]) -> Void)!) {
        
        self.doGet(url: (BaseAPI.shared.getBaseHotels(atPage: page))) { (hotels, packages)  in
            completion(hotels, packages)
        }
    }
}


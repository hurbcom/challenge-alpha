//
//  HUAPIHelper.swift
//  Hotels
//
//  Created by Adolfho Athyla on 25/08/2018.
//  Copyright © 2018 a7hyla. All rights reserved.
//

import UIKit
import EVReflection
import Alamofire

class HUAPIHelper: NSObject {
    static let uri = "https://search.hotelurbano.com/api"
    static var alamofireManager: Alamofire.SessionManager?
    
    //MARK: - Config Alamofire
    static func configAlamofire() {
        let configuration = URLSessionConfiguration.default
        configuration.timeoutIntervalForResource = 50
        configuration.allowsCellularAccess = true
        HUAPIHelper.alamofireManager = Alamofire.SessionManager(configuration: configuration)
    }
    
    //MARK: - Hotels
    static func loadHotels(query: String = "Fortaleza", completionHandler: @escaping ((_ hotels: [Hotel]?, _ success: Bool) -> ())) {
        UIApplication.shared.isNetworkActivityIndicatorVisible = true
        HUAPIHelper.configAlamofire()
        
        let parameters: Parameters = [
            "q": query
        ]
        
        HUAPIHelper.alamofireManager?.request(uri, method: .get, parameters: parameters, encoding: URLEncoding.default, headers: nil).responseObject(completionHandler: { (response: DataResponse<Response>) in
            guard let hotelsResponse = response.response else { return }
            switch hotelsResponse.statusCode {
            case 200:
                if let hotels = response.result.value?.results {
                    completionHandler(hotels, true)
                }
            default:
                completionHandler(nil, false)
            }
            UIApplication.shared.isNetworkActivityIndicatorVisible = false
        })
        
    }
}

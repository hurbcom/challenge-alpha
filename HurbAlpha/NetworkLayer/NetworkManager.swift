//
//  NetworkManager.swift
//  HurbAlpha
//
//  Created by Rodrigo Bukowitz on 05/11/19.
//  Copyright © 2019 Rodrigo Bukowitz. All rights reserved.
//
import Moya
import os.log


/// enum representing the state machine of the network
enum NetworkState {
    case loading
    case ready
    case error
}

/// in order to simplify the access  to the NetworkManager capabilities
let netManager = NetworkManager.shared

/// This class resolves all management that requires network conection
///
/// This class is a singleton and can be acesses from anywhere in the code
class NetworkManager: HurbAlphaAPI {
    /// project pattern singleton
    static var shared = NetworkManager()
    
    /// enable logs on the network enviroment
    var enableLogs = true
    
    /// moya provider
    internal var provider = MoyaProvider<HurbOffersAPI>()
    
    /// state machine of the network
    var state: NetworkState = .loading {
        didSet {
            switch state {
            case .ready:
                os_log(.info, "Ready")
                
            case .loading:
                os_log(.info, "Loading")
                /// load all data
            case .error:
                os_log(.error, "Error")
            }
        }
    }
    
    /// method to get the offers
    func getOffers(place: String, page: Int, completion: @escaping ([HurbOffers]?, Error?) -> ()) {
        provider.request(.getOffers(query: place , page: page )) { (result) in
            switch result {
            case .success(let response):
                let decoder = JSONDecoder()
                do {
                    let res = try decoder.decode(APIRoot.self, from: response.data)
                    completion(res.results, nil)
                } catch (let error) {
                    completion(nil, error)
                }
            case .failure(let error):
                completion(nil, error)
            }
        }
    }
}


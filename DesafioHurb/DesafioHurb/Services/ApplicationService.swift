//
//  ApplicationService.swift
//  DesafioHurb
//
//  Created by Filipo Negrao on 22/10/19.
//  Copyright © 2019 Filipo Negrao. All rights reserved.
//

import Foundation
import Alamofire

private let data = ApplicationService()
class ApplicationService : NSObject {
    
    class var sharedInstance: ApplicationService {
        return data
    }
    
    /** Método responsável por recuperar os hoteis da API */
    func getHotels(
        searchText: String?,
        pageIndex: Int,
        callback: @escaping((_ hotels: [Hotel], _ error: String?)->())) {
        // Instancia url
        var url = "\(API_URL)?page=\(pageIndex)"
        if let term = searchText {
            url += "&q=" + term
        }
        // Instancia resultado
        var result = [Hotel]()
        // Faz a request
        AF.request(
        url,
        method: .get,
        parameters: nil,
        encoding: JSONEncoding.default,
        headers: nil, interceptor: nil).response { (response: AFDataResponse<Data?>) in
            guard let data = response.data else {
                callback(result, ERROR_SERVER_MESSAGE)
                return
            }
            // Transforma a resposta para dict
            do {
                guard let json = try JSONSerialization.jsonObject(with: data, options: .allowFragments) as? [String : Any] else {
                    callback(result, ERROR_SERVER_MESSAGE)
                    return
                }
                if let hotelsJson = json["results"] as? [[String: Any]] {
                    for hotelJson in hotelsJson {
                        let hotel = Hotel.fromDict(dict: hotelJson)
                        result.append(hotel)
                    }
                }
                callback(result, nil)
                
            } catch {
                callback(result, ERROR_SERVER_MESSAGE)
            }
        }
        
    }
    
}

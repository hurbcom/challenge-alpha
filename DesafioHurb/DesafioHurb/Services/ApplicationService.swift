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
    func getData(
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
    
    func getHotels(
           searchText: String?,
           pageIndex: Int,
           callback: @escaping((_ hotels: [Hotel], _ error: String?)->())) {
        
        self.getData(searchText: searchText, pageIndex: pageIndex) { (hotels: [Hotel], error: String?) in
            if let error = error {
                callback(hotels, error)
            }
            var filtered = [Hotel]()
            for hotel in hotels {
                if hotel.isHotel {
                    filtered.append(hotel)
                }
            }
            callback(filtered, error)
        }
    }
    
    func getHotelsAndPackages(
           searchText: String?,
           pageIndex: Int,
           callback: @escaping((_ hotels: [Hotel], _ packages: [Hotel], _ error: String?)->())) {
        
        self.getData(searchText: searchText, pageIndex: pageIndex) { (result: [Hotel], error: String?) in
            var hotels = [Hotel]()
            var packages = [Hotel]()
            if let error = error {
                callback(hotels, packages, error)
            }
            for hotel in result {
                if hotel.isHotel {
                    hotels.append(hotel)
                }
                if hotel.isPackage {
                    packages.append(hotel)
                }
            }
            
            hotels = hotels.sorted(by: { $0.stars > $1.stars })
            packages = packages.sorted(by: { $0.stars > $1.stars })

            callback(hotels, packages, error)
        }
    }
    
}

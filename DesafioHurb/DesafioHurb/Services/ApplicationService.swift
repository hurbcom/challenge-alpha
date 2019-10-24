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
        var url = "\(API_URL)"
        if let term = searchText {
            url += "?q=\(term)"
        }
        // Instancia resultado
        var result = [Hotel]()
        
        let escapedAddress = url.addingPercentEncoding(withAllowedCharacters: CharacterSet.urlQueryAllowed)
        
        // Faz a request
        AF.request(
            escapedAddress!,
            method: .get,
            parameters: nil,
            encoding: JSONEncoding.default,
            headers: nil, interceptor: nil).response { (response: AFDataResponse<Data?>) in
                guard let data = response.data else {
                    let error = response.error
                    print(error)
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
    
    func getHotelsAndPackages(
        searchText: String?,
        pageIndex: Int,
        callback: @escaping((_ hotelsByStars: [[Hotel]], _ packages: [Hotel], _ error: String?)->())) {
        
        self.getData(searchText: searchText, pageIndex: pageIndex) { (result: [Hotel], error: String?) in
            var categories = [[Hotel]]()
            var hotels5 = [Hotel]()
            var hotels4 = [Hotel]()
            var hotels3 = [Hotel]()
            var hotels2 = [Hotel]()
            var hotels1 = [Hotel]()
            var packages = [Hotel]()
            if let error = error {
                callback(categories, packages, error)
            }
            for hotel in result {
                if hotel.isHotel {
                    switch hotel.stars {
                    case 1:
                        hotels1.append(hotel)
                    case 2:
                        hotels2.append(hotel)
                    case 3:
                        hotels3.append(hotel)
                    case 4:
                        hotels4.append(hotel)
                    case 5:
                        hotels5.append(hotel)
                    default:
                        hotels1.append(hotel)
                    }
                }
                if hotel.isPackage {
                    packages.append(hotel)
                }
            }
            categories.append(hotels5)
            categories.append(hotels4)
            categories.append(hotels3)
            categories.append(hotels2)
            categories.append(hotels1)
            
            callback(categories, packages, error)
        }
    }
    
}

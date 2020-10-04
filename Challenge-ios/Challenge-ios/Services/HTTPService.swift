//
//  HTTPService.swift
//  Challenge-ios
//
//  Created by Andre Dias on 29/09/20.
//  Copyright © 2020 Andre Dias. All rights reserved.
//

import Foundation
import Alamofire

class HttpService: NSObject {
    
    func doGet(url:String, completion: @escaping (_ hotels: [HotelsResults]?, _ packages: [PackageResults]?, _ error: APIError?) ->()) {
   
        UIApplication.shared.isNetworkActivityIndicatorVisible = true
        AF.request(url, method: .get, parameters: nil, headers: nil).responseJSON { response in

            switch response.result {
            case .success:
                UIApplication.shared.isNetworkActivityIndicatorVisible = false

                debugPrint(response.value as Any)
                var hotels = [HotelsResults]()
                var packages = [PackageResults]()
                if let hotelResult = self.getHotelFrom(response: response) {
                    hotels = hotelResult
                }
                if let packageResult = self.getPackagesFrom(response: response) {
                    packages = packageResult
                }
                
                completion(hotels, packages, nil)
            case .failure(let error):
                 let error = self.handleError(error: error, statusCode: response.response?.statusCode ?? 0)
                completion(nil, nil, error)
            }
            
        }
    }
    private func handleError(error: AFError, statusCode: Int) -> APIError {
        print("Error \(error.localizedDescription)")
        switch statusCode {
            case 300...499:
                return APIError.HTTPError(statusCode: statusCode)
            case 500...599:
                return APIError.serverError(message: error.localizedDescription)
            default:
                return APIError.noInternet
        }
    }
    
    // Lógica para pegar o node de hoteis dentro do response da chamada
    private func getHotelFrom(response:AFDataResponse<Any>) -> [HotelsResults]? {
        if let result = (response.value as? [String : AnyObject])?[Constants.nodeResult] as? [[String : AnyObject]] {
            var hotelArray = [[String : AnyObject]]()
            for item in result {
                if (item[Constants.nodeIsHotel] as? Bool) == true {
                    hotelArray.append(item)
                }
            }
            
            return self.parseJsonHotelData(data: hotelArray)
        } else {
            return nil
        }
    }
    
    // Lógica para pegar o node de pacotes dentro do response da chamada
    private func getPackagesFrom(response:AFDataResponse<Any>) -> [PackageResults]? {
        if let result = (response.value as? [String : AnyObject])?[Constants.nodeResult] as? [[String : AnyObject]] {
            var PackageArray = [[String : AnyObject]]()
            for item in result {
                if (item[Constants.nodeIsPackage] as? Bool) == true {
                    PackageArray.append(item)
                }
            }
            
            return self.parseJsonPackageData(data: PackageArray)
        } else {
            return nil
        }
    }

    private func parseJsonHotelData(data: [[String : AnyObject]]) -> [HotelsResults] {
     
        var hotels = [HotelsResults]()
        let decoder = JSONDecoder()
        
        do {
            let dataConverted = try JSONSerialization.data(withJSONObject: data, options: [])
            let hotelsList = try decoder.decode([HotelsResults].self, from: dataConverted)
            hotels = hotelsList
     
        } catch {
            print(error)
        }
     
        return hotels
    }
    
    private func parseJsonPackageData(data: [[String : AnyObject]]) -> [PackageResults] {
     
        var hotels = [PackageResults]()
        let decoder = JSONDecoder()
        
        do {
            let dataConverted = try JSONSerialization.data(withJSONObject: data, options: [])
            let hotelsList = try decoder.decode([PackageResults].self, from: dataConverted)
            hotels = hotelsList
     
        } catch {
            print(error)
        }
     
        return hotels
    }
}





//
//  HTTPService.swift
//  Challenge-ios
//
//  Created by Andre Dias on 29/09/20.
//  Copyright © 2020 Andre Dias. All rights reserved.
//

import Foundation
import Alamofire

class HttpService: NSObject{
    
    func doGet(url:String, completion:@escaping (([HotelsResults]) -> Void)) {
   
        UIApplication.shared.isNetworkActivityIndicatorVisible = true
        AF.request(url, method: .get, parameters: nil, headers: nil).responseJSON { response in

            switch response.result {
            case .success:
                UIApplication.shared.isNetworkActivityIndicatorVisible = false

                debugPrint(response.value)

                if let result = (response.value as? [String : AnyObject])?["results"] as? [[String : AnyObject]] {
                    var hotelArray = [[String : AnyObject]]()
                    for item in result {
                        if (item["isHotel"] as? Bool) == true {
                            hotelArray.append(item)
                        }
                    }
                    
                    let hotels = self.parseJsonData(data: hotelArray)
                    
                    completion(hotels)
                }

            case .failure(let error):
                print("Error \(error.localizedDescription)")

            }
            
        }
    }

    func parseJsonData(data: [[String : AnyObject]]) -> [HotelsResults] {
     
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
}





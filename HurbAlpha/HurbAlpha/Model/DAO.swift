//
//  DAO.swift
//  HurbAlpha
//
//  Created by Julia Rocha on 04/09/19.
//  Copyright © 2019 Julia Rocha. All rights reserved.
//

import Foundation

protocol DAORequester {
    func readedDataFromJson(result:Hotel)
}

class DAO {
    
    static let instance = DAO()
    private init() {}
    
    func jsonDataRequest (page:Int, requester: DAORequester) {
        let url = "https://www.hurb.com/search/api?q=gramado&page=\(String(page))"
        let urlObj = URL(string: url)
        URLSession.shared.dataTask(with: urlObj!) { (data, response, error) in
            do {

                // Json to Hotel
                let result = try JSONDecoder().decode(Hotel.self, from: data!)
                requester.readedDataFromJson(result: result)

                // Logic after response has arrived
                DispatchQueue.main.async {
                    debugPrint("main.async")
                }
            } catch {
                debugPrint(error)
            }
            }.resume()
    }
}

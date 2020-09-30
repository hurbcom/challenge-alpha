//
//  BaseAPI.swift
//  Challenge-ios
//
//  Created by Andre Dias on 29/09/20.
//  Copyright © 2020 Andre Dias. All rights reserved.
//

import Foundation

class BaseAPI: NSObject {
    
    static let shared: BaseAPI = {
        return BaseAPI()
    }()
    
    let baseURL = "https://www.hurb.com/search/api?q=buzios"
    
    private func getURL(_ urlSufix: String) -> String {
        let url: String = self.baseURL + urlSufix
        return url
    }
    
    public func getBaseHotels(atPage: Int) -> String {
        return getURL("&page=\(atPage)")
    }

}


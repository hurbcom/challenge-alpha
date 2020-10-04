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
    
    private(set) var baseURL = Constants.baseURL
}


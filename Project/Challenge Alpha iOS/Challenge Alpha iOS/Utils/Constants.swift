//
//  Constants.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 19/01/23.
//

import Foundation

struct Constants {
    private init() {}
    
    static let BUNDLE_NAME = "com.yuri.Challenge-Alpha-iOS"
    static let DEFAULT_DESTINATION = "Rio de Janeiro, Brasil"
    
    struct USER_DEFAULTS {
        private init() {}
        
        static let LAST_SEARCHED_HOTEL = "LAST_SEARCHED_HOTEL"
        static let LAST_SEARCHED_PACKAGE = "LAST_SEARCHED_PACKAGE"
    }
}

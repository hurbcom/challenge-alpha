//
//  BaseService.swift
//  GPSurb
//
//  Created by Gilson Santos on 16/10/19.
//  Copyright © 2019 Gilson Santos. All rights reserved.
//

import Foundation
import Alamofire

// MARK: - SERVICE ERROR TYPE -
public enum ErrorType: Error {
    case errorParsingJSON
    case errorURL
    case error404
    case errorLocalBundle
    case errorData
    case errorGeneric
}

open class BaseService: NSObject {
    public lazy var sessionManager = SessionManager.default
    func getHost() -> String {
        return "https://www.hurb.com"
    }
}

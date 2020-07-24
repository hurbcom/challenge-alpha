//
//  NSError.swift
//  Hurb Alpha
//
//  Created by Thiago Augusto on 24/07/20.
//  Copyright © 2020 objectivesev. All rights reserved.
//

import Foundation

extension NSError {
    class func from(code: Int, data: Data, description: String) -> NSError {
        let domain = Bundle.main.bundleIdentifier ?? "undefined"
        let userInfo = [NSLocalizedDescriptionKey : description]
        let error = NSError(domain: domain, code: code, userInfo: userInfo)
        return error
    }
}

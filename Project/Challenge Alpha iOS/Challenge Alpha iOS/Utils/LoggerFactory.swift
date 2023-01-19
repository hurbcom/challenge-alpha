//
//  LoggerFactory.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 19/01/23.
//

import Foundation
import os.log

enum LoggerFactory {
    static func createLogger<T>(class: T.Type) -> Logger {
        let bundleName = Constants.BUNDLE_NAME
        let className = String(describing: T.self)
        
        let logger = Logger(subsystem: bundleName, category: className)
        return logger
    }
}

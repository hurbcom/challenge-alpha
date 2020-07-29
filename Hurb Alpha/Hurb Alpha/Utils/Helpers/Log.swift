//
//  Log.swift
//  Hurb Alpha
//
//  Created by Thiago Augusto on 24/07/20.
//  Copyright © 2020 objectivesev. All rights reserved.
//

import Foundation

class Log {

    static let shared = Log()
        
    internal func show(info: Any) {
        print("HA Info: \(info)")
    }

    internal func show(error: Any) {
        print("HA error: \(error)")
    }

    internal func show(event: Any) {
        print("HA event: \(event)")
    }
}

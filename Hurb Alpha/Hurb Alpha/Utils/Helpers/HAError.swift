//
//  HAError.swift
//  Hurb Alpha
//
//  Created by Thiago Augusto on 30/07/20.
//  Copyright © 2020 objectivesev. All rights reserved.
//

import Foundation

enum HAError: Error {
    case invalidQuery
    
    var code: Int {
        switch self {
        case .invalidQuery: return 555
        }
    }
}

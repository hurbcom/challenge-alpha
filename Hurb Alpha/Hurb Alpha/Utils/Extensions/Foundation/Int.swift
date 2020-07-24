//
//  Int.swift
//  Hurb Alpha
//
//  Created by Thiago Augusto on 24/07/20.
//  Copyright © 2020 objectivesev. All rights reserved.
//

import Foundation


extension Int {
    func isInternetErrorCode() -> Bool {
        return [0, -1001, 408, -1009].contains(self)
    }
    
    func isSuccessCode() -> Bool {
        return (200...299).contains(self)
    }
}

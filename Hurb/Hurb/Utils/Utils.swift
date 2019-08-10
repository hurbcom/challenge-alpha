//
//  Utils.swift
//  Hurb
//
//  Created by Alexandre Papanis on 08/08/19.
//  Copyright © 2019 Papanis. All rights reserved.
//

import Foundation

class Utils {
    
    static func convertDoubleToCurrencyString(number: Double) -> String {
        let formatter = NumberFormatter()
        formatter.locale = Locale(identifier: "pt_BR")
        formatter.numberStyle = .currency
        return formatter.string(from: number as NSNumber) ?? ""
    }
    
}

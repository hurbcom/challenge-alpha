//
//  NumberFormatter.swift
//  Hurb-iOS-Challenge
//
//  Created by Rômulo Monteiro on 10/01/23.
//

import Foundation

extension NumberFormatter {
    
    static func currencyFormatter(from currency: String, fractionDigits: Int = 0) -> NumberFormatter {
        
        let formatter: NumberFormatter = NumberFormatter()
        formatter.numberStyle = .currency
        formatter.currencyCode = currency
        formatter.maximumFractionDigits = fractionDigits
        formatter.locale = Locale.current
        
        return formatter
    }
}

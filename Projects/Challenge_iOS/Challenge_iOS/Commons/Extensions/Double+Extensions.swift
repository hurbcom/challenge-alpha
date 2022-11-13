//
//  Double+Extensions.swift
//  Challenge_iOS
//
//  Created by Helio Junior on 13/11/22.
//

import Foundation

extension Double {
    func formatCurrency(from currency: String?, fractionDigits: Int = 0) -> String {
        let formatter = NumberFormatter()
        formatter.numberStyle = .currency
        formatter.currencyCode = currency ?? "BRL"
        formatter.maximumFractionDigits = fractionDigits
        let number = NSNumber(value: self)
        return formatter.string(from: number)!
    }
}

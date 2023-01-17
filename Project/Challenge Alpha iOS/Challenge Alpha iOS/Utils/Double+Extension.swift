//
//  Double+Extension.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 17/01/23.
//

import Foundation

extension Double {
    func formatCurrency(_ currency: String?) -> String {
        let nsNumber = NSNumber(value: self)
        
        let formatter = NumberFormatter()
        formatter.numberStyle = .currency
        formatter.currencyCode = currency ?? "BRL"
        formatter.maximumFractionDigits = 0
        formatter.locale = Locale(identifier: "pt-BR")
        
        let string = formatter.string(from: nsNumber) ?? ""
        
        return string
    }
}

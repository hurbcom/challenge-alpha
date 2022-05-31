//
//  CurrencyUtils.swift
//  Hurb_iOS_Challenge
//
//  Created by Gáudio Ney on 24/05/22.
//

import UIKit

class CurrencyUtils {
    class func formatPrice(price: Double) -> String {
        let currencyFormatter = NumberFormatter()
        currencyFormatter.usesGroupingSeparator = true
        currencyFormatter.numberStyle = .currency
        currencyFormatter.locale = Locale(identifier: "pt_BR")
        return currencyFormatter.string(from: NSNumber(value: price))!
    }
}

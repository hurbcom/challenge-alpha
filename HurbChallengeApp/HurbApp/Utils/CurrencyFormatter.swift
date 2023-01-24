//
//  CurrencyFormatter.swift
//  HurbApp
//
//  Created by Fabio Takahashi on 24/01/23.
//

import Foundation

struct CurrencyFormatter {

    static func getPriceFormatted(price: SearchResultPrice) -> String? {

        let numberFormatter = NumberFormatter()
        numberFormatter.numberStyle = .currency
        numberFormatter.currencyCode = price.currency
        numberFormatter.maximumFractionDigits = 2

        let numberAmount = NSNumber(value: price.amount)

        return numberFormatter.string(from: numberAmount)
    }

}

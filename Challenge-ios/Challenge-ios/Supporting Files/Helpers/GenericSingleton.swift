//
//  GenericSingleton.swift
//  Challenge-ios
//
//  Created by Andre Dias on 02/10/20.
//  Copyright © 2020 Andre Dias. All rights reserved.
//

import Foundation
import UIKit

class GenericSingleton: NSObject {
    
    static let shared: GenericSingleton = {
        return GenericSingleton()
    }()

   let currencyFormatter: NumberFormatter = {
        let currencyFormatter = NumberFormatter()
        currencyFormatter.locale = Locale.init(identifier: "pt_BR")
        currencyFormatter.numberStyle = .currency
        return currencyFormatter
    }()
    
    
    func setAtributtedText(color: UIColor, font: UIFont, string: String) -> NSAttributedString {
        let attributes: [NSAttributedString.Key : Any] = [
                  NSAttributedString.Key.foregroundColor: color,
                  NSAttributedString.Key.font: font]
         return NSAttributedString(string: string, attributes: attributes)
    }
}

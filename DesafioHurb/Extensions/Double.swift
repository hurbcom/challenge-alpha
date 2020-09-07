//
//  Double.swift
//  DesafioHurb
//
//  Created by Edson Aparecido Guido on 07/09/20.
//  Copyright © 2020 Edson Aparecido Guido. All rights reserved.
//

import Foundation

extension Double {
    
    func currencyFormatted() -> String {
        let formatter = NumberFormatter()
        formatter.numberStyle = .currency
        formatter.locale = Locale(identifier: "pt_BR")
        return formatter.string(from: NSNumber(value: self))!
    }
    
}

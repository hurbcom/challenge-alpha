//
//  ExtensionDouble .swift
//  HotelUrbanoChallenge
//
//  Created by maciosdev on 27/07/2018.
//  Copyright © 2018 Ronilson. All rights reserved.
//

import Foundation

extension Double {
    // Creates a string using the double value, formated as: `R$ {VALUE}`
    func formatedAsCurreny() -> String {
        return String(format: "R$ %.2f", locale: Locale(identifier: "pt_BR"), self)
    }
}

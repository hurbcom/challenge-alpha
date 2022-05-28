//
//  StringUtils.swift
//  Hurb_iOS_Challenge
//
//  Created by Gáudio Ney on 24/05/22.
//

import Foundation

extension String {
    /// Function that set the first letter of the string to uppercased style.
    /// - Returns: String.
    func capitalizedFirstLetter() -> String {
        return self.prefix(1).uppercased() + self.lowercased().dropFirst()
    }
}

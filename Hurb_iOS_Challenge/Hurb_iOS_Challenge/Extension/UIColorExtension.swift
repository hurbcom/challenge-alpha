//
//  UIColorExtension.swift
//  Hurb_iOS_Challenge
//
//  Created by Gáudio Ney on 29/05/22.
//

import UIKit

// MARK: - UIColor
/// Set all custom collors used.
extension UIColor {
    static func rgb(red: CGFloat, green: CGFloat, blue: CGFloat) -> UIColor {
        return UIColor(red: red/255, green: green/255, blue: blue/255, alpha: 1)
    }
    /// Freen Cancelation Green:
    static let freeCancelationGreen = UIColor.rgb(red: 56, green: 150, blue: 50)
    
    /// Hurb Main Blue:
    static let hurbMainBlue = UIColor.rgb(red: 31, green: 96, blue: 245)
}

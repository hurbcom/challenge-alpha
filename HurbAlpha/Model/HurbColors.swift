//
//  HurbColors.swift
//  HurbAlpha
//
//  Created by Rodrigo Bukowitz on 06/11/19.
//  Copyright © 2019 Rodrigo Bukowitz. All rights reserved.
//
import UIKit

/// Extension to represent all the colors for the app
extension UIColor {
    public class var hurbYellow: UIColor {
        return UIColor(named: "HurbYellow") ?? UIColor.yellow
    }
    public class var hurbBlue: UIColor {
        return UIColor(named: "HurbBlue") ?? UIColor.blue
    }
}

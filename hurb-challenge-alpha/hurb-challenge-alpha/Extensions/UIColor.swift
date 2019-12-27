//
//  UIColor.swift
//  hurb-challenge-alpha
//
//  Created by Hannah  on 26/12/2019.
//  Copyright © 2019 Hannah . All rights reserved.
//

import SwiftUI

///extension responsible for adding the hex property to the Color Class
extension Color {
    init(hex string: String) {
        var string: String = string.trimmingCharacters(in: CharacterSet.whitespacesAndNewlines)
        if string.hasPrefix("#") {
            _ = string.removeFirst()
        }

        // Double the last value if incomplete hex
        if !string.count.isMultiple(of: 2), let last = string.last {
            string.append(last)
        }

        // Fix invalid values
        if string.count > 8 {
            string = String(string.prefix(8))
        }

        // Scanner creation
        let scanner = Scanner(string: string)

        var color: UInt64 = 0
        scanner.scanHexInt64(&color)

        if string.count == 2 {
            let mask = 0xFF

            let gColor = Int(color) & mask

            let gray = Double(gColor) / 255.0

            self.init(.sRGB, red: gray, green: gray, blue: gray, opacity: 1)

        } else if string.count == 4 {
            let mask = 0x00FF

            let gColor = Int(color >> 8) & mask
            let aColor = Int(color) & mask

            let gray = Double(gColor) / 255.0
            let alpha = Double(aColor) / 255.0

            self.init(.sRGB, red: gray, green: gray, blue: gray, opacity: alpha)

        } else if string.count == 6 {
            let mask = 0x0000FF
            let rColor = Int(color >> 16) & mask
            let gColor = Int(color >> 8) & mask
            let bColor = Int(color) & mask

            let redColor = Double(rColor) / 255.0
            let green = Double(gColor) / 255.0
            let blue = Double(bColor) / 255.0

            self.init(.sRGB, red: redColor, green: green, blue: blue, opacity: 1)

        } else if string.count == 8 {
            let mask = 0x000000FF
            let rColor = Int(color >> 24) & mask
            let gColor = Int(color >> 16) & mask
            let bColor = Int(color >> 8) & mask
            let aColor = Int(color) & mask

            let redColor = Double(rColor) / 255.0
            let green = Double(gColor) / 255.0
            let blue = Double(bColor) / 255.0
            let alpha = Double(aColor) / 255.0

            self.init(.sRGB, red: redColor, green: green, blue: blue, opacity: alpha)

        } else {
            self.init(.sRGB, red: 1, green: 1, blue: 1, opacity: 1)
        }
    }
}

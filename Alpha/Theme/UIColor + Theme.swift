//
//  UIColor + Theme.swift
//  Alpha
//
//  Created by Theo Mendes on 21/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

import UIKit

extension UIColor: ThemeProvider { }

extension Theme where Base: UIColor { // all app colors should be available in UIColor.theme namespace
    static var hurbBlue: UIColor { return UIColor(asset: Asset.hurbBlue) }
    static var hurbOrange: UIColor { return UIColor(asset: Asset.hurbOrange) }
    static var hurbYellow: UIColor { return UIColor(asset: Asset.hurbYellow) }
    static var hurbGray: UIColor { return UIColor(asset: Asset.hurbGray) }
    static var hurbLightGray: UIColor { return UIColor(asset: Asset.hurbLightGray) }
    static var hurbDarkGray: UIColor { return UIColor(asset: Asset.hurbDarkGray) }
}


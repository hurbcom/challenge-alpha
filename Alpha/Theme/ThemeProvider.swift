//
//  ThemeProvider.swift
//  Alpha
//
//  Created by Theo Mendes on 21/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

import Foundation

struct Theme<Base> { }

protocol ThemeProvider { }

extension ThemeProvider {
    static var theme: Theme<Self>.Type { return Theme<Self>.self }

    var theme: Theme<Self> { return Theme<Self>() } // theoretically unneccessary allocation overhead every call, but SnapKit uses the same pattern so...
}


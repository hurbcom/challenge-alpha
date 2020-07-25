//
//  UIView+Extensions.swift
//  HurbHotel_iOS
//
//  Created by Helio Junior on 25/07/20.
//  Copyright © 2020 HelioTecnologia. All rights reserved.
//

import UIKit

extension UIView {
    class func fromNib<T: UIView>() -> T {
        return Bundle.main.loadNibNamed(String(describing: T.self), owner: nil, options: nil)![0] as! T
    }
}

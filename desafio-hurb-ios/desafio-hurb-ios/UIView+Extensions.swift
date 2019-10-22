//
//  UIView+Extensions.swift
//  desafio-hurb-ios
//
//  Created by Vinícius Barcelos on 21/10/19.
//  Copyright © 2019 Vinícius Barcelos. All rights reserved.
//

import UIKit

extension UIView {
    
    func roundedCorner(with radius: Float) {
        self.layer.cornerRadius = CGFloat(radius)
        self.clipsToBounds = true
        self.layer.masksToBounds = true
    }

    func hide() {
        self.isHidden = true
    }
    
    func show() {
        self.isHidden = false
    }
}

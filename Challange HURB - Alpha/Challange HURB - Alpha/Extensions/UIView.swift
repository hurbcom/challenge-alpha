//
//  UIView.swift
//  Challange HURB - Alpha
//
//  Created by Luiz Fernando Cunha Duarte on 12/11/19.
//  Copyright © 2019 Luiz Fernando Cunha Duarte. All rights reserved.
//

import UIKit

extension UIView {
    
     // Extracted from https://stackoverflow.com/questions/10167266/
    
    /// Allows to round the selected corners of the UIView
    /// - Parameter corners: Array of corners to be rounded
    /// - Parameter radius: The corner radius
    func roundCorners(corners: UIRectCorner, radius: CGFloat) {
        let path = UIBezierPath(roundedRect: bounds, byRoundingCorners: corners, cornerRadii: CGSize(width: radius, height: radius))
        let mask = CAShapeLayer()
        mask.path = path.cgPath
        layer.mask = mask
    }
}

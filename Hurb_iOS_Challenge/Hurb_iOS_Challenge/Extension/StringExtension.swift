//
//  StringExtension.swift
//  Hurb_iOS_Challenge
//
//  Created by Gáudio Ney on 06/06/22.
//

import UIKit

extension String {
    /// Function get the height of a ULabel based on it text imput.
    func height(constraintedWidth width: CGFloat, font: UIFont) -> CGFloat {
        let label =  UILabel(frame: CGRect(x: 0, y: 0, width: width, height: .greatestFiniteMagnitude))
        label.numberOfLines = 0
        label.text = self
        label.font = font
        label.sizeToFit()

        return label.frame.height
     }
}

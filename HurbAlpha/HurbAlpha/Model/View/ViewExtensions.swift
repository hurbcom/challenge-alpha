//
//  ViewExtentions.swift
//  HurbAlpha
//
//  Created by Julia Rocha on 04/09/19.
//  Copyright © 2019 Julia Rocha. All rights reserved.
//

import UIKit

extension UIColor {
    static let baseBlue = UIColor(red:17.0/255.0, green: 41.0/255.0, blue: 104.0/255.0, alpha: 1.0)
    static let basePink = UIColor(red:221.0/255.0, green: 0.0/255.0, blue: 116.0/255.0, alpha: 1.0)
    static let baseOrange = UIColor(red:231.0/255.0, green: 85.0/255.0, blue: 7.0/255.0, alpha: 1.0)
    static let baseGreen = UIColor(red:178.0/255.0, green: 196.0/255.0, blue: 40.0/255.0, alpha: 1.0)
}

extension UIViewController {
    func hideKeyboardWhenTappedAround() {
        let tap: UITapGestureRecognizer = UITapGestureRecognizer(target: self, action: #selector(UIViewController.dismissKeyboard))
        tap.cancelsTouchesInView = false
        view.addGestureRecognizer(tap)
    }
    @objc func dismissKeyboard() {
        view.endEditing(true)
    }
}

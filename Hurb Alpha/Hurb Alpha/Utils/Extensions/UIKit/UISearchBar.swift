//
//  UISearchBar.swift
//  Hurb Alpha
//
//  Created by Thiago Augusto on 28/07/20.
//  Copyright © 2020 objectivesev. All rights reserved.
//

import UIKit

extension UISearchBar {
    /// Returns the`UITextField` that is placed inside the text field.
    var textField: UITextField? {
        if #available(iOS 13, *) {
            return searchTextField
        } else {
            return self.value(forKey: "searchField") as? UITextField
        }
    }
}

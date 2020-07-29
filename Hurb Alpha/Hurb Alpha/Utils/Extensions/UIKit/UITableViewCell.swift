//
//  UITableViewCell.swift
//  Hurb Alpha
//
//  Created by Thiago Augusto on 28/07/20.
//  Copyright © 2020 objectivesev. All rights reserved.
//

import UIKit

public extension UITableViewCell {
    
    /** Return identifier with the same name of the subclass */
    static var defaultIdentifier: String {
        return String(describing: self)
    }
}

public extension UITableViewHeaderFooterView {
    
    static var defaultIdentifier: String {
        return String(describing: self)
    }
}

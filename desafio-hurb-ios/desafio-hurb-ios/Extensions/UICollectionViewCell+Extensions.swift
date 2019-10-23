//
//  UICollectionViewCell+Extensions.swift
//  desafio-hurb-ios
//
//  Created by Vinícius Barcelos on 21/10/19.
//  Copyright © 2019 Vinícius Barcelos. All rights reserved.
//

import UIKit

extension UICollectionViewCell {
    class var identifier: String {
        return String(describing: self)
    }
}

extension UITableViewCell {
    class var identifier: String {
        return String(describing: self)
    }
}

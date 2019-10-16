//
//  ReusableIdentifier.swift
//  GPSurb
//
//  Created by Gilson Santos on 16/10/19.
//  Copyright © 2019 Gilson Santos. All rights reserved.
//

import UIKit

protocol ReusableIdentifier: class {
    static var reusableIdentifier: String { get }
}

extension ReusableIdentifier {
    static var reusableIdentifier: String {
        return String(describing: self)
    }
}

extension UITableViewCell: ReusableIdentifier {}
extension UICollectionViewCell: ReusableIdentifier {}

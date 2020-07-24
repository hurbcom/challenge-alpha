//
//  UICollectionViewCell+Extensions.swift
//  HurbHotel_iOS
//
//  Created by Helio Junior on 24/07/20.
//  Copyright © 2020 HelioTecnologia. All rights reserved.
//

import UIKit

extension UICollectionViewCell {
    var identifier: String {
        return String(describing: Self.self)
    }
}

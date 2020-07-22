//
//  UITableViewCell+Extensions.swift
//  HurbHotel_iOS
//
//  Created by Helio Junior on 22/07/20.
//  Copyright © 2020 HelioTecnologia. All rights reserved.
//

import UIKit

extension UITableViewCell {
    var identifier: String {
        return String(describing: Self.self)
    }
}

//
//  UIImageView+Extensions.swift
//  HurbHotel_iOS
//
//  Created by Helio Junior on 22/07/20.
//  Copyright © 2020 HelioTecnologia. All rights reserved.
//

import UIKit

extension UIImageView {
    func tintImage(color: UIColor) {
        guard let image = image else {
            return
        }
        switch image.renderingMode {
        case .alwaysTemplate:
            break
        default:
            self.image = image.withRenderingMode(.alwaysTemplate)
        }
        tintColor = color
    }
}

//
//  UIImageView+Helpers.swift
//  HotelSearchiOS
//
//  Created by Tulio Parreiras on 20/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import UIKit

extension UIImageView {
    func setImageAnimated(_ newImage: UIImage?) {
        image = newImage
        
        guard newImage != nil else { return }
        
        alpha = 0
        UIView.animate(withDuration: 0.25) {
            self.alpha = 1
        }
    }
}


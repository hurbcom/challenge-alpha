//
//  UIImageView+KF.swift
//  desafio-hurb-ios
//
//  Created by Vinícius Barcelos on 22/10/19.
//  Copyright © 2019 Vinícius Barcelos. All rights reserved.
//

import UIKit
import Kingfisher

extension UIImageView {
    
    func setImage(with string: String, placeholder: String) {
        guard let imageURL = URL(string: string) else {return}
        self.kf.setImage(with: imageURL, placeholder: UIImage(named: placeholder))
    }
    
}

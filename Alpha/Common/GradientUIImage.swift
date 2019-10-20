//
//  GradientUIImage.swift
//  Alpha
//
//  Created by Theo Mendes on 19/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

import Foundation
import UIKit

class GradientUIImage: UIImageView {
    let gradient: CAGradientLayer = {
        let gradient = CAGradientLayer()
//        gradient.startPoint = CGPoint(x: 0, y: 0)
//        gradient.endPoint = CGPoint(x: 1, y: 1)
        gradient.isOpaque = false
//        gradient.locations = [0.0, 0.3, 0.5, 0.7, 1.0]
        gradient.locations = [0.0, 0.9]
        return gradient
    }()

  override init(frame: CGRect) {
    super.init(frame: frame)
    self.setup()
  }

  required init(coder aDecoder: NSCoder) {
    fatalError("init(coder:) has not been implemented")
  }

  func setup() {
    gradient.colors = [
        UIColor.clear.cgColor,
        UIColor(red: 0, green: 0, blue: 0, alpha: 0.5).cgColor
    ]
    self.layer.addSublayer(gradient)
  }

  override func layoutSubviews() {
    gradient.frame = self.layer.bounds
  }
}

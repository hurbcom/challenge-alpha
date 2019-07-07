//
//  ShadowView.swift
//  HurbChallenge
//
//  Created by Felipe Alves on 07/07/19.
//  Copyright © 2019 Bolzaniapps. All rights reserved.
//

import Foundation
import UIKit

/*
 Essa classe permite criar uma view com shadow e cornerRadius da forma correta
 */
class ShadowView: UIView {
    override var bounds: CGRect {
        didSet {
            setupShadow()
        }
    }
    
    private func setupShadow() {
        self.layer.cornerRadius = 5
        self.layer.shadowOffset = CGSize(width: 1, height: 1)
        self.layer.shadowRadius = 2
        self.layer.shadowOpacity = 0.4
        self.layer.shadowPath = UIBezierPath(roundedRect: self.bounds, byRoundingCorners: .allCorners, cornerRadii: CGSize(width: 5, height: 5)).cgPath
        self.layer.shouldRasterize = true
        self.layer.rasterizationScale = UIScreen.main.scale
    }
}

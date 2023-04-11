//
//  UIViewExtension.swift
//  iTravel_iOS_Challenge
//
//  Created by convidado on 09/04/23.
//

import Foundation
import UIKit

//cria sombras em views
extension UIView {
    func dropShadow(scale: Bool = true) {
        layer.masksToBounds = false
        layer.shadowColor = UIColor.black.cgColor
        layer.shadowOpacity = 0.2
        layer.shadowOffset = .zero
        layer.shadowRadius = 1
        layer.shouldRasterize = true
        layer.rasterizationScale = scale ? UIScreen.main.scale : 1
    }
}

// adiciona efeito de blur em views
extension UIView {
    func addBlurToView() {
        var blurEffect: UIBlurEffect!
        blurEffect = UIBlurEffect(style: .dark)
        let blurredEffectView = UIVisualEffectView(effect: blurEffect)
        blurredEffectView.frame = self.bounds
        blurredEffectView.alpha = 0.8
        blurredEffectView.autoresizingMask = [.flexibleWidth, .flexibleHeight]
        self.addSubview(blurredEffectView)
    }
    
    func removeBlurFromView() {
        for subview in self.subviews {
            if subview is UIVisualEffectView {
                subview.removeFromSuperview()
            }
        }
    }
    
}

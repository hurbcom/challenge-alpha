//
//  UIView+Extensions.swift
//  Challenge Alpha iOS (iOS)
//
//  Created by Kleiton Mendes on 16/01/23.
//

import UIKit

extension UIView {
// TODO: Remover espaçamento desnecessário, eu sugeria pra você criar uma extensão por funcionalidade
// exemplo: UIView+Background contendo esse método, deixa a classe mais concisa e segue o príncipio de responsabilidade única 😉
    func setBackground() {
        let gradient = CAGradientLayer()
        gradient.frame = self.bounds
        gradient.colors = [UIColor.lightBackground.cgColor, UIColor.darkBackground.cgColor]
        gradient.startPoint = CGPoint(x: 0, y: 0)
        gradient.endPoint = CGPoint(x: 0, y: 1)
        self.layer.addSublayer(gradient)
    }
}


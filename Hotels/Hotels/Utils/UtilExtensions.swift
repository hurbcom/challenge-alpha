//
//  UtilExtensions.swift
//  Hotels
//
//  Created by Adolfho Athyla on 25/08/2018.
//  Copyright © 2018 a7hyla. All rights reserved.
//

import UIKit

extension Double {
    //estrutura definida para transformar um Double em formato de Reais (R$)
    func getMoneyValue() -> String {
        Double.currencyFormatter.locale = Locale(identifier: "pt_BR")
        let stringValue = String(format: "%.2f", self)
        let money = (Double(stringValue)?.currency)!
        return money.replacingOccurrences(of: "R$", with: "")
    }
    
    static var currencyFormatter: NumberFormatter = {
        let numberFormatter = NumberFormatter()
        numberFormatter.numberStyle = .currency
        return numberFormatter
    }()
    
    var currency: String {
        return Double.currencyFormatter.string(from: self as NSNumber) ?? ""
    }
}


extension UIView {
    
    //método utilizado para arredondar bordas de determinada view
    func roundCorners(_ corners: UIRectCorner, radius: CGFloat) {
        let path = UIBezierPath(roundedRect: self.bounds, byRoundingCorners: corners, cornerRadii: CGSize(width: radius, height: radius))
        let mask = CAShapeLayer()
        mask.path = path.cgPath
        self.layer.mask = mask
    }
    
}

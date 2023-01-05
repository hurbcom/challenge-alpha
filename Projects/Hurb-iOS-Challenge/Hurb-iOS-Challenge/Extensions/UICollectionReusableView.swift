//
//  UICollectionReusableView.swift
//  Hurb-iOS-Challenge
//
//  Created by Rômulo Monteiro on 05/01/23.
//

import UIKit

extension UICollectionReusableView {
    
    /// Swift only
    static var cellIdentifier: String {
        
        return String(describing: self)
    }
    
    static var nib: UINib {
        
        let bundle = Bundle(for: self)
        
        let nib = UINib(
            nibName: self.cellIdentifier,
            bundle: bundle
        )
        
        return nib
    }
}

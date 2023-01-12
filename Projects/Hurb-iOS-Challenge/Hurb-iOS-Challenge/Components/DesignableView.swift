//
//  DesignableView.swift
//  Hurb-iOS-Challenge
//
//  Created by Rômulo Monteiro on 10/01/23.
//

import UIKit

@IBDesignable class DesignableView: UIView {
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        
        self.layer.shouldRasterize = true
        self.layer.rasterizationScale = UIScreen.main.scale
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        
        self.layer.shouldRasterize = true
        self.layer.rasterizationScale = UIScreen.main.scale
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()
        
        var mutableBounds: CGRect = self.bounds
        
        // rounded-rect path for "shadow" border
        mutableBounds.size.height += self.shadowOffset.height
                
        self.layer.shadowPath = UIBezierPath(roundedRect: mutableBounds, cornerRadius: self.cornerRadius).cgPath
    }
    
    // Rounded properties
    // Corner radius
    @IBInspectable var cornerRadius: CGFloat {
        
        get {
            
            return self.layer.cornerRadius
        }
        
        set(value) {
            
            self.layer.cornerRadius = value
            self.layer.masksToBounds = (value > 0.0)
        }
    }
    
    // Shaded properties
    // Shadow radius
    @IBInspectable var shadowRadius: CGFloat {
        
        get {
            
            return self.layer.shadowRadius
        }
        
        set(value) {
            
            self.layer.shadowRadius = value
        }
    }
    
    // Shadow opacity
    @IBInspectable var shadowOpacity: Float {
        
        get {
            
            return self.layer.shadowOpacity
        }
        
        set(value) {
            
            self.layer.shadowOpacity = value
        }
    }
    
    // Shadow offset
    @IBInspectable var shadowOffset: CGSize {
        
        get {
            
            return self.layer.shadowOffset
        }
        
        set(value) {
            
            self.layer.shadowOffset = value
        }
    }
    
    // Shadow color
    @IBInspectable var shadowColor: UIColor? {
        
        get {
            
            guard let color = self.layer.shadowColor else {
                
                return nil
            }
            
            return UIColor(cgColor: color)
        }
        
        set(value) {
            
            self.layer.masksToBounds = false
            self.layer.shadowColor = value?.cgColor
        }
    }
    
    // All borders
    // Width of all borders
    @IBInspectable var widthAllBorders: CGFloat = 0.0 {
        
        didSet {

            self.layer.borderWidth = self.widthAllBorders
        }
    }
    
    // Color of all borders
    @IBInspectable var colorAllBorders: UIColor = UIColor.clear {
        
        didSet {
            
            self.layer.borderColor = self.colorAllBorders.cgColor
        }
    }
}

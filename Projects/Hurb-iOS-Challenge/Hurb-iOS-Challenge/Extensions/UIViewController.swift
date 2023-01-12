//
//  UIViewController.swift
//  Hurb-iOS-Challenge
//
//  Created by Rômulo Monteiro on 11/01/23.
//

import UIKit

extension UIViewController {
    
    class func newInstanceFromStoryboard<T: UIViewController>(
        storyboardName: String,
        storyboardId: String) -> T {
                    
        let storyboard: UIStoryboard = UIStoryboard(name: storyboardName, bundle: nil)
        let controller: T = storyboard.instantiateViewController(withIdentifier: storyboardId) as! T
        
        return controller
    }
}

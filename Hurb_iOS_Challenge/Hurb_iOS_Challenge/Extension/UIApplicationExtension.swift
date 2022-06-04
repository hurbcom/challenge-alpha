//
//  UIApplicationExtension.swift
//  Hurb_iOS_Challenge
//
//  Created by Gáudio Ney on 02/06/22.
//

import UIKit

// MARK: - UIApplication

fileprivate let window = UIApplication.shared.connectedScenes.compactMap { $0 as? UIWindowScene }.flatMap { $0.windows }.first { $0.isKeyWindow }

extension UIApplication {
    class func topViewController(controller: UIViewController? = window?.rootViewController) -> UIViewController? {
        
        if let navigationController = controller as? UINavigationController {
            return topViewController(controller: navigationController.visibleViewController)
        }
        
        if let tabController = controller as? UITabBarController {
            if let selected = tabController.selectedViewController {
                return topViewController(controller: selected)
            }
        }
        
        if let presented = controller?.presentedViewController {
            return topViewController(controller: presented)
        }
        
        return controller
    }
}

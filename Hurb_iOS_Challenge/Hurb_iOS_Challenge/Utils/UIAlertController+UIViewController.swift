//
//  AlertUtils+UIAlertController.swift
//  Hurb_iOS_Challenge
//
//  Created by Gáudio Ney on 24/05/22.
//

import UIKit

/// Class that set all the App Alerts.
class AlertUtils {
    class func showAlert(message: String, description: String? = nil, attributedDescription: NSMutableAttributedString? = nil, viewController: UIViewController? = nil, completionHandler: (() -> ())? = nil) {
        let alert = UIAlertController(title: message, message: description, preferredStyle: .alert)
        
        if attributedDescription != nil {
            alert.setValue(attributedDescription, forKey: "attributedMessage")
        }
        
        alert.addAction(UIAlertAction(title: "OK", style: .default, handler: { (_) in
            if completionHandler == nil {
                return
            }
            completionHandler!()
        }))
        
        if viewController != nil {
            viewController?.present(alert, animated: true, completion: nil)
        }
        else {
            UIApplication.topViewController()!.present(alert, animated: true, completion: nil)
        }
    }
}

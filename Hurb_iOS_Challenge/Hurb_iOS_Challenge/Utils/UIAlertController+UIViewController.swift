//
//  AlertUtils+UIAlertController.swift
//  Hurb_iOS_Challenge
//
//  Created by Gáudio Ney on 24/05/22.
//

import UIKit

extension UIViewController {
    func showAlert(message: String, title: String? = nil, completionHandler: (() -> ())? = nil) {
        let alert = UIAlertController(title: message, message: description, preferredStyle: .alert)
        
        alert.addAction(UIAlertAction(title: "OK", style: .default, handler: { (_) in
            if completionHandler == nil {
                return
            }
            completionHandler!()
        }))
        self.present(alert, animated: true, completion: nil)
    }
}

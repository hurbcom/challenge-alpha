//
//  Extensions+UIViewController.swift
//  Hurb
//
//  Created by Alexandre Papanis on 10/08/19.
//  Copyright © 2019 Papanis. All rights reserved.
//

import UIKit

extension UIViewController {
    func showAlert(mensagem: String?) -> UIAlertController {
        let alertController = UIAlertController(title: "Aviso", message: mensagem ?? "Ocorreu um erro inesperado. Por favor, tente novamente mais tarde.", preferredStyle: .alert)
        
        let okButton = UIAlertAction(title: "OK", style: .cancel, handler: nil)
        alertController.addAction(okButton)
        
        return alertController
    }
    
    @objc func dismissKeyboard() {
        view.endEditing(true)
    }
}

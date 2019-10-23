//
//  UIViewController+Extensions.swift
//  desafio-hurb-ios
//
//  Created by Vinícius Barcelos on 22/10/19.
//  Copyright © 2019 Vinícius Barcelos. All rights reserved.
//

import UIKit

extension UIViewController {
    
    @objc class var identifier: String {
        return String(describing: self)
    }
    
    func customizeNavigationController() {
        navigationItem.backBarButtonItem = UIBarButtonItem(title: "", style: .plain, target: nil, action: nil)
        navigationController?.navigationBar.barStyle = .black
        self.navigationController?.navigationBar.shadowImage = UIImage()
    }
}


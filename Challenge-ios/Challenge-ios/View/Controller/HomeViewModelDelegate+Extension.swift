//
//  HomeViewModelDelegate+Extension.swift
//  Challenge-ios
//
//  Created by Andre Dias on 01/10/20.
//  Copyright © 2020 Andre Dias. All rights reserved.
//

import Foundation
import UIKit

extension HomeViewController: HotelsViewModelDelegate {
    
    func didFinishFetchingHotels() {
        self.tableView.reloadData()
        
        UIView.animate(withDuration: 0.75) {
            self.backgroundViewWithLogo.isHidden = true
            self.backgroundViewWithLogo.backgroundColor = .white
            self.tableView.isHidden = false
        }
    }
    
    func errorMessage(error: String) {
        let alert = UIAlertController(title: "Erro", message: error, preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: "OK", style: .default, handler: { action in
            self.dismiss(animated: false, completion: nil)
            UIControl().sendAction(#selector(URLSessionTask.suspend), to: UIApplication.shared, for: nil)
        }))

        self.present(alert, animated: true)
    }
}

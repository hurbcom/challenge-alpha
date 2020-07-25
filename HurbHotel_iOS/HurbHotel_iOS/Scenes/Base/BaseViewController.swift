//
//  BaseViewController.swift
//  HurbHotel_iOS
//
//  Created by Helio Junior on 21/07/20.
//  Copyright © 2020 HelioTecnologia. All rights reserved.
//

import UIKit

class BaseViewController: UIViewController {
    
    var activityIndicatorView: UIActivityIndicatorView = {
        let ai = UIActivityIndicatorView()
        ai.color = .blue
        ai.startAnimating()
        ai.hidesWhenStopped = true
        return ai
    }()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        view.addSubview(activityIndicatorView)
        configureActivityIndicatorView()
    }
    
    //Helpers
    func configureActivityIndicatorView() {
        activityIndicatorView.translatesAutoresizingMaskIntoConstraints = false
        activityIndicatorView.centerXAnchor.constraint(equalTo: view.centerXAnchor).isActive = true
        activityIndicatorView.centerYAnchor.constraint(equalTo: view.centerYAnchor).isActive = true
        closeLoading()
    }
    
    func showLoading() {
        activityIndicatorView.startAnimating()
    }
    
    func closeLoading() {
        DispatchQueue.main.async {
            self.activityIndicatorView.stopAnimating()
        }
    }
}

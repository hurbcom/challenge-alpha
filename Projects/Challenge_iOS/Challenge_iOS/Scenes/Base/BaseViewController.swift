//
//  BaseViewController.swift
//  Challenge_iOS
//
//  Created by Helio Junior on 12/11/22.
//

import UIKit

class BaseViewController: UIViewController {
    
    private let loadingView: LoadingView = LoadingView.fromNib()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        setupLoadingView()
    }
}

// MARK: LoadingView
extension BaseViewController {
    private func setupLoadingView() {
        loadingView.isHidden = true
        loadingView.frame = view.bounds
        view.addSubview(loadingView)
        loadingView.bringSubviewToFront(view)
    }
    
    func showLoading() {
        loadingView.isHidden = false
    }
    
    func closeLoading() {
        DispatchQueue.main.async {
            self.loadingView.isHidden = true
        }
    }
}

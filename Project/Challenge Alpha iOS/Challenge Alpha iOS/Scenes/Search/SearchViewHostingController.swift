//
//  ViewController.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 15/01/23.
//

import UIKit
import SwiftUI

class SearchViewHostingController: UIHostingController<SearchView> {
    
    // MARK: - Lifecycle
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.navigationItem.title = "Explorar"
        self.setupNavigationBarAppearance()
    }
    
    // MARK: - Navigation bar setup
    private func setupNavigationBarAppearance() {
        self.navigationController?.navigationBar.prefersLargeTitles = true
        
        let appearance = UINavigationBarAppearance()
        appearance.backgroundColor = .white
        appearance.backgroundEffect = .none
        appearance.shadowColor = .clear
        
        self.navigationController?.navigationBar.standardAppearance = appearance
        self.navigationController?.navigationBar.compactAppearance = appearance
        self.navigationController?.navigationBar.scrollEdgeAppearance = appearance
    }
}


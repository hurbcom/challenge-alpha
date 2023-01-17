//
//  PackageListHostingController.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 16/01/23.
//

import Foundation
import UIKit
import SwiftUI

final class PackageListHostingController: UIHostingController<PackageListView> {
    
    let viewModel: PackageListViewModel
    
    init(viewModel: PackageListViewModel, rootView: PackageListView) {
        self.viewModel = viewModel
        super.init(rootView: rootView)
    }
    
    @MainActor required dynamic init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    // MARK: Lifecycle
    override func viewDidLoad() {
        super.viewDidLoad()
        self.setupNavigationBar()
    }
    
    // MARK: Navigation Bar setup
    private func setupNavigationBar() {
        let rightBarItem = UIBarButtonItem(
            image: UIImage(systemName: "magnifyingglass"),
            style: .plain, target: self.viewModel,
            action: #selector(viewModel.onSearchTap)
        )
        
        self.navigationItem.rightBarButtonItem = rightBarItem
        self.navigationItem.title = "Pacotes"
        
        self.navigationController?.navigationBar.tintColor = UIColor(named: UIConstants.COLOR_NAME.hurbBlue)
        self.navigationController?.navigationBar.prefersLargeTitles = true
    }
}

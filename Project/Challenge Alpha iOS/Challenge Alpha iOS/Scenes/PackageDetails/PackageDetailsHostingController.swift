//
//  PackageDetailsHostingController.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 17/01/23.
//

import UIKit
import SwiftUI

final class PackageDetailsHostingController: UIHostingController<PackageDetailsView> {
    
    let viewModel: PackageDetailsViewModel
    
    init(viewModel: PackageDetailsViewModel, rootView: PackageDetailsView) {
        self.viewModel = viewModel
        super.init(rootView: rootView)
    }
    
    @MainActor required dynamic init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    // MARK: - Lifecycle
    override func viewDidLoad() {
        super.viewDidLoad()
        self.setupNavigationBarItems()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        self.setupNavigationBarAppearance()
    }
    
    // MARK: Setup functions
    private func setupNavigationBarItems() {
        self.navigationItem.largeTitleDisplayMode = .never
        
        let rightBarItem = UIBarButtonItem(
            image: UIImage(systemName: "square.and.arrow.up"),
            style: .plain, target: self.viewModel,
            action: #selector(viewModel.onShareButtonTap)
        )
        
        self.navigationItem.rightBarButtonItem = rightBarItem
    }
    
    private func setupNavigationBarAppearance() {
        self.navigationController?.navigationBar.tintColor = .white
        self.navigationController?.navigationBar.topItem?.backButtonDisplayMode = .minimal

        // Transparent nav bar
        let appearance = UINavigationBarAppearance()
        appearance.backgroundColor = .white.withAlphaComponent(0)
        appearance.backgroundEffect = .none
        appearance.shadowColor = .clear
        
        self.navigationController?.navigationBar.standardAppearance = appearance
        self.navigationController?.navigationBar.compactAppearance = appearance
        self.navigationController?.navigationBar.scrollEdgeAppearance = appearance
    }
}

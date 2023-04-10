//
//  HomeViewCoordinator.swift
//  iTravel_iOS_Challenge
//
//  Created by convidado on 08/04/23.
//

import Foundation
import UIKit

class MovieListCoordinator: BaseCoordinator {
    private var viewModel: HomeViewModel = HomeViewModel()
    
    override func start() {
        setupBinding()
        let homeViewController = HomeViewController()
        homeViewController.viewmodel = viewModel
        self.navigationController = UINavigationController(rootViewController: homeViewController)
        self.navigationController.viewControllers = [homeViewController]
    }
    
    private func setupBinding() {
        viewModel.selectedItem.subscribe(to: self) { this, package in
            this.navigateToPackageDetail(package: package)
        }
    }
    


    func navigateToPackageDetail(package: PackageResult) {
        let packageCoordinator = PackageDetailViewCoordinator(package: package)
        packageCoordinator.navigationController = navigationController
        start(coordinator: packageCoordinator)
    }

}

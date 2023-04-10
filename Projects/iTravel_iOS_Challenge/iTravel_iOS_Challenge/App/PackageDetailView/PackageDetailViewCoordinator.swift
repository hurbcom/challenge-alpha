//
//  PackageDetailViewCoordinator.swift
//  iTravel_iOS_Challenge
//
//  Created by convidado on 08/04/23.
//

import Foundation
import UIKit

class PackageDetailViewCoordinator: BaseCoordinator {
    private var viewModel: PackageDetailViewModel = PackageDetailViewModel()
    private var package:PackageResult?

    
    init(package: PackageResult) {
        self.package = package
    }

    override func start() {
        
        let packageViewController = PackageDetailViewController()
        packageViewController.viewmodel = viewModel
        packageViewController.package = package
        self.navigationController.pushViewController(packageViewController, animated: true)
    }
    
   
    

}

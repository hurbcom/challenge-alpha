//
//  HomeCoordinator.swift
//  Challenge-ios
//
//  Created by Andre Dias on 02/10/20.
//  Copyright © 2020 Andre Dias. All rights reserved.
//

import UIKit

final class HomeCoordinator: Coordinator {
    
    private(set) var childCoordinators: [Coordinator] = []
    private let naviagtionController: UINavigationController
    
    init(naviagtionController: UINavigationController) {
        self.naviagtionController = naviagtionController
    }
    
    func start() {
        let homeViewController = HomeViewController.instantiate()
        self.naviagtionController.setViewControllers([homeViewController], animated: false)
    }

}


//
//  AppCoordinator.swift
//  Challenge-ios
//
//  Created by Andre Dias on 02/10/20.
//  Copyright © 2020 Andre Dias. All rights reserved.
//

import UIKit

protocol Coordinator {
    var childCoordinators: [Coordinator] { get }
    func start()
}

final class AppCoordinator: Coordinator {
    
    private let window: UIWindow
    private(set) var childCoordinators: [Coordinator] = []
    
    init(window: UIWindow) {
        self.window = window
    }
    
    func start() {
        let navigationController = UINavigationController()
        let homeCoordinator = HomeCoordinator(naviagtionController: navigationController)
        childCoordinators.append(homeCoordinator)
        homeCoordinator.start()
        window.rootViewController = navigationController
        window.makeKeyAndVisible()
    }
    
    
}

//
//  AppCoordinator.swift
//  Alpha
//
//  Created by Theo Mendes on 14/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//
import UIKit

class AppCoordinator: BaseCoordinator {
    let window: UIWindow

    init(window: UIWindow) {
        self.window = window
        super.init()
    }

    override func start() {
        // preparing root view
        let navigationController = UINavigationController()
        let mainCoordinator = FeedCoordinator(navigationController: navigationController)

        // store child coordinator
        self.store(coordinator: mainCoordinator)
        mainCoordinator.start()

        window.rootViewController = navigationController
        window.makeKeyAndVisible()

        // detect when free it
        mainCoordinator.isCompleted = { [weak self] in
            self?.free(coordinator: mainCoordinator)
        }
    }
}

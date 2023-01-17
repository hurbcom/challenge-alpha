//
//  AppCoordinator.swift
//  HurbApp
//
//  Created by Fabio Takahashi on 17/01/23.
//

import UIKit

final class AppCoordinator {

    private let window: UIWindow

    init(window: UIWindow) {
        self.window = window
    }

    func start() {

        let viewModel = HUViewModel()

        let rootViewController = HUViewController(viewModel: viewModel)

        window.rootViewController = rootViewController
        window.makeKeyAndVisible()
    }

}

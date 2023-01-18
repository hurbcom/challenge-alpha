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

        let viewModel = SearchViewModel()

        let serchViewController = SearchViewController(viewModel: viewModel)

        let rootViewController = UINavigationController(rootViewController: serchViewController)

        let standardAppearance = UINavigationBarAppearance()
        standardAppearance.backgroundColor = UIColor(named: "Primary")
        rootViewController.navigationBar.standardAppearance = standardAppearance

        let compactAppearance = UINavigationBarAppearance()
        compactAppearance.backgroundColor = UIColor(named: "Primary")
        rootViewController.navigationBar.compactAppearance = compactAppearance

        let scrollEdgeAppearance = UINavigationBarAppearance()
        scrollEdgeAppearance.backgroundColor = UIColor(named: "Primary")
        rootViewController.navigationBar.scrollEdgeAppearance = scrollEdgeAppearance

        window.rootViewController = rootViewController
        window.makeKeyAndVisible()
    }

}

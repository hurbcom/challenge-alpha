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
//        standardAppearance.configureWithOpaqueBackground()
        standardAppearance.backgroundColor = .red
        rootViewController.navigationBar.standardAppearance = standardAppearance

        let compactAppearance = UINavigationBarAppearance()
//        compactAppearance.configureWithOpaqueBackground()
        compactAppearance.backgroundColor = .blue
        rootViewController.navigationBar.compactAppearance = compactAppearance

        let scrollEdgeAppearance = UINavigationBarAppearance()
//        scrollEdgeAppearance.configureWithOpaqueBackground()
        scrollEdgeAppearance.backgroundColor = .yellow
        rootViewController.navigationBar.scrollEdgeAppearance = scrollEdgeAppearance

        window.rootViewController = rootViewController
        window.makeKeyAndVisible()
    }

}

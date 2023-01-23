//
//  AppCoordinator.swift
//  HurbApp
//
//  Created by Fabio Takahashi on 17/01/23.
//

import UIKit

final class AppCoordinator {

    private let window: UIWindow
    private var rootViewController: UINavigationController?

    init(window: UIWindow) {
        self.window = window
    }

    func start() {

        let viewModel = SearchViewModel()

        let serchViewController = SearchViewController(viewModel: viewModel)
        serchViewController.delegate = self

        viewModel.delegate = serchViewController

        let rootViewController = HUNavigationController(rootViewController: serchViewController)
        self.rootViewController = rootViewController

        window.rootViewController = rootViewController
        window.makeKeyAndVisible()
    }

}

extension AppCoordinator: SearchViewControllerDelegate {

    func presentAlertMessage(viewController: SearchViewController, alertMessage: AlertMessage) {

        let alertViewController = UIAlertController(
            title: alertMessage.title,
            message: alertMessage.message,
            preferredStyle: .alert
        )

        viewController.present(alertViewController, animated: true)
    }

    func presentDetailViewController(viewController: SearchViewController, seletedItem: SearchResult) {

        let viewModel = SearchDetailViewModel(item: seletedItem)

        let destinationViewController = SearchDetailViewController(viewModel: viewModel)
        destinationViewController.delegate = self

        viewModel.delegate = destinationViewController

        rootViewController?.pushViewController(destinationViewController, animated: true)
    }

}

extension AppCoordinator: SearchDetailViewControllerDelegate {

    func searchDetailViewController(viewController: SearchDetailViewController, didShareActivityItems activityItems: [Any]) {

        let activityViewController = UIActivityViewController(
            activityItems: activityItems,
            applicationActivities: nil
        )

        viewController.present(activityViewController, animated: true)
    }

}

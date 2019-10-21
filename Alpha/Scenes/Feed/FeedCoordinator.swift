//
//  MainCoordinator.swift
//  Alpha
//
//  Created by Theo Mendes on 14/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

import UIKit

class FeedCoordinator: BaseCoordinator {
    var navigationController: UINavigationController?
    let provider: AlphaAPI

    init(navigationController: UINavigationController?, provider: AlphaAPI) {
        self.navigationController = navigationController
        self.provider = provider
    }

    override func start() {
        let viewModel = FeedViewModel(provider: provider)
        let viewController = FeedViewController(viewModel: viewModel)

        navigationController?.pushViewController(viewController, animated: true)
    }
}

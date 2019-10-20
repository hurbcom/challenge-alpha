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

    init(navigationController: UINavigationController?) {
        self.navigationController = navigationController
    }

    override func start() {
        let viewModel = FeedViewModel()
        let viewController = FeedViewController(viewModel: viewModel)

        navigationController?.pushViewController(viewController, animated: true)
    }
}

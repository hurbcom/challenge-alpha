//
//  MainCoordinator.swift
//  Alpha
//
//  Created by Theo Mendes on 14/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

import UIKit

class MainCoordinator: BaseCoordinator {
    var navigationController: UINavigationController?

    init(navigationController: UINavigationController?) {
        self.navigationController = navigationController
    }

    override func start() {
        let viewModel = MainViewModel()
        let viewController = MainViewController(viewModel: viewModel)

        navigationController?.pushViewController(viewController, animated: true)
    }
}

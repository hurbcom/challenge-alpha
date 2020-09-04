//
//  HomeBuilder.swift
//  DesafioHurb
//
//  Created by Edson Aparecido Guido on 04/09/20.
//  Copyright (c) 2020 Edson Aparecido Guido. All rights reserved.
//

import UIKit

protocol HomeBuildable: AnyObject {
    func build() -> UIViewController
}

final class HomeBuilder: Builder, HomeBuildable {
    func build() -> UIViewController {
        let interactor = HomeInteractor()
        let viewModel = HomeViewModel(interactor: interactor)
        let router = HomeRouter()
        let viewController = HomeViewController(withViewModel: viewModel, router: router)
        router.viewController = viewController

        return viewController
    }
}

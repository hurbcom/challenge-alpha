//
//  DetailBuilder.swift
//  DesafioHurb
//
//  Created by Edson Aparecido Guido on 07/09/20.
//  Copyright (c) 2020 Edson Aparecido Guido. All rights reserved.
//

import UIKit

protocol DetailBuildable: AnyObject {
    func build(hotel: Hotel) -> UIViewController
}

final class DetailBuilder: Builder, DetailBuildable {
    func build(hotel: Hotel) -> UIViewController {
        let interactor = DetailInteractor()
        let viewModel = DetailViewModel(interactor: interactor, hotel: hotel)
        let router = DetailRouter()
        let viewController = DetailViewController(withViewModel: viewModel, router: router)
        router.viewController = viewController

        return viewController
    }
}

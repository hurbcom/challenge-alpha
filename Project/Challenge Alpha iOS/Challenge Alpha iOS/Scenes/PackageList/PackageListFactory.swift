//
//  PackageListFactory.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 16/01/23.
//

import Foundation

enum PackageListFactory {
    static func build() -> PackageListHostingController {
        let interactor = PackageListInteractor()
        let router = PackageListRouter()
        let viewModel = PackageListViewModel(interactor: interactor, router: router)
        let view = PackageListView(viewModel: viewModel)
        let viewController = PackageListHostingController(viewModel: viewModel, rootView: view)
        
        router.viewController = viewController
        
        return viewController
    }
}

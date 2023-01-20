//
//  PackageListFactory.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 16/01/23.
//

import Foundation

enum PackageListFactory {
    static func build(with searchTerm: String = Constants.DEFAULT_DESTINATION) -> PackageListHostingController {
        let interactor = PackageListInteractor()
        let router = PackageListRouter()
        let viewModel = PackageListViewModel(query: searchTerm, interactor: interactor, router: router)
        let view = PackageListView(viewModel: viewModel)
        let viewController = PackageListHostingController(viewModel: viewModel, rootView: view)
        
        router.viewController = viewController
        
        return viewController
    }
}

//
//  PackageDetailsFactory.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 17/01/23.
//

import Foundation

enum PackageDetailsFactory {
    static func build(package: PackageResult) -> PackageDetailsHostingController {
        let router = PackageDetailsRouter()
        let viewModel = PackageDetailsViewModel(package: package, router: router)
        let rootView = PackageDetailsView(viewModel: viewModel)
        let viewController = PackageDetailsHostingController(viewModel: viewModel, rootView: rootView)
        
        router.viewController = viewController
        
        return viewController
    }
}

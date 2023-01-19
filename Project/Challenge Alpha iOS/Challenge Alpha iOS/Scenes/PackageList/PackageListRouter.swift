//
//  PackageListRouter.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 16/01/23.
//

import UIKit

protocol PackageListRouterProtocol {
    func navigateToPackageDetails(_ package: PackageResult)
    func presentSearch()
}

final class PackageListRouter: PackageListRouterProtocol {
    weak var viewController: UIViewController?
    
    func navigateToPackageDetails(_ package: PackageResult) {
        let packageDetailsVC = PackageDetailsFactory.build(package: package)
        self.viewController?.navigationController?.pushViewController(packageDetailsVC, animated: true)
    }
    
    func presentSearch() {
        let suggestionVC = SuggestionFactory.build(suggestionType: .package)
        self.viewController?.present(suggestionVC, animated: true)
    }
}

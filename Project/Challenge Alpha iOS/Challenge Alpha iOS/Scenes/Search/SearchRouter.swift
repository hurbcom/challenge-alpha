//
//  SearchRouter.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 16/01/23.
//

import UIKit

protocol SearchRouterProtocol {
    func navigateToHotelDetails(_ hotel: HotelResult)
    func navigateToPackageDetails(_ package: PackageResult)
    func presentSuggestions(suggestionType: SuggestionType, onComplete: @escaping ((String) -> Void))
}

final class SearchRouter: SearchRouterProtocol {
    weak var viewController: UIViewController?
    
    func navigateToHotelDetails(_ hotel: HotelResult) {
//        let hotelDetailsVC = HotelDetailsFactory.build(package: package)
//        viewController?.navigationController?.pushViewController(packageDetailsVC, animated: true)
    }
    
    func navigateToPackageDetails(_ package: PackageResult) {
        let packageDetailsVC = PackageDetailsFactory.build(package: package)
        viewController?.navigationController?.pushViewController(packageDetailsVC, animated: true)
    }
    
    func presentSuggestions(suggestionType: SuggestionType, onComplete: @escaping ((String) -> Void)) {
        let suggestionVC = SuggestionFactory.build(suggestionType: suggestionType, onSearchComplete: onComplete)
        viewController?.present(suggestionVC, animated: true)
    }
    
}

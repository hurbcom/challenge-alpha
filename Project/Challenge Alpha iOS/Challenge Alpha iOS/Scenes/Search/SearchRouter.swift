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
    func navigateToHotelList(with searchTerm: String)
    func navigateToPackageList(with searchTerm: String)
    func presentSuggestions(suggestionType: SuggestionType, onComplete: @escaping ((String) -> Void))
}

final class SearchRouter: SearchRouterProtocol {
    weak var viewController: UIViewController?
    
    func navigateToHotelDetails(_ hotel: HotelResult) {
        let hotelDetailsVC = HotelDetailsFactory.build(hotel: hotel)
        viewController?.navigationController?.pushViewController(hotelDetailsVC, animated: true)
    }
    
    func navigateToPackageDetails(_ package: PackageResult) {
        let packageDetailsVC = PackageDetailsFactory.build(package: package)
        viewController?.navigationController?.pushViewController(packageDetailsVC, animated: true)
    }
    
    func navigateToHotelList(with searchTerm: String) {
        let hotelListVC = HotelListFactory.build(with: searchTerm)
        viewController?.navigationController?.pushViewController(hotelListVC, animated: true)
    }
    
    func navigateToPackageList(with searchTerm: String) {
        let packageListVC = PackageListFactory.build(with: searchTerm)
        viewController?.navigationController?.pushViewController(packageListVC, animated: true)
    }
    
    func presentSuggestions(suggestionType: SuggestionType, onComplete: @escaping ((String) -> Void)) {
        let suggestionVC = SuggestionFactory.build(suggestionType: suggestionType, onSearchComplete: onComplete)
        viewController?.present(suggestionVC, animated: true)
    }
    
}

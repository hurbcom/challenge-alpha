//
//  HotelListRouter.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 19/01/23.
//

import UIKit

protocol HotelListRouterProtocol {
    func navigateToHotelDetails(_ hotel: HotelResult)
}

final class HotelListRouter: HotelListRouterProtocol {
    weak var viewController: UIViewController?
    
    func navigateToHotelDetails(_ hotel: HotelResult) {
        let hotelDetailsVC = HotelDetailsFactory.build(hotel: hotel)
        viewController?.navigationController?.pushViewController(hotelDetailsVC, animated: true)
    }
}

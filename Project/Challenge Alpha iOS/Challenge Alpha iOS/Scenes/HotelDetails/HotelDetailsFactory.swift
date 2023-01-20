//
//  HotelDetailsFactory.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 19/01/23.
//

import Foundation

enum HotelDetailsFactory {
    static func build(hotel: HotelResult) -> HotelDetailsHostingController {
        let router = HotelDetailsRouter()
        let viewModel = HotelDetailsViewModel(hotel: hotel, router: router)
        let rootView = HotelDetailsView(viewModel: viewModel)
        let viewController = HotelDetailsHostingController(viewModel: viewModel, rootView: rootView)
        
        router.viewController = viewController
        
        return viewController
    }
}

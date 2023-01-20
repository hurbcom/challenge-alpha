//
//  HotelDetailsViewModel.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 19/01/23.
//

import Foundation
import Combine

final class HotelDetailsViewModel: ObservableObject {
    
    // MARK: - Dependencies
    var hotel: HotelResult
    var router: HotelDetailsRouterProtocol
    
    init(hotel: HotelResult, router: HotelDetailsRouterProtocol = HotelDetailsRouter()) {
        self.hotel = hotel
        self.router = router
    }
    
    // MARK: - Actions
    @objc func onShareButtonTap() {
        router.presentShareSheet(urlString: self.hotel.url ?? "")
    }
    
    @available(iOS 15, *)
    func onReadMoreTap() {
        router.presentDescription(self.hotel.getDescription())
    }
    
    @available(iOS 15, *)
    func onDescriptionButtonTap() {
        router.presentDescription(self.hotel.description ?? "")
    }
}

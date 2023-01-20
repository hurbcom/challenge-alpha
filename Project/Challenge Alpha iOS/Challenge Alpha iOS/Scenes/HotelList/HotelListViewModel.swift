//
//  HotelListViewModel.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 19/01/23.
//

import Foundation
import Combine

final class HotelListViewModel: ObservableObject {
    
    // MARK: - Published properties
    @Published var hotels: [HotelResult] = []
    @Published var query: String
    @Published var showLoading: Bool = true
    @Published var showError: Bool = false
    
    // MARK: - Dispose bag
    var cancellables = Set<AnyCancellable>()
    
    // MARK: - Dependencies
    var interactor: HotelListInteractorInput
    var router: HotelListRouterProtocol
    
    init(query: String = Constants.DEFAULT_DESTINATION, interactor: HotelListInteractorInput, router: HotelListRouterProtocol = HotelListRouter()) {
        self.query = query
        self.interactor = interactor
        self.router = router
    }
    
    func getHotelList() {
        interactor.getHotels(
            query: query,
            pagination: .init(
                page: 1,
                limit: 20,
                sort: .price,
                sortOrder: .desc)
        )
        .receive(on: DispatchQueue.main)
        .sink { completion in
            if case .failure(_) = completion {
                self.showError = true
            }
            
            self.hideLoading()
        } receiveValue: { hotels in
            if hotels.isEmpty {
                self.showError = true
            }
            
            self.hotels = hotels
        }
        .store(in: &cancellables)
    }
    
    // MARK: - Actions
    func onHotelTap(_ hotel: HotelResult) {
        //
    }
    
    @objc func onSearchTap() {
//        router.presentSearch(onComplete: self.redraw(with:))
    }
    
    // MARK: - Helpers
    func hideLoading() {
        self.showLoading = false
    }
}

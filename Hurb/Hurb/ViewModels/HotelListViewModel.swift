//
//  ProductListViewModel.swift
//  Hurb
//
//  Created by Arthur Givigir on 12/27/20.
//

import Foundation
import Combine

class HotelListViewModel: ObservableObject, Identifiable {
    
    @Published var hotelPagination: HotelsPaginationModel?
    @Published var groupedHotels: [GroupedHotels]?
    @Published var listPlaces: [Suggestion]?
    @Published var searchByCity: String = ""
    @Published var selectedPlace: String = ""
    
    private var hotelListInteractor: HotelListInteractorProtocol
    private var disposables: Set<AnyCancellable> = []
    
    init() {
        self.hotelListInteractor = HotelListInteractor()
        self.getHotelList()
    }
    
    func getHotelList() {
        
        hotelListInteractor
            .getHotelListBy(local: self.selectedPlace, page: 1)
            .receive(on: RunLoop.main)
            .sink { result in
                switch result {
                    case .failure(let error):
                        print("Error: \(error.localizedDescription)")
                        
                    case .finished:
                        print("Finished")
                }
                
            } receiveValue: { [weak self] in
                self?.groupedHotels = $0
            }
            .store(in: &disposables)
    }
    
    func getPlaceList() {
        
        if self.searchByCity.count < 2 {
            return
        }
        
        hotelListInteractor
            .getPlaceListBy(typing: self.searchByCity)
            .receive(on: RunLoop.main)
            .sink { result in
                switch result {
                    case .failure(let error):
                        print("Error: \(error.localizedDescription)")
                        
                    case .finished:
                        print("Finished")
                }
            } receiveValue: { [weak self] in
                self?.listPlaces = $0
            }
            .store(in: &disposables)
    }
    
    func selectPlaceAndSearch(suggestion: Suggestion) {
        self.selectedPlace = suggestion.text ?? ""
        
        self.listPlaces = []
        self.getHotelList()
    }
}

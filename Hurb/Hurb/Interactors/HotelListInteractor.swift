//
//  ProductListInteractor.swift
//  Hurb
//
//  Created by Arthur Givigir on 12/27/20.
//
import Foundation
import Combine

protocol HotelListInteractorProtocol {
    
    func getHotelListBy(local: String, page: Int) -> AnyPublisher<[GroupedHotels]?, Error>
}

class HotelListInteractor: HotelListInteractorProtocol {
    
    private var productService = HotelService()
    
    func getHotelListBy(local: String, page: Int) -> AnyPublisher<[GroupedHotels]?, Error> {
        return self.productService
            .getHotelListBy(local: local, page: page)
            .map { self.orderByStarsOrCategories(hotels: $0?.results) }
            .eraseToAnyPublisher()
    }
    
    func orderByStarsOrCategories(hotels: [Hotel]?) -> [GroupedHotels]? {
        
        guard let hotels = hotels else {
            return nil
        }
        
        let dictHotels = Dictionary(grouping: hotels, by: { $0.stars }).sorted(by: { $0.key ?? 0 > $1.key ?? 0 })
        
        var groupedHotels: [GroupedHotels] = []
        _ = dictHotels.map { dictionary in
            groupedHotels.append(GroupedHotels(section: dictionary.key, hotels: dictionary.value))
        }
        
        return groupedHotels
        
        
    }
    
}

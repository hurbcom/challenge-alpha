//
//  ProductListInteractor.swift
//  Hurb
//
//  Created by Arthur Givigir on 12/27/20.
//

import Combine

protocol HotelListInteractorProtocol {
    func getHotelListBy(local: String, page: Int) -> AnyPublisher<HotelsPaginationModel?, Error>
}

class HotelListInteractor: HotelListInteractorProtocol {
    
    private var productService = HotelService()
    
    func getHotelListBy(local: String, page: Int) -> AnyPublisher<HotelsPaginationModel?, Error> {
        return self.productService.getHotelListBy(local: local, page: page)
    }
    
}

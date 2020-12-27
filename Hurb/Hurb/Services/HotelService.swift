//
//  ProductService.swift
//  Hurb
//
//  Created by Arthur Givigir on 12/27/20.
//

import Combine
import Alamofire
import Foundation

protocol HotelServiceProtocol {
    func getHotelListBy(local: String, page: Int) -> AnyPublisher<HotelsPaginationModel?, Error>
}

class HotelService: HotelServiceProtocol {
    
    private let hotelListPaginationResult = PassthroughSubject<HotelsPaginationModel?, Error>()
    
    func getHotelListBy(local: String, page: Int) -> AnyPublisher<HotelsPaginationModel?, Error> {
        
        let url = "https://www.hurb.com/search/api?q=\(local)&page=\(page)"
        
        AF.request(url, method: .get).response { [weak self] response in
            switch response.result {
                case .success(let result):
                    
                    if let result = result {
                        let productPagination = try? JSONDecoder().decode(HotelsPaginationModel.self, from: result)
                        print("success: \(String(describing: productPagination))")
                        
                        self?.hotelListPaginationResult.send(productPagination)
                    }
                    
                    break
                case .failure(let error):
                    print("failure: \(error)")
                    self?.hotelListPaginationResult.send(completion: .failure(error))
                    break
            }
        }
        
        return hotelListPaginationResult.eraseToAnyPublisher()
    }
    
}

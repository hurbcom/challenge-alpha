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
    func getPlaceListBy(typing: String) -> AnyPublisher<PlaceModel?, Error>
}

class HotelService: HotelServiceProtocol {
    
    private let hotelListPaginationResult = PassthroughSubject<HotelsPaginationModel?, Error>()
    private let placeListResult = PassthroughSubject<PlaceModel?, Error>()
    
    func getHotelListBy(local: String, page: Int) -> AnyPublisher<HotelsPaginationModel?, Error> {
        
        let url = "https://www.hurb.com/search/api?q=\(local)&page=\(page)"
        
        AF.request(url.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed)!, method: .get).response { [weak self] response in
            switch response.result {
                case .success(let result):
                    
                    if let result = result {
                        do {
                            let productPagination = try JSONDecoder().decode(HotelsPaginationModel.self, from: result)
                            print("success: \(String(describing: productPagination))")
                            
                            self?.hotelListPaginationResult.send(productPagination)
                        } catch let error {
                            print("failure: \(error)")
                            self?.hotelListPaginationResult.send(completion: .failure(error))
                            break
                        }
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
    
    func getPlaceListBy(typing: String) -> AnyPublisher<PlaceModel?, Error> {
        let url = "https://www.hurb.com/search/api/suggestion?q=\(typing)"
        
        AF.request(url.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed)!, method: .get)
            .response { [weak self] response in
            switch response.result {
                case .success(let result):
                    
                    if let result = result {
                        do {
                            let placeModel = try JSONDecoder().decode(PlaceModel.self, from: result)
                            print("success: \(String(describing: placeModel))")
                            
                            self?.placeListResult.send(placeModel)
                        } catch let error {
                            print("failure: \(error)")
                            self?.placeListResult.send(completion: .failure(error))
                            break
                        }
                    }
                    
                    break
                case .failure(let error):
                    print("failure: \(error)")
                    self?.placeListResult.send(completion: .failure(error))
                    break
            }
        }
        
        return placeListResult.eraseToAnyPublisher()
    }
}

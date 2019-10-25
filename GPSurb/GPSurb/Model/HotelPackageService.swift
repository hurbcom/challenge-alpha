//
//  HotelPackageService.swift
//  GPSurb
//
//  Created by Gilson Santos on 16/10/19.
//  Copyright © 2019 Gilson Santos. All rights reserved.
//

import Alamofire

public enum TypeFilter: String {
    case hotel = "is_hotel%7C1"
    case package = "is_package%2Cis_offer%7C1"
}

open class HotelPackageService: BaseService {
    
    private let path = "/search/api"
    
    open func getHotelAndPackage(query: String, filter: TypeFilter, page: Int, completion: @escaping (Swift.Result<HURBListModel, ErrorType>) -> Void) {
        
        let url = self.getHost() + self.path + "?q=\(query)&filters=\(filter.rawValue)&page=\(page)"
        
        self.sessionManager.request(url, method: .get).responseJSON { (response) in
            switch response.result {
            case .success:
                do {
                    guard let data = response.data else { completion(.failure(.errorData)); return  }
                    let result = try JSONDecoder().decode(HURBListModel.self, from: data)
                    try self.validation(model: result)
                    completion(.success(result))
                } catch {
                    completion(.failure(.errorParsingJSON))
                }
            case .failure:
                completion(.failure(.errorGeneric))
            }
        }
    }
    
    public func validation(model: HURBListModel) throws {
        guard let result = model.results, !result.isEmpty else { throw ErrorType.errorGeneric }
    }
}

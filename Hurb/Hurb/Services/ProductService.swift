//
//  ProductService.swift
//  Hurb
//
//  Created by Arthur Givigir on 12/27/20.
//

import Combine
import Alamofire
import Foundation

class ProductService {
    
    @Published var productPagination: ProductsPaginationModel?
    
    func getProductBy(local: String, page: Int) {
        
        let url = "https://www.hurb.com/search/api?q=\(local)&page=\(page)"
        
        AF.request(url, method: .get).response { response in
            switch response.result {
                case .success(let result):
                    
                    if let result = result {
                        let productsPagination = try? JSONDecoder().decode(ProductsPaginationModel.self, from: result)
                        print("success: \(String(describing: productsPagination))")
                    }
                    
                    break
                case .failure(let error):
                    print("failure: \(error)")
                    break
            }
        }
        
    }
}

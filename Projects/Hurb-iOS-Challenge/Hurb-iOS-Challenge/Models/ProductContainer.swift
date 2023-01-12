//
//  ProductContainer.swift
//  Hurb-iOS-Challenge
//
//  Created by Rômulo Monteiro on 11/01/23.
//

import UIKit

struct ProductContainer: Codable {
    
    private enum CodingKeys: String, CodingKey {
        
        case pagination
        case products = "results"
    }
    
    let pagination: Pagination
    let products: [Product]
}

// Decode methods
extension ProductContainer {
    
    init(from decoder: Decoder) throws {
        
        let container = try decoder.container(keyedBy: CodingKeys.self)

        self.pagination = try container.decode(Pagination.self, forKey: .pagination)
        self.products = try container.decode([Product].self, forKey: .products)
    }
}

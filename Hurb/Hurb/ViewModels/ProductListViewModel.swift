//
//  ProductListViewModel.swift
//  Hurb
//
//  Created by Arthur Givigir on 12/27/20.
//

import Foundation

class ProductListViewModel: ObservableObject, Identifiable {
    
    var productService = ProductService()
    
    init() {
        productService.getProductBy(local: "Buzios", page: 1)
    }
    
}

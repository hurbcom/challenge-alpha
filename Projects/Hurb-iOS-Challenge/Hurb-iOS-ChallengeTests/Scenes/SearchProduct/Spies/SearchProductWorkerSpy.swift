//
//  SearchProductWorkerSpy.swift
//  Hurb-iOS-ChallengeTests
//
//  Created by Rômulo Monteiro on 20/01/23.
//

@testable import Hurb_iOS_Challenge

class SearchProductWorkerSpy: SearchProductWorkerProtocol {

    var invokedSearchProducts = false
    var invokedSearchProductsCount = 0
    var invokedSearchProductsParameters: (term: String, page: Int, limit: Int)?
    var invokedSearchProductsParametersList = [(term: String, page: Int, limit: Int)]()
    var stubbedSearchProductsCompletionResult: (Result<ProductContainer, ServiceError>, Void)?

    func searchProducts(term: String, page: Int, limit: Int, completion: @escaping (Result<ProductContainer, ServiceError>) -> Void) {
        invokedSearchProducts = true
        invokedSearchProductsCount += 1
        invokedSearchProductsParameters = (term, page, limit)
        invokedSearchProductsParametersList.append((term, page, limit))
        if let result = stubbedSearchProductsCompletionResult {
            completion(result.0)
        }
    }
}

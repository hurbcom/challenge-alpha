//
//  PackageServiceFailureStub.swift
//  Challenge_iOSTests
//
//  Created by Helio Junior on 15/11/22.
//

import Foundation
@testable import Challenge_iOS

struct PackageServiceFailureStub: PackageServiceProtocol {
    func getSuggestionsFrom(text: String, completion: @escaping (Result<[SuggestionModel], CustomError>) -> Void) {
        completion(.failure(.unknown))
    }
    
    func findPackageFrom(query: String, pagination: Int, completion: @escaping (Result<[SearchResultModel], CustomError>) -> Void) {
        completion(.failure(.unknown))
    }
}

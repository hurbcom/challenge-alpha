//
//  SearchServiceFailureStub.swift
//  Challenge_iOSTests
//
//  Created by Helio Junior on 14/11/22.
//

import Foundation
@testable import Challenge_iOS

struct SearchServiceFailureStub: SearchServiceProtocol {
    func getSuggestionsFrom(text: String, completion: @escaping (Result<[SuggestionModel], CustomError>) -> Void) {
        completion(.failure(.unknown))
    }
    
    func fetchSearchFrom(query: String, pagination: Int, completion: @escaping (Result<[SearchResultModel], CustomError>) -> Void) {
        completion(.failure(.unknown))
    }
}

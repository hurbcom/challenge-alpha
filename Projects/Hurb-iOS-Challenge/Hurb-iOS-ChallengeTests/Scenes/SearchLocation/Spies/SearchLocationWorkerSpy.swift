//
//  SearchLocationWorkerSpy.swift
//  Hurb-iOS-ChallengeTests
//
//  Created by Rômulo Monteiro on 20/01/23.
//

@testable import Hurb_iOS_Challenge

class SearchLocationWorkerSpy: SearchLocationWorkerProtocol {

    var invokedSearchLocations = false
    var invokedSearchLocationsCount = 0
    var invokedSearchLocationsParameters: (term: String, limit: Int)?
    var invokedSearchLocationsParametersList = [(term: String, limit: Int)]()
    var stubbedSearchLocationsCompletionResult: (Result<[Location]?, ServiceError>, Void)?

    func searchLocations(term: String, limit: Int, completion: @escaping (Result<[Location]?, ServiceError>) -> Void) {
        invokedSearchLocations = true
        invokedSearchLocationsCount += 1
        invokedSearchLocationsParameters = (term, limit)
        invokedSearchLocationsParametersList.append((term, limit))
        if let result = stubbedSearchLocationsCompletionResult {
            completion(result.0)
        }
    }
}

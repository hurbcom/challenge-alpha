//
//  GraphQLNetworkService.swift
//  HurbApp
//
//  Created by Fabio Takahashi on 18/01/23.
//

import HUGraphQL
import Apollo

enum NetworkError: Error {
    case emptyData
    case graphQLErrors([GraphQLError])
}

struct GraphQLNetworkService {

    static func makeRequest<T: GraphQLQuery>(query: T, completion: @escaping (Result<T.Data, Error>) -> Void) {

        let service = HUGService(enableLog: true)

        service.client.fetch(query: query) { result in
            switch result {
            case .failure(let error):
                completion(.failure(error))
            case .success(let graphQLValue):
                if let graphQLErrors = graphQLValue.errors {
                    completion(.failure(NetworkError.graphQLErrors(graphQLErrors)))
                    return
                }
                guard let responseData = graphQLValue.data else {
                    completion(.failure(NetworkError.emptyData))
                    return
                }
                completion(.success(responseData))
            }
        }
    }

}

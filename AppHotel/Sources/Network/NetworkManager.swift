import HUGraphQL

enum NetworkError: Error {
    case invalidURL
    case emptyData
    case decodingError
    case unknownError(String)
}

protocol NetworkManagerType {
    func makeRequest<T: Decodable>(query: GraphQLQuery, parameters: [String: Any], onComplete: @escaping (Result<T, NetworkError>) -> Void)
}

struct NetworkManager: NetworkManagerType {
    func makeRequest<T>(query: GraphQLQuery, onComplete: @escaping  (Result<T, NetworkError>) -> Void) {
        service.client.fetch(query: query) { res in
            switch res {
            case .failure(let error):
                onComplete(.failure(.unknownError(error.localizedDescription)))
                return
            case .success(let graphQLResponse):
                if let errors = graphQLResponse.errors {
                    onComplete(.failure(.unknownError(errors.jsonValue)))
                    return
                }
                
                onComplete(.success(graphQLResponse.data?.search))
            }
        }
    }
}

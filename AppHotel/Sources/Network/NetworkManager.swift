import HUGraphQL
import Apollo

enum NetworkError: Error {
    case invalidURL
    case emptyData
    case decodingError
    case unknownError(String)
}

protocol NetworkManagerType {
    func makeRequest<Q: GraphQLQuery>(query: Q, onComplete: @escaping (Result<Q.Data?, NetworkError>) -> Void)
}

struct NetworkManager: NetworkManagerType {
    var service = HUGService(enableLog: true)
    func makeRequest<Q: GraphQLQuery>(query: Q, onComplete: @escaping (Result<Q.Data?, NetworkError>) -> Void) {
        service.client.fetch(query: query) { res in
            switch res {
            case .failure(let error):
                onComplete(.failure(.unknownError(error.localizedDescription)))
                return
            case .success(let graphQLResponse):
                if let errors = graphQLResponse.errors {
                    onComplete(.failure(.unknownError(errors.debugDescription)))
                    return
                }
                
                onComplete(.success(graphQLResponse.data))
            }
        }
    }
}

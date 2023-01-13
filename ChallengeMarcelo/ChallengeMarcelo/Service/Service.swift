import HUGraphQL

typealias SearchResult = HUGraphQL.SearchQuery.Data.Search.Result

struct Service {
    // MARK: Singleton

    private init() {}
    static let shared = Service()
    
    // MARK: Properties

    private let hugService = HUGService(enableLog: false)

    // MARK: Public
    
    func perform(
        search queryString: String,
        completion: @escaping (Result<[SearchResult]?, Error>) -> Void
    ) {
        let query = HUGraphQL.SearchQuery(q: queryString, pagination: nil)
        hugService.client.fetch(query: query) { result in
            switch result {
            case .success(let onSuccess):
                completion(.success(onSuccess.data?.search?.results))
            case .failure(let error):
                completion(.failure(error))
            }
        }        
    }
}

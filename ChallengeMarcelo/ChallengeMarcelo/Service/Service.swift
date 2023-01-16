import HUGraphQL

typealias SuggestionResult = HUGraphQL.SuggestionsQuery.Data.Suggestion.Result
typealias SearchHotelResult = HUGraphQL.SearchHotelQuery.Data.SearchHotel.Result
typealias SearchPackageResult = HUGraphQL.SearchPackageQuery.Data.SearchPackage.Result

struct Service {
    // MARK: Singleton
    
    private init() {}
    static let shared = Service()
    
    // MARK: Properties
    
    private let hugService = HUGService(enableLog: true)
    
    // MARK: Public
    
    func performHotel(
        search queryString: String,
        completion: @escaping (Result<[SearchHotelResult]?, Error>) -> Void
    ) {
        let query =
        HUGraphQL.SearchHotelQuery(
            q: queryString,
            filters: nil,
            pagination: nil,
            l10n: .init(pos: "br", locale: "pt", currency: "BRL"),
            //            checkin: Date(),
            checkout: nil,
            rooms: nil
        )
        hugService.client.fetch(query: query) { result in
            switch result {
            case .success(let onSuccess):
                completion(.success(onSuccess.data?.searchHotel?.results))
            case .failure(let error):
                completion(.failure(error))
            }
        }
    }

    func performPackage(
        search queryString: String,
        completion: @escaping (Result<[SearchPackageResult]?, Error>) -> Void
    ) {
        let query = HUGraphQL.SearchPackageQuery(q: queryString, pagination: nil)
        hugService.client.fetch(query: query) { result in
            switch result {
            case .success(let onSuccess):
                completion(.success(onSuccess.data?.searchPackage?.results))
            case .failure(let error):
                completion(.failure(error))
            }
        }
    }

    func performSuggestion(
        queryString: String,
        completion: @escaping (Result<[SuggestionResult]?, Error>) -> Void
    ) {
        let query = HUGraphQL.SuggestionsQuery(
            q: queryString,
            limit: 20,
            //            productType: productType,
            l10n: .init(pos: "br", locale: "pt", currency: "BRL"))
        
        hugService.client.fetch(query: query) { result in
            switch result {
            case .success(let onSuccess):
                completion(.success(onSuccess.data?.suggestions?.results))
            case .failure(let error):
                completion(.failure(error))
            }
        }
    }
}

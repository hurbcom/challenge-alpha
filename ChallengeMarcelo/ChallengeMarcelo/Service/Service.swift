import HUGraphQL

struct Service {
    typealias SearchResult = HUGraphQL.SearchQuery.Data.Search.Result
    
    // MARK: Singleton
    private init() {}
    static let shared = Service()
    
    // MARK: Properties
    private let hugService = HUGService(enableLog: false)
    
    func perform(
        search queryString: String,
        completion: @escaping ([SearchResult]?) -> Void
    ) {
        let query = HUGraphQL.SearchQuery(q: queryString, pagination: nil)
        hugService.client.fetch(query: query) { result in
            switch result {
            case .success(let successResult):
                completion(successResult.data?.search?.results)
            case .failure(let error):
                print(error.localizedDescription)
            }
        }
    }
}



struct Hotel {
//    var url: String?
//    var price: Any?
//    var smallDescription: String?
//    var gallery: Any?
//    var description: String?
    var name: String?
//    var amenities: Any?
//    var category: String?
//    var tags: Any?
//    var id: String?
//    var sku: String?
//    var isAvailable: String?
//    var address: Any?
}

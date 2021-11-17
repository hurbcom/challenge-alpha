import Foundation
import HUGraphQL

protocol HotelRepositoryType {
    func search(term: String,
                page: Pagination,
                onSuccess: @escaping ([SearchResult], Pagination) -> Void,
                onFailure: @escaping (NetworkError) -> Void)
}

struct Pagination {
    let current: Int
    let hasNext: Bool
}

class HotelRepository: HotelRepositoryType {
    var networkManager: NetworkManagerType!
    
    func search(
        term: String,
        page: Pagination,
        onSuccess: @escaping ([SearchResult], Pagination) -> Void,
        onFailure: @escaping (NetworkError) -> Void
    ) {
        let pagination = HUGraphQL.SearchInputPagination(
            page: page.current + 1,
            limit: 20,
            sort: .score,
            sortOrder: .desc)
        let searchQuery = HUGraphQL.SearchQuery(q: term, pagination: pagination)
        networkManager.makeRequest(query: searchQuery) { result in
            switch result {
            case .failure(let error):
                onFailure(.unknownError(error.localizedDescription))
                return
            case .success(let response):
                guard let data = response?.search,
                      let list = data.results?.toModel(),
                      let pagination = data.pagination?.toModel() else {
                    return
                }
                onSuccess(list, pagination)
            }
        }
    }
}

typealias SearchData = HUGraphQL.SearchQuery.Data.Search

extension Array where Element == SearchData.Result {
    func toModel() -> [SearchResult] {
        return self.map {
            SearchResult(
                name: $0.name,
                description: $0.smallDescription,
                category: ResultCategory(rawValue: $0.category) ?? .none)
        }
    }
}

extension SearchData.Pagination {
    func toModel() -> Pagination {
        return Pagination(
            current: self.current,
            hasNext: self.hasNext)
    }
}

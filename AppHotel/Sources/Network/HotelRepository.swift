import Foundation
import HUGraphQL

protocol HotelRepositoryType {
    func getHotel(id: String, onSuccess: @escaping (Hotel) -> Void)
    func searchHotels(query: String, onSuccess: @escaping ([Hotel]) -> Void)
    func searchPackages(query: String, onSuccess: @escaping ([String]) -> Void)
}

class HotelRepository: HotelRepositoryType {
    var networkManager: NetworkManagerType!
    
    func getHotel(id: String, onSuccess: @escaping (Hotel) -> Void) {
//        onSuccess(Hotel]())
    }
    
    func searchHotels(query: String, onSuccess: @escaping ([Hotel]) -> Void) {
        let pagination = HUGraphQL.SearchInputPagination(
            page: 1,
            limit: 100,
            sort: .score,
            sortOrder: .desc)
        let searchQuery = HUGraphQL.SearchHotelQuery(
            q: query,
            filters: nil,
            pagination: pagination,
            l10n: .init(pos: "br", locale: "pt", currency: "BRL"),
            checkin: Date(),
            checkout: nil,
            rooms: nil)
        networkManager.makeRequest(query: searchQuery) { result in
            switch result {
            case .failure(let error):
                return
            case .success(let response):
                guard let list = response?.searchHotel?.results?.toModel() else {
                    return
                }
                onSuccess(list)
            }
        }
    }
    
    func searchPackages(query: String, onSuccess: @escaping ([String]) -> Void) {
        onSuccess([])
    }
}

typealias SearchHotelResult = HUGraphQL.SearchHotelQuery.Data.SearchHotel.Result

extension Array where Element == SearchHotelResult {
    func toModel() -> [Hotel] {
        return self.map { Hotel(name: $0.name, description: $0.smallDescription) }
    }
}

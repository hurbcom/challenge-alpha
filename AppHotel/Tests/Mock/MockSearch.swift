@testable import AppHotel

class MockSearch: SearchRepositoryType {
    var shouldFail = false
    var mockResponse: [SearchResult] = []
    var mockPagination: Pagination = Pagination(current: 1, hasNext: true)
    var mockError: NetworkError = .unknownError("Erro")

    @Spy var invokedSearch: (term: String, page: Int)?

    func search(
        term: String,
        page: Pagination,
        onSuccess: @escaping ([SearchResult], Pagination) -> Void,
        onFailure: @escaping (NetworkError) -> Void
    ) {
        invokedSearch = (term, page.current)
        if shouldFail {
            onFailure(mockError)
            return
        }

        onSuccess(mockResponse, mockPagination)
    }
}

protocol HomeInteractorInput {
    func fetchResults(term: String)
}

protocol HomeInteractorOutput {
    func presentResults(_ list: [SearchResult])
    func presentError(_ error: NetworkError)
}

class HomeInteractor: HomeInteractorInput {
    var output: HomeInteractorOutput!
    var repository: SearchRepositoryType!
    var currentPagination: Pagination = Pagination(current: 0, hasNext: true)
    var currentTerm: String?
    
    func fetchResults(term: String) {
        currentPagination = Pagination(current: 0, hasNext: true)
        currentTerm = term
        fetchNextPage()
    }

    func fetchNextPage() {
        guard currentPagination.hasNext,
        let searchTerm = currentTerm else {
            return
        }

        repository.search(term: searchTerm, page: currentPagination, onSuccess: { [weak self] (list, pagination) in
            self?.currentPagination = pagination
            self?.output.presentResults(list)
        }, onFailure: { [weak self] error in
            self?.output.presentError(error)
        })
    }
}

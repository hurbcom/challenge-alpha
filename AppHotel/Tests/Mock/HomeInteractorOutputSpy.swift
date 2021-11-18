@testable import AppHotel

class HomeInteractorOutputSpy: HomeInteractorOutput {
    @Spy var invokedPresentResults: [SearchResult]?
    @Spy var invokedPresentError: NetworkError?

    func presentResults(_ list: [SearchResult]) {
        invokedPresentResults = list
    }

    func presentError(_ error: NetworkError) {
        invokedPresentError = error
    }
}

import UIKit

extension HomeVC {
    func configure() {
        let presenter = HomePresenter()
        presenter.output = self
        
        let interactor = HomeInteractor()
        interactor.output = presenter
        
        let repository = SearchRepository()
        repository.networkManager = NetworkManager()
        interactor.repository = repository

        self.output = interactor

        let router = HomeRouter()
        router.viewController = self
        self.router = router
    }
}

extension HomeInteractor: HomeVCOutput {
    func askForSearch(term: String) {
        fetchResults(term: term)
    }
}

extension HomePresenter: HomeInteractorOutput {
    func presentResults(_ list: [SearchResult]) {
        formatResults(list)
    }
    
    func presentError(_ error: NetworkError) {
        formatError(error)
    }
}

extension HomeVC: HomePresenterOutput {
    func showResults(_ list: [SearchResult]) {
        displayResults(list)
    }
    
    func showAlert(_ alert: UIAlertController) {
        displayAlert(alert)
    }
}

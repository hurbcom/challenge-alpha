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
    }
}

extension HomeInteractor: HomeVCOutput {
    func askForSearch(term: String) {
        fetchProducts(term: term)
    }
}

extension HomePresenter: HomeInteractorOutput {
    func presentProducts(_ list: [SearchResult]) {
        formatProducts(list)
    }
    
    func presentError(_ error: NetworkError) {
        formatError(error)
    }
}

extension HomeVC: HomePresenterOutput {
    func showProducts(_ list: [SearchResult]) {
        displayProducts(list)
    }
    
    func showAlert(_ alert: UIAlertController) {
        displayAlert(alert)
    }
}

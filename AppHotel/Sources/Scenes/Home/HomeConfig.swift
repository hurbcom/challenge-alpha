import UIKit

extension HomeVC {
    func configure() {
        let presenter = HomePresenter()
        presenter.output = self
        
        let interactor = HomeInteractor()
        interactor.output = presenter
        
        self.output = interactor
    }
}

extension HomeInteractor: HomeVCOutput {
    func askForProducts() { fetchProducts() }
}

extension HomePresenter: HomeInteractorOutput {
    func presentProducts(_ list: [Hotel]) {
        formatProducts(list)
    }
    
    func presentError(_ error: String) {
        formatError(error)
    }
}

extension HomeVC: HomePresenterOutput {
    func showProducts(_ list: [Hotel]) {
        displayProducts(list)
    }
    
    func showAlert(_ alert: UIAlertController) {
        displayAlert(alert)
    }
}

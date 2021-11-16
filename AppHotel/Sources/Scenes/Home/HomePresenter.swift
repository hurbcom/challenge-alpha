import UIKit

protocol HomePresenterInput {
    func formatProducts(_ list: [Hotel])
    func formatError(_ error: String)
}

protocol HomePresenterOutput: AnyObject {
    func showProducts(_ list: [Hotel])
    func showAlert(_ alert: UIAlertController)
}

class HomePresenter: HomePresenterInput {
    weak var output: HomePresenterOutput!
    
    func formatProducts(_ list: [Hotel]) {
        output.showProducts(list)
    }
    
    func formatError(_ error: String) {
        let alert = UIAlertController(
            title: "Erro!",
            message: error,
            preferredStyle: .alert)
        let okAction = UIAlertAction(title: "OK", style: .default, handler: nil)
        alert.addAction(okAction)
        output.showAlert(alert)
    }
}

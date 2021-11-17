import UIKit

protocol HomePresenterInput {
    func formatProducts(_ list: [SearchResult])
    func formatError(_ error: NetworkError)
}

protocol HomePresenterOutput: AnyObject {
    func showProducts(_ list: [SearchResult])
    func showAlert(_ alert: UIAlertController)
}

class HomePresenter: HomePresenterInput {
    weak var output: HomePresenterOutput!
    
    func formatProducts(_ list: [SearchResult]) {
        output.showProducts(list)
    }
    
    func formatError(_ error: NetworkError) {
        let alert = UIAlertController(
            title: "Ops!",
            message: error.message,
            preferredStyle: .alert)
        let okAction = UIAlertAction(title: "OK", style: .default, handler: nil)
        alert.addAction(okAction)
        output.showAlert(alert)
    }
}

extension NetworkError {
    var message: String {
        switch self {
        case .invalidURL: return "URL Inválida"
        case .emptyData: return "Retorno vazio"
        case .decodingError: return "Objeto não identificado na resposta"
        case .unknownError(let string): return "Erro: \(string)"
        }
    }
}

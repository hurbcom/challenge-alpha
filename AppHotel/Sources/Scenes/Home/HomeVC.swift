import UIKit

protocol HomeVCInput {
    func displayProducts(_ list: [Hotel])
    func displayAlert(_ alert: UIAlertController)
}

protocol HomeVCOutput {
    func askForProducts()
}

class HomeVC: UIViewController {
    // MARK: - Init and variables
    var router: HomeRouter!
    var output: HomeVCOutput!
    
    init() {
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    // MARK: - Lifecycle
    override func viewDidLoad() {
        configure()
        view.backgroundColor = .white
        output.askForProducts()
    }
}

extension HomeVC: HomeVCInput {
    func displayProducts(_ list: [Hotel]) {
        // Update tableView
    }
    
    func displayAlert(_ alert: UIAlertController) {
        present(alert, animated: true, completion: nil)
    }
}

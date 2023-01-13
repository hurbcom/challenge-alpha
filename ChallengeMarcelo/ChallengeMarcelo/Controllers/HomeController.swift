import UIKit

final class HomeController: UIViewController {

    lazy var screenView = HomeView()
    
    let viewModel = HomeViewModel()
    
    override func loadView() {
        view = screenView
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        screenView.tableView.delegate = self
        screenView.searchButton.addTarget(self, action: #selector(didTapSearch), for: .touchUpInside)
    }
    
    @objc func didTapSearch() {
        viewModel.performSearch(for: screenView.inputField.text ?? "")
    }
}

extension HomeController: UITableViewDelegate {
    
}

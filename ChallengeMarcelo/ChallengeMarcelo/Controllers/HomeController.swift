import UIKit

final class HomeController: UIViewController {
    // MARK: UI

    lazy var screenView = HomeView()
    
    // MARK: Properties
    
    private var dataSource = [Product]()
    private let viewModel = HomeViewModel()

    // MARK: Override
    
    override func loadView() {
        view = screenView
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        viewModel.delegate = self
        screenView.tableView.delegate = self
        screenView.tableView.dataSource = self
        screenView.searchButton.addTarget(self, action: #selector(didTapSearch), for: .touchUpInside)
    }
    
    // MARK: Obj-C
    
    @objc func didTapSearch() {
        viewModel.performSearch(for: screenView.inputField.text ?? "")
    }
}

// MARK: - UITableViewDelegate

extension HomeController: UITableViewDelegate {
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        print(dataSource[indexPath.row].name)
    }
}

// MARK: - UITableViewDataSource

extension HomeController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return dataSource.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = UITableViewCell()
        cell.textLabel?.text = "[\(dataSource[indexPath.row].category)] \(dataSource[indexPath.row].name)"
        return cell
    }
}

// MARK: - HomeViewModelDelegate

extension HomeController: HomeViewModelDelegate {
    func didUpdate(products: [Product]) {
        dataSource = products

        DispatchQueue.main.async { [weak self] in
            guard let self else { return }
            self.screenView.tableView.reloadData()
        }
    }
}

import UIKit

final class ProductListController: UIViewController {
    // MARK: UI

    lazy var screenView = ProductListView()
    
    // MARK: Properties

    private let viewModel = ProductListViewModel()

    // MARK: Override
    
    override func loadView() {
        view = screenView
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        viewModel.delegate = self
        screenView.tableView.delegate = self
        screenView.tableView.dataSource = self
        screenView.tableView.register(ProductListCell.self, forCellReuseIdentifier: "HomeCell")
        screenView.inputField.searchButton.addTarget(self, action: #selector(didTapSearch), for: .touchUpInside)
        screenView.segmentedControl.addTarget(self, action: #selector(didChangeSegmentedControl), for: .valueChanged)
    }
    
    // MARK: Obj-C

    @objc func didTapSearch() {
        shouldSearch()
    }

    @objc private func didChangeSegmentedControl() {
        shouldSearch()
    }
    
    // MARK: - Private
    
    private func shouldSearch() {
        guard let place = screenView.inputField.textField.text else { return }

        if screenView.segmentedControl.selectedSegmentIndex == 0 {
            viewModel.searchHotels(in: place)
        } else {
            viewModel.searchPackages(in: place)
        }
    }
}

// MARK: - UITableViewDelegate

extension ProductListController: UITableViewDelegate {
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        navigationController?.pushViewController(
            ProductDetailViewController(model: viewModel.products[indexPath.row]),
            animated: true
        )
    }
}

// MARK: - UITableViewDataSource

extension ProductListController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return viewModel.products.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if let cell = tableView.dequeueReusableCell(withIdentifier: "HomeCell", for: indexPath) as? ProductListCell {
            cell.setupCell(product: viewModel.products[indexPath.row])
            return cell
        } else {
            return UITableViewCell()
        }
    }
}

// MARK: - HomeViewModelDelegate

extension ProductListController: ProductListViewModelDelegate {
    func didUpdate() {
        DispatchQueue.main.async { [weak self] in
            guard let self else { return }
            self.screenView.tableView.reloadData()
        }
    }
}

import UIKit

final class FavoritesViewController: UIViewController {
    // MARK: UI
    
    lazy var screenView = FavoritesView()
    
    // MARK: Properties
    
    var arrayData = UserDefaults.standard.array(forKey: "favorites") as? [Data] ?? [Data]()
    
    // MARK: Override
    
    override func loadView() {
        view = screenView
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        screenView.tableView.dataSource = self
        screenView.tableView.register(ProductListCell.self, forCellReuseIdentifier: "HomeCell")
    }
}

// MARK: - UITableViewDataSource

extension FavoritesViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return arrayData.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let data = arrayData[indexPath.row]
        let product = try! JSONDecoder().decode(Product.self, from: data)

        if let cell = tableView.dequeueReusableCell(withIdentifier: "HomeCell", for: indexPath) as? ProductListCell {
            cell.setupCell(product: product)
            return cell
        }

        return UITableViewCell()
    }
}

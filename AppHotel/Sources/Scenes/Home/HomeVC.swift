import UIKit

protocol HomeVCInput {
    func displayProducts(_ list: [SearchResult])
    func displayAlert(_ alert: UIAlertController)
}

protocol HomeVCOutput {
    func askForSearch(term: String)
}

class HomeVC: UIViewController {
    // MARK: - Init and variables
    var router: HomeRouter!
    var output: HomeVCOutput!
    
    var items: [SearchResult] = []
    var searchTerm: String! {
        didSet {
            title = searchTerm
            output.askForSearch(term: searchTerm)
        }
    }

    init() {
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    // MARK: - Layout components
    private lazy var tableView: UITableView = {
        let tableView = UITableView()
        tableView.setCodable()
        tableView.separatorStyle = .singleLine
        tableView.dataSource = self
        return tableView
    }()

    // MARK: - Lifecycle
    override func viewDidLoad() {
        configure()
        setupViews()

        view.backgroundColor = .white
        searchTerm = "Bariloche"
    }

    func setupViews() {
        view.addSubview(tableView)
        tableView.setEdgesToSuperview()
    }
}

extension HomeVC: HomeVCInput {
    func displayProducts(_ list: [SearchResult]) {
        items = list
        tableView.reloadData()
    }
    
    func displayAlert(_ alert: UIAlertController) {
        present(alert, animated: true, completion: nil)
    }
}

extension HomeVC: UITableViewDataSource {
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return items.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = UITableViewCell(style: .subtitle, reuseIdentifier: "id")
        let item = items[indexPath.row]
        cell.textLabel?.text = item.name
        cell.detailTextLabel?.text = item.description

        switch item.category {
        case .activity: cell.textLabel?.textColor = .green
        case .hotel: cell.textLabel?.textColor = .systemOrange
        case .none: cell.textLabel?.textColor = .lightGray
        case .package: cell.textLabel?.textColor = .blue
        }

        return cell
    }
}

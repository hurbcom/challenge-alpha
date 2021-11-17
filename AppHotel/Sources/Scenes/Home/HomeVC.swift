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
        tableView.separatorStyle = .none
        tableView.dataSource = self
        tableView.register(ResultCell.self)
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
        let cell = tableView.dequeueReusableCell(ResultCell.self, for: indexPath)!
        let item = items[indexPath.row]
        cell.configure(result: item)
        return cell
    }
}

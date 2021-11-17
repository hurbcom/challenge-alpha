import UIKit

enum DetailSections {
    case banner(gallery: [ResultImage])
    case description(text: String)
}

class DetailsVC: UIViewController {
    // MARK: - Init and variables
//    var router: DetailsRouter!
//    var viewModel: DetailsViewModelContract!
    
    let result: SearchResult
    let sections: [DetailSections]

    init(result: SearchResult) {
        self.result = result
        self.sections = [
            .description(text: result.description)
        ]

        super.init(nibName: nil, bundle: nil)

        title = result.name
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
        tableView.register(DescriptionCell.self)
        return tableView
    }()

    // MARK: - Lifecycle
    override func viewDidLoad() {
        setupViews()

        view.backgroundColor = .white
    }

    func setupViews() {
        view.addSubview(tableView)
        tableView.setEdgesToSuperview()
    }
}

extension DetailsVC: UITableViewDataSource {
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return sections.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let item = sections[indexPath.row]
        switch item {
            case .description(let text):
                let cell = tableView.dequeueReusableCell(DescriptionCell.self, for: indexPath)!
                cell.configure(description: text)
                return cell
            case .banner(gallery: let gallery):
                return UITableViewCell()
        }
    }
}

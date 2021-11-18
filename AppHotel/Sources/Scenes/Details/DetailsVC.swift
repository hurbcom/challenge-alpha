import UIKit

enum DetailSections {
    case banner(image: String)
    case info(result: SearchResult)
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
            .banner(image: result.cover),
            .info(result: result),
            .description(text: result.description)
        ]

        super.init(nibName: nil, bundle: nil)

        title = result.name
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    // MARK: - Layout components
    lazy var tableView: UITableView = {
        let tableView = UITableView()
        tableView.setCodable()
        tableView.separatorStyle = .none
        tableView.dataSource = self
        tableView.register(BannerCell.self)
        tableView.register(InfoCell.self)
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
            case .banner(let image):
                let cell = tableView.dequeueReusableCell(BannerCell.self, for: indexPath)!
                cell.configure(coverImage: image)
                return cell
            case .info(let result):
                let cell = tableView.dequeueReusableCell(InfoCell.self, for: indexPath)!
                cell.configure(result: result)
                return cell
            case .description(let text):
                let cell = tableView.dequeueReusableCell(DescriptionCell.self, for: indexPath)!
                cell.configure(description: text)
                return cell
        }
    }
}

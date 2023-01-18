import UIKit

final class SuggestionsViewController: UIViewController {

    lazy var screenView = SuggestionView()
    let viewModel: SuggestionsViewModel
    
    init(model: [Suggestion]) {
        self.viewModel = SuggestionsViewModel(model: model)
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder: NSCoder) { nil }
    
    override func loadView() {
        view = screenView
    }

    override func viewDidLoad() {
        super.viewDidLoad()
        screenView.tableView.register(SuggestionCell.self, forCellReuseIdentifier: "SuggestionCell")
        screenView.tableView.dataSource = self
        screenView.tableView.delegate = self
    }
}

extension SuggestionsViewController: UITableViewDelegate {

}

extension SuggestionsViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        viewModel.suggestions.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if let cell = tableView.dequeueReusableCell(withIdentifier: "SuggestionCell", for: indexPath) as? SuggestionCell {
            cell.setupCell(suggestion: viewModel.suggestions[indexPath.row])
            return cell
        }
           return UITableViewCell()
    }
}

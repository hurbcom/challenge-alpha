//
//  SearchViewController.swift
//  Challenge_iOS
//
//  Created by Helio Junior on 12/11/22.
//

import UIKit

class SearchViewController: BaseViewController {
    // MARK: Properties
    private var viewModel: SearchViewModel
    private let searchController = UISearchController(searchResultsController: nil)
    private var viewSearchSuggestions: SuggestionsView = SuggestionsView.fromNib()
    
    // MARK: Outlets
    @IBOutlet weak var tableView: UITableView!
    
    // MARK: Initialization
    init(viewModel: SearchViewModel = SearchViewModel()) {
        self.viewModel = viewModel
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    // MARK: Overrides
    override func viewDidLoad() {
        super.viewDidLoad()
        
        bindEvents()
        setupUI()
    }
    
    // MARK: Actions
    
    // MARK: BindEvents
    private func bindEvents() {
        viewModel.didReturnSuggestions = { [weak self] suggestions in
            DispatchQueue.main.async {
                self?.viewSearchSuggestions.setup(with: suggestions)
            }
        }
        
        viewModel.shouldUpdateUI = { [weak self] in
            DispatchQueue.main.async {
                guard let self = self else { return }
                self.tableView.reloadData()
                self.closeLoading()
            }
        }
        
        viewSearchSuggestions.didSelectedSuggestion = { [weak self] suggestion in
            DispatchQueue.main.async {
                self?.searchController.searchBar.text = suggestion.text
                self?.viewModel.fetchSearchFrom(query: suggestion.text)
                if #available(iOS 13.0, *) {
                    self?.searchController.searchBar.searchTextField.resignFirstResponder()
                }
                self?.becomeFirstResponder()
            }
        }
    }
    
    // MARK: Methods
    
    // MARK: Setup
    private func setupUI() {
        setupSearchController()
        setupSugestionView()
        setupTableView()
    }
    
    private func setupTableView() {
        tableView.register(UINib(nibName: CardSearchTableViewCell.identifier,
                                 bundle: nil),
                           forCellReuseIdentifier: CardSearchTableViewCell.identifier)
        tableView.dataSource = self
    }
    
    private func setupSearchController() {
        navigationItem.searchController = self.searchController
        searchController.obscuresBackgroundDuringPresentation = false
        searchController.searchBar.placeholder = "Pesquisar..."
        searchController.searchBar.delegate = self
    }
    
    private func setupSugestionView() {
        viewSearchSuggestions.isHidden = true
        viewSearchSuggestions.frame = view.bounds
        view.addSubview(viewSearchSuggestions)
        viewSearchSuggestions.bringSubviewToFront(view)
    }
}

// MARK: Extensions
extension SearchViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        viewModel.getSearchResults().count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        let model = viewModel.getSearchResults()[indexPath.row]
        
        if let cell = tableView.dequeueReusableCell(withIdentifier: CardSearchTableViewCell.identifier) as? CardSearchTableViewCell {
            
            cell.setupWith(model: model)
            return cell
        }
        
        return UITableViewCell()
    }
}

extension SearchViewController: UISearchBarDelegate {
    func searchBar(_ searchBar: UISearchBar, textDidChange searchText: String) {
        viewModel.getSuggestionsFrom(text: searchText)
    }
    
    func searchBarShouldBeginEditing(_ searchBar: UISearchBar) -> Bool {
        viewSearchSuggestions.isHidden = false
        return true
    }
    
    func searchBarTextDidEndEditing(_ searchBar: UISearchBar) {
        viewSearchSuggestions.isHidden = true
    }
    
    func searchBarSearchButtonClicked(_ searchBar: UISearchBar) {
        self.becomeFirstResponder()
        showLoading()
        viewModel.fetchSearchFrom(query: searchBar.text ?? "")
    }
    
    func searchBarCancelButtonClicked(_ searchBar: UISearchBar) {
        self.becomeFirstResponder()
    }
}

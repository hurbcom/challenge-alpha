//
//  PackageViewController.swift
//  Challenge_iOS
//
//  Created by Helio Junior on 12/11/22.
//

import UIKit

final class PackageViewController: BaseViewController {
    // MARK: Properties
    private var viewModel: PackageViewModel
    let searchController = UISearchController(searchResultsController: nil)
    var viewSearchSuggestions: SuggestionsView = SuggestionsView.fromNib()
    let viewSearchNotFound: SearchNotFoundView = SearchNotFoundView.fromNib()
    
    // MARK: Outlets
    @IBOutlet weak var tableView: UITableView!
    
    // MARK: Initialization
    init(viewModel: PackageViewModel = PackageViewModel()) {
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
    
    override func viewDidAppear(_ animated: Bool) {
        super.viewDidAppear(animated)
        title = "Pacote"
        navigationController?.navigationBar.prefersLargeTitles = true
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        title = ""
    }
    
    // MARK: Actions
    
    // MARK: BindEvents
    private func bindEvents() {
        viewModel.didReturnSuggestions = { [weak self] in
            DispatchQueue.main.async {
                guard let self = self else { return }
                self.viewSearchSuggestions.setup(with: self.viewModel.getSuggestionsResults())
            }
        }
        
        viewModel.shouldUpdateUI = { [weak self] in
            DispatchQueue.main.async {
                guard let self = self else { return }
                self.reloadData()
                self.closeLoading()
            }
        }
        
        viewModel.shouldShowNotFound = { [weak self] in
            self?.showViewNotFoundResult()
        }
        
        viewSearchSuggestions.didSelectedSuggestion = { [weak self] suggestion in
            self?.viewModel.findPackageFrom(query: suggestion.text)
            DispatchQueue.main.async {
                self?.searchController.searchBar.text = suggestion.text
                if #available(iOS 13.0, *) {
                    self?.searchController.searchBar.searchTextField.resignFirstResponder()
                }
                self?.becomeFirstResponder()
            }
        }
    }
    
    // MARK: Methods
    private func showViewNotFoundResult() {
        DispatchQueue.main.async {
            self.tableView.reloadData()
            self.tableView.backgroundView = self.viewSearchNotFound
            self.closeLoading()
        }
    }
    
    private func reloadData() {
        DispatchQueue.main.async {
            self.tableView.reloadData()
            self.tableView.backgroundView = nil
        }
    }
    
    private func showPackageDetail(_ model: SearchResultModel) {
        let viewController = PackageDetailViewController(model: model)
        self.navigationController?.pushViewController(viewController, animated: true)
    }
    
    // MARK: Setup
    private func setupUI() {
        setupSearchController()
        setupSugestionView()
        setupTableView()
    }
    
    private func setupTableView() {
        tableView.register(UINib(nibName: CardPackageTableViewCell.identifier,
                                 bundle: nil),
                           forCellReuseIdentifier: CardPackageTableViewCell.identifier)
        tableView.dataSource = self
        tableView.delegate = self
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
extension PackageViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        viewModel.getSearchResults().count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let model = viewModel.getSearchResults()[indexPath.row]
        
        if let cell = tableView.dequeueReusableCell(withIdentifier: CardPackageTableViewCell.identifier) as? CardPackageTableViewCell {
            
            cell.setupWith(model: model)
            cell.didClicked = {
                self.showPackageDetail(model)
            }
            return cell
        }
        return UITableViewCell()
    }
}

extension PackageViewController: UITableViewDelegate {
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let model = viewModel.getSearchResults()[indexPath.row]
        showPackageDetail(model)
    }
}

extension PackageViewController: UISearchBarDelegate {
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
        viewModel.findPackageFrom(query: searchBar.text ?? "")
    }
    
    func searchBarCancelButtonClicked(_ searchBar: UISearchBar) {
        self.becomeFirstResponder()
        viewSearchSuggestions.isHidden = true
    }
}

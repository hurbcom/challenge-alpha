//
//  SearchViewController.swift
//  HurbHotel_iOS
//
//  Created by Helio Junior on 21/07/20.
//  Copyright © 2020 HelioTecnologia. All rights reserved.
//

import UIKit

final class SearchViewController: BaseViewController {
    
    // MARK: Properties
    let viewModel = SearchViewModel()
    let searchController = UISearchController(searchResultsController: nil)
    let viewSearchNotFound = SearchNotFoundView().instanceFromNib()
    var viewSearchSuggestions: SearchSuggestionsView = SearchSuggestionsView.fromNib()
    
    // MARK: Outlets
    @IBOutlet weak var tableView: UITableView! {
        didSet{
            tableView.register(UINib(nibName: ProductCardCell().identifier, bundle: nil), forCellReuseIdentifier: ProductCardCell().identifier)
            tableView.dataSource = self
            tableView.delegate = self
        }
    }
    
    // MARK: Overrides
    override func viewDidLoad() {
        super.viewDidLoad()
        
        bindEvents()
        configureSearchController()
    }
    
    override var canBecomeFirstResponder: Bool {
        return true
    }
    
    // MARK: Helpers
    func bindEvents() {
        viewModel.didSuccess = { [weak self] in
            self?.closeLoading()
            if self?.viewModel.products.isEmpty ?? true {
                self?.notFoundResult()
            } else {
                self?.loadResults()
            }
        }
        
        viewModel.didError = { [weak self] error in
            debugPrint("==> Error: \(error)")
            self?.closeLoading()
        }
        
        viewModel.notFound = { [weak self] in
            self?.closeLoading()
            self?.notFoundResult()
        }
        
        viewModel.didReturnSuggestions = { [weak self] suggestions in
            DispatchQueue.main.async {
                self?.viewSearchSuggestions.setup(with: suggestions.suggestions)
            }
        }
        
        viewSearchSuggestions.didSelectedSuggestion = { [weak self] suggestion in
            self?.becomeFirstResponder()
            self?.viewModel.newSearchFrom(term: suggestion.city)
        }
    }
    
    private func notFoundResult() {
        DispatchQueue.main.async {
            self.tableView.isHidden = false
            self.tableView.reloadData()
            self.tableView.backgroundView = self.viewSearchNotFound
        }
    }
    
    private func loadResults() {
        DispatchQueue.main.async {
            self.tableView.isHidden = false
            self.tableView.reloadData()
            self.tableView.backgroundView = nil
        }
    }
    
    func configureSearchController() {
        navigationItem.searchController = self.searchController
        
        searchController.obscuresBackgroundDuringPresentation = false
        searchController.searchBar.placeholder = "Pesquisar..."
        searchController.searchBar.delegate = self
    }
}

// MARK: Extensions
extension SearchViewController: UISearchBarDelegate {
    func searchBar(_ searchBar: UISearchBar, textDidChange searchText: String) {
        viewModel.getSuggestions(term: searchText)
    }
    
    func searchBarShouldBeginEditing(_ searchBar: UISearchBar) -> Bool {
        tableView.isHidden = true
        tableView.backgroundView = viewSearchSuggestions
        return true
    }
    
    func searchBarTextDidEndEditing(_ searchBar: UISearchBar) {
        tableView.backgroundView = nil
        tableView.isHidden = false
    }
    
    func searchBarSearchButtonClicked(_ searchBar: UISearchBar) {
        self.becomeFirstResponder()
        showLoading()
        tableView.isHidden = true
        viewModel.newSearchFrom(term: searchBar.text)
    }
    
    func searchBarCancelButtonClicked(_ searchBar: UISearchBar) {
        self.becomeFirstResponder()
    }
}

extension SearchViewController: UITableViewDataSource {
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return viewModel.products.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let card = viewModel.products[indexPath.row]
        guard let cell = tableView.dequeueReusableCell(withIdentifier: ProductCardCell().identifier) as? ProductCardCell else {return UITableViewCell()}
        cell.setup(with: card)
        return cell
    }
}

extension SearchViewController: UITableViewDelegate {
    func tableView(_ tableView: UITableView, willDisplay cell: UITableViewCell, forRowAt indexPath: IndexPath) {
        if indexPath.row > (viewModel.products.count - 3) {
            showLoading()
            viewModel.paginationSearch()
        }
    }
}

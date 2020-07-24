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
    var termSearch: String? {
        didSet{
            self.navigationItem.title = termSearch != nil ? termSearch : "Perquisar"
        }
    }
    let viewSearchNotFound = SearchNotFoundView().instanceFromNib()
    
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
            if self?.viewModel.searchResult?.results?.isEmpty ?? true {
                self?.notFoundResult()
            } else {
                self?.loadResults()
            }
        }
        
        viewModel.didError = { [weak self] error in
            debugPrint("==> Error: \(error)")
            self?.notFoundResult()
        }
    }
    
    private func notFoundResult() {
        DispatchQueue.main.async {
            self.closeLoading()
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
            self.closeLoading()
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
        //Sugestions...
    }
    
    func searchBarSearchButtonClicked(_ searchBar: UISearchBar) {
        guard let term = searchBar.text else {return}
        termSearch = term
        self.becomeFirstResponder()
        showLoading()
        tableView.isHidden = true
        viewModel.searchFrom(term: term, page: "1")
    }
    
    func searchBarCancelButtonClicked(_ searchBar: UISearchBar) {
        self.becomeFirstResponder()
        termSearch = nil
    }
}

extension SearchViewController: UITableViewDataSource {
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return viewModel.searchResult?.results?.count ?? 0
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        guard let card = viewModel.searchResult?.results?[indexPath.row] else {return UITableViewCell()}
        
        if let cell = tableView.dequeueReusableCell(withIdentifier: ProductCardCell().identifier) as? ProductCardCell {
            cell.setup(with: card)
            
            return cell
        }
        return UITableViewCell()
    }
}

extension SearchViewController: UITableViewDelegate {
}

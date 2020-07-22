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
        
        //viewModel.searchFrom(term: "gramado", page: "1")
    }
    
    // MARK: Helpers
    func bindEvents() {
        viewModel.didSuccess = { [weak self] in
            print("==> SearchResult: \(String(describing: self?.viewModel.searchResult?.results?.compactMap({ $0.sku })))")
            self?.reloadTable()
        }
        
        viewModel.didError = { error in
            print("==> Error: \(error)")
        }
    }
    
    private func reloadTable() {
        DispatchQueue.main.async {
            self.tableView.reloadData()
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
        print("==> TERM: \(term)")
        viewModel.searchFrom(term: term, page: "1")
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

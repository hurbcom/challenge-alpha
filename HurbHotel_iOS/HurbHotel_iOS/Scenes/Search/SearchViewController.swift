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
    let viewSearchNotFound: SearchNotFoundView = SearchNotFoundView.fromNib()
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
        configureSugestionView()
    }
    
    override var canBecomeFirstResponder: Bool {
        return true
    }
    
    private func configureSugestionView() {
        viewSearchSuggestions.isHidden = true
        viewSearchSuggestions.frame = view.bounds
        view.addSubview(viewSearchSuggestions)
        viewSearchSuggestions.bringSubviewToFront(view)
    }
    
    // MARK: Helpers
    func bindEvents() {
        ///Retorno da chamada de Busca
        viewModel.didSuccess = { [weak self] in
            self?.closeLoading()
            if self?.viewModel.products.isEmpty ?? true {
                self?.notFoundResult()
            } else {
                self?.loadResults()
            }
        }
        
        ///Qualquer erro após fazer uma chamada.
        viewModel.didError = { [weak self] error in
            debugPrint("==> Error: \(error)")
            self?.closeLoading()
        }
        
        ///Quando a chamada de Busca não encontra nada.
        viewModel.notFound = { [weak self] in
            self?.closeLoading()
            self?.notFoundResult()
        }
        
        ///Retorno da chamada de sugestões.
        viewModel.didReturnSuggestions = { [weak self] suggestions in
            DispatchQueue.main.async {
                self?.viewSearchSuggestions.setup(with: suggestions.suggestions)
            }
        }
        
        ///Quando é clicado em uma Sugestão.
        viewSearchSuggestions.didSelectedSuggestion = { [weak self] suggestion in
            debugPrint("==> selected suggestion: \(suggestion)")
            self?.becomeFirstResponder()
            var term = suggestion.city
            if term == nil { term = suggestion.state }
            if term == nil { term = suggestion.text }
            self?.viewModel.newSearchFrom(term: term?.replacingOccurrences(of: " ", with: "_"))
        }
    }
    
    private func notFoundResult() {
        DispatchQueue.main.async {
            self.tableView.reloadData()
            self.tableView.backgroundView = self.viewSearchNotFound
        }
    }
    
    private func loadResults() {
        DispatchQueue.main.async {
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
//        tableView.backgroundView = viewSearchSuggestions
        viewSearchSuggestions.isHidden = false
        return true
    }
    
    func searchBarTextDidEndEditing(_ searchBar: UISearchBar) {
//        tableView.backgroundView = nil
        viewSearchSuggestions.isHidden = true
    }
    
    func searchBarSearchButtonClicked(_ searchBar: UISearchBar) {
        self.becomeFirstResponder()
        showLoading()
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

//
//  SearchViewController.swift
//  Challenge_iOS
//
//  Created by Helio Junior on 12/11/22.
//

import UIKit

class SearchViewController: BaseViewController {
    // MARK: Properties
    private let searchController = UISearchController(searchResultsController: nil)
    private var viewSearchSuggestions: SuggestionsView = SuggestionsView.fromNib()
    
    // MARK: Outlets
    @IBOutlet weak var tableView: UITableView!
    
    // MARK: Overrides
    override func viewDidLoad() {
        super.viewDidLoad()
        
        setupUI()
    }
    
    // MARK: Actions
    
    // MARK: BindEvents
    private func bindEvents() {
        
    }
    
    // MARK: Methods
    
    // MARK: Updates
    
    // MARK: Setup
    private func setupUI() {
        setupSearchController()
        setupSugestionView()
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
extension SearchViewController: UISearchBarDelegate {
    func searchBar(_ searchBar: UISearchBar, textDidChange searchText: String) {
        print("==> textDidChange: \(searchText)")
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
        print("==> searchBarSearchButtonClicked: \(searchBar.text)")
    }
    
    func searchBarCancelButtonClicked(_ searchBar: UISearchBar) {
        self.becomeFirstResponder()
    }
}

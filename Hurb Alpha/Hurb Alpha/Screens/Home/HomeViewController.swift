//
//  HomeViewController.swift
//  Hurb Alpha
//
//  Created by Thiago Augusto on 23/07/20.
//  Copyright © 2020 objectivesev. All rights reserved.
//

import UIKit

class HomeViewController: BaseViewController {
    let viewModel: HomeViewModel
    
    private lazy var searchBar: UISearchBar = {
        let search = UISearchBar()
        search.placeholder = "Faça sua busca"
        search.delegate = self
        search.textField?.backgroundColor = .white
        return search
    }()
    
    private lazy var emptyView: EmptyView = {
        let view = EmptyView()
        view.configure(type: .searchInitial)
        return view
    }()
    
    private lazy var tableView: UITableView = {
        let table = UITableView()
        table.tableFooterView = UIView()
        table.separatorStyle = .none
        table.rowHeight = UITableView.automaticDimension
        table.estimatedRowHeight = 120
        table.delegate = self
        table.dataSource = self
        table.registerCell(cellClass: HomeHotelCell.self)
        table.registerHeaderFooter(cellClass: HomeHotelHeader.self)
        let refreshControl = UIRefreshControl()
        refreshControl.tintColor = .primaryColor
        refreshControl.addTarget(self, action: #selector(didRefresh), for: .valueChanged)
        table.refreshControl = refreshControl
        return table
    }()
    
    init(viewModel: HomeViewModel) {
        self.viewModel = viewModel
        super.init()
        viewModel.delegate = self
        configureLayout()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
}

private extension HomeViewController {
    func configureLayout() {
        view.backgroundColor = .white
        
        navigationItem.titleView = searchBar
        
        tableView.createConstraints(view) { maker in
            maker.edges.equalToSuperview()
        }
        
        emptyView.createConstraints(view) { maker in
            maker.center.equalToSuperview()
        }
    }
    
    @objc func didRefresh() {
        viewModel.getItems(reload: true)
    }
    
    func showNoResults() {
        emptyView.configure(type: .search)
        emptyView.isHidden = false
        tableView.isHidden = true
    }
    
    func prepareShowResults() {
        emptyView.isHidden = true
        tableView.isHidden = false
        tableView.reloadData()
    }
}

extension HomeViewController: HomeViewModelDelegate {
    func didSelectAction(_ action: HomeViewModelAction) {
        tableView.endRefreshing()
        switch action {
        case .empty:
            showNoResults()
        case .reload:
            prepareShowResults()
        case .failure(let error, let code):
            if code != HAError.invalidQuery.code {
                showError(error: error)
            }
        }
    }
}

extension HomeViewController: UISearchBarDelegate {
    func searchBar(_ searchBar: UISearchBar, textDidChange searchText: String) {
        viewModel.updateQuery(text: searchBar.text)
    }
    
    func searchBarSearchButtonClicked(_ searchBar: UISearchBar) {
        tableView.beginRefreshing()
        viewModel.getItems(reload: true)
    }
}

extension HomeViewController: UITableViewDelegate, UITableViewDataSource {
    func numberOfSections(in tableView: UITableView) -> Int {
        viewModel.numberOfSections()
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        viewModel.numberOfRowsIn(section)
    }
    
    func tableView(_ tableView: UITableView, viewForHeaderInSection section: Int) -> UIView? {
        let header = tableView.dequeue(cellClass: HomeHotelHeader.self)
        let rating = viewModel.ratingAt(section)
        header.configure(stars: rating)
        return header
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeue(cellClass: HomeHotelCell.self, indexPath: indexPath)
        let item = viewModel.itemAt(indexPath)
        cell.configure(result: item)
        return cell
    }
    
    func tableView(_ tableView: UITableView, willDisplay cell: UITableViewCell, forRowAt indexPath: IndexPath) {
        viewModel.handleDisplayItemAt(indexPath)
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: true)
    }
}

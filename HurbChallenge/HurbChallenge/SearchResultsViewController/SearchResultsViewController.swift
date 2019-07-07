//
//  SearchResultsViewController.swift
//  HurbChallenge
//
//  Created by Felipe Alves on 05/07/19.
//  Copyright © 2019 Bolzaniapps. All rights reserved.
//

import UIKit
import Promises

fileprivate let LOADING_CELL_SECTION: Int = 7
fileprivate let LOADING_CELL_HEIGHT: CGFloat = 60
fileprivate let DEFAULT_SEARCH_TERM: String = "Rio de Janeiro"
fileprivate let RESULT_CELL_HEIGHT: CGFloat = 250

class SearchResultsViewController: UIViewController {

    // MARK: - Outlets
    
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var activityIndicator: UIActivityIndicatorView!
    @IBOutlet weak var noResultsLabel: UILabel!

    // MARK: - Properties
    
    var datasource: SearchResultsDataSource = SearchResultsDataSource()
    var remoteSearch: RemoteSearch!
    var loadingMore: Bool = false
    var searchBar: UISearchBar!

}

// MARK: - View Lifecycle

extension SearchResultsViewController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
        setupTableView()
        createSearchBar()
        performInitialLoad()
    }
}

// MARK: - View Setup

extension SearchResultsViewController {
    
    /**
     Configura a tableView.
     */
    func setupTableView() {
        tableView.dataSource = self
        tableView.delegate = self
        tableView.tableFooterView = UIView()
    }
    
    /**
    Cria uma searchBar e a adiciona à navigationBar.
    */
    func createSearchBar() {
        let searchController = UISearchController(searchResultsController: nil)
        searchController.obscuresBackgroundDuringPresentation = false
        searchController.searchBar.delegate = self
        navigationItem.searchController = searchController
        definesPresentationContext = true
    }
    
    /**
    Reseta o estado da view para o inicial.
    */
    func resetView() {
        datasource = SearchResultsDataSource()
        noResultsLabel.isHidden = true
        tableView.reloadData()
        activityIndicator.startAnimating()
    }
    
    /**
    Anima a inserção das células ao adicionar uma página nova.
    */
    func insertRows(at indexPaths: [IndexPath]) -> Promise<Void> {
        return Promise<Void> { resolve, _ in
            self.tableView.beginUpdates()
            self.tableView.insertRows(at: indexPaths, with: .fade)
            self.tableView.endUpdates()
            resolve(())
        }
    }
}

// MARK: - Loading Results

extension SearchResultsViewController {
    
    /**
     Realiza o load inicial, utilizando o termo padrão ("Rio de Janeiro") caso um não seja definido.
     Antes de realizar a busca, reseta a view para seu estado inicial.
     
     - parameter searchTerm: O termo a ser buscado. Caso um não seja definido, será utilizado "Rio de Janeiro", conforme definido na documentação do desafio.
    */
    func performInitialLoad(with searchTerm: String? = DEFAULT_SEARCH_TERM) {
        guard let searchTerm = searchTerm else { return }
        remoteSearch?.canceled = true
        resetView()
        remoteSearch = RemoteSearch(term: searchTerm)
        remoteSearch
            .loadMore()
            .then(on: DispatchQueue.global(), datasource.update)
            .always(activityIndicator.stopAnimating)
            .then {
                self.noResultsLabel.isHidden = $0.count > 0
                self.tableView.reloadData()
        }
    }
    
    /**
     Carrega a próxima página de resultados do servidor e insere as células novas na tableView.
     Exibe uma célula com um activityIndicator ao fim da tableView durante o load.
    */
    func loadMore() {
        guard !loadingMore else { return }
        loadingMore = true
        tableView.reloadSections(IndexSet(integer: LOADING_CELL_SECTION), with: .none)
        remoteSearch
            .loadMore()
            .then(on: DispatchQueue.global(), datasource.update)
            .then(insertRows)
            .always{self.loadingMore = false}
            .always{self.tableView.reloadSections(IndexSet(integer: LOADING_CELL_SECTION), with: .none)}
    }
}

// MARK: - UISearchBarDelegate

extension SearchResultsViewController: UISearchBarDelegate {
    
    func searchBarSearchButtonClicked(_ searchBar: UISearchBar) {
        performInitialLoad(with: searchBar.text)
    }
    
    func searchBarCancelButtonClicked(_ searchBar: UISearchBar) {
        // Caso o usuário tenha feito alguma busca antes de cancelar, retornamos ao estado inicial com o termo padrão
        if remoteSearch.searchTerm != DEFAULT_SEARCH_TERM {
            performInitialLoad()
        }
    }
}

// MARK: - UITableViewDataSource

extension SearchResultsViewController: UITableViewDataSource {
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return datasource.numberOfSections() + 1
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if section == LOADING_CELL_SECTION {
            return loadingMore ? 1 : 0
        } else {
            return datasource.numberOfRowsInSection(section)
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if indexPath.section == LOADING_CELL_SECTION {
            let cell = tableView.dequeueReusableCell(withIdentifier: "LoadinCell", for: indexPath)
            (cell.viewWithTag(101) as! UIActivityIndicatorView).startAnimating()
            return cell
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier: SearchResultCell.identifier, for: indexPath) as! SearchResultCell
            let item = datasource.item(at: indexPath)
            cell.configure(with: item)
            return cell
        }
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        if indexPath.section == LOADING_CELL_SECTION {
            return LOADING_CELL_HEIGHT
        } else {
            return RESULT_CELL_HEIGHT
        }
    }
    
    func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        return datasource.titleForHeaderInSection(section)
    }
}

// MARK: - UITableViewDelegate

extension SearchResultsViewController: UITableViewDelegate {
    
    func scrollViewDidScroll(_ scrollView: UIScrollView) {
        
        let offsetY = scrollView.contentOffset.y
        let contentHeight = scrollView.contentSize.height

        // Ao chegar próximo do final da tableView, solicitamos a próxima página, se houver
        if offsetY > contentHeight - scrollView.frame.height * 1.1 {
            if remoteSearch.hasMoreToLoad {
                loadMore()
            }
        }
    }
    
}

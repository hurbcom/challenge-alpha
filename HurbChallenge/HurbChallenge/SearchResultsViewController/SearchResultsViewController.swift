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

class SearchResultsViewController: UIViewController {

    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var activityIndicator: UIActivityIndicatorView!
    var datasource: SearchResultsDataSource = SearchResultsDataSource()
    var remoteSearch: RemoteSearch!
    var loadingMore = false
    
    override func viewDidLoad() {
        super.viewDidLoad()
        tableView.dataSource = self
        tableView.delegate = self
        tableView.tableFooterView = UIView()
        performInitialFetch(with: "Rio de Janeiro")
    }
    
    func performInitialFetch(with searchTerm: String) {
        remoteSearch = RemoteSearch(term: searchTerm)
        remoteSearch
            .loadNextPage()
            .then(on: DispatchQueue.global(), datasource.update)
            .always(tableView.reloadData)
            .always(activityIndicator.stopAnimating)
    }
    
    func loadMore() {
        guard !loadingMore else { return }
        loadingMore = true
        tableView.reloadSections(IndexSet(integer: LOADING_CELL_SECTION), with: .none)
        remoteSearch
            .loadNextPage()
            .then(on: DispatchQueue.global(), datasource.update)
            .then(insertRows)
            .always{self.loadingMore = false}
            .always{self.tableView.reloadSections(IndexSet(integer: LOADING_CELL_SECTION), with: .none)}
    }
    
    func insertRows(at indexPaths: [IndexPath]) -> Promise<Void> {
        return Promise<Void> { resolve, _ in
            self.tableView.beginUpdates()
            self.tableView.insertRows(at: indexPaths, with: .fade)
            self.tableView.endUpdates()
            resolve(())
        }
    }
 }

extension SearchResultsViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if section == LOADING_CELL_SECTION {
            return loadingMore ? 1 : 0
        } else {
            return datasource.numberOfRowsInSection(section)
        }
    }
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return datasource.numberOfSections() + 1
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
    
    func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        return datasource.titleForHeaderInSection(section)
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        if indexPath.section == LOADING_CELL_SECTION {
            return 60
        } else {
            return 250
        }
    }
}

extension SearchResultsViewController: UITableViewDelegate {
    
    func scrollViewDidScroll(_ scrollView: UIScrollView) {
        
        let offsetY = scrollView.contentOffset.y
        let contentHeight = scrollView.contentSize.height

        if offsetY > contentHeight - scrollView.frame.height {
            if remoteSearch.hasMoreToLoad() {
                loadMore()
            }
        }
    }
    
}

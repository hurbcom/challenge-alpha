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
        remoteSearch
            .loadNextPage()
            .then(on: DispatchQueue.global(), datasource.update)
            .then(insertRows)
            .always({self.loadingMore = false})
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
        return datasource.numberOfItems(at: section)
    }
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return datasource.numberOfSections()
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: SearchResultCell.identifier, for: indexPath) as! SearchResultCell
        let item = datasource.item(at: indexPath)
        cell.configure(with: item)
        return cell
    }
    
    func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        return datasource.title(for: section)
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 140
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

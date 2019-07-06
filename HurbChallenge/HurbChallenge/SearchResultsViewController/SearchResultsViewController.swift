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
            .then(datasource.update)
            .always(tableView.reloadData)
            .always(activityIndicator.stopAnimating)
    }
    
    func loadMore() {
        loadingMore.toggle()
        tableView.reloadSections(IndexSet(integer: LOADING_CELL_SECTION), with: .none)
        remoteSearch
            .loadNextPage()
            .then(datasource.update)
            .always({self.loadingMore.toggle()})
            .always(tableView.beginUpdates)
            .always({self.tableView.reloadSections(IndexSet(integer: LOADING_CELL_SECTION), with: .none)})
            .then({self.tableView.insertRows(at: $0, with: .automatic)})
            .always(tableView.endUpdates)
    }
 }

extension SearchResultsViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if section == LOADING_CELL_SECTION {
            return loadingMore ? 1 : 0
        } else {
             return datasource.numberOfItems(in: section)
        }
    }
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return datasource.numberOfSections() + 1
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        if indexPath.section == LOADING_CELL_SECTION {
            let cell = tableView.dequeueReusableCell(withIdentifier: "LoadingCell", for: indexPath)
            (cell.viewWithTag(101) as! UIActivityIndicatorView).startAnimating()
            return cell
        } else {
            let cell = tableView.dequeueReusableCell(withIdentifier: HotelTableViewCell.identifier, for: indexPath) as! HotelTableViewCell
            let item = datasource.item(in: indexPath)
            cell.configure(with: item)
            return cell
        }
    }
    
    func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        return datasource.title(for: section)
    }
}

extension SearchResultsViewController: UITableViewDelegate {
    
    func scrollViewDidScroll(_ scrollView: UIScrollView) {
        
        let offsetY = scrollView.contentOffset.y
        let contentHeight = scrollView.contentSize.height

        if offsetY > contentHeight - scrollView.frame.height * 1.1 {
            if remoteSearch.hasMoreToLoad() && !loadingMore {
                loadMore()
            }
        }
    }
    
}

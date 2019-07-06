//
//  SearchResultsViewController.swift
//  HurbChallenge
//
//  Created by Felipe Alves on 05/07/19.
//  Copyright © 2019 Bolzaniapps. All rights reserved.
//

import UIKit

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
        remoteSearch = RemoteSearch(term: "Rio de Janeiro")
        loadMore()
    }
    
    func loadMore() {
        loadingMore = datasource.hasItems()
        if (loadingMore) {
            tableView.reloadSections(IndexSet(integer: LOADING_CELL_SECTION), with: .none)
        }
        remoteSearch
            .loadNextPage()
            .always { self.loadingMore = false }
            .then (self.insertNewResults)
            .always{ self.updateView() }
            .catch({print($0)})
    }

    func insertNewResults(_ newItems: [SearchResultElement]) {
        let alreadyHadItemsBeforeMerge = datasource.hasItems()
        datasource
            .update(with: newItems)
            .then({ insertedIndexPaths in
                if alreadyHadItemsBeforeMerge {
                    self.tableView.beginUpdates()
                    self.tableView.reloadSections(IndexSet(integer: LOADING_CELL_SECTION), with: .none)
                    self.tableView.insertRows(at: insertedIndexPaths, with: .automatic)
                    self.tableView.endUpdates()
                } else {
                    self.tableView.reloadData()
                }
            })
    }
    
    func updateView() {
        activityIndicator.stopAnimating()
    }
}

extension SearchResultsViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if section == LOADING_CELL_SECTION {
            if loadingMore {
                return 1
            } else {
                return 0
            }
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

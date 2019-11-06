//
//  ViewController.swift
//  HurbAlpha
//
//  Created by Rodrigo Bukowitz on 04/11/19.
//  Copyright © 2019 Rodrigo Bukowitz. All rights reserved.
//

import UIKit
import SnapKit
/// Custom UIViewController based on Hurb paterns for this aplication
class HurbViewController: UIViewController {
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.view.backgroundColor = .purple
        //load all offers
        loadOffers()
        
    }
    
    // MARK: - Views
    let feedTableview: UITableView = {
        let feed = UITableView()
        feed.backgroundColor = UIColor.white
        feed.register(HurbTableViewCell.self, forCellReuseIdentifier: "offers")
        return feed
    }()
    
    var currentDataSource: UITableViewDataSource? {
        didSet {
            self.feedTableview.dataSource = currentDataSource
            self.feedTableview.delegate = currentDataSource as! UITableViewDelegate
            self.feedTableview.reloadData()
        }
    }
    
    
    
    // MARK: - Network Methods
    
    /// This method loads all offers
    private func loadOffers() {
        netManager.state = .loading
        netManager.getOffers(place: "buzios", page: 1) { [weak self] (offers, error) in
            if let err = error {
                debugPrint("An error has ocurred trying to get info ", err)
                netManager.state = .error
            }
            guard let offers = offers else { return }
            
            /// make parse function to arrange rows
            self?.currentDataSource = FeedDataSource(with: [
                FeedSection(title: "Hotel", type: .hotel, items: offers)
            ])
            
            if netManager.enableLogs {
                 dump(offers)
            }
            self?.showAllOffers(with: offers)
            netManager.state = .ready
        }
    }
    
    // MARK: - View Methods
    /// display in a tableView all offers
    func showAllOffers(with offers : [HurbOffers] ) {
        self.view.backgroundColor = .white
        /// add feed
        setFeedTableView()
        
    }
    
    /// prepare feedTableView for use
    func setFeedTableView() {
    
        view.addSubview(feedTableview)
        
        /// adding feedTabelViewConstraints with snapKit
        feedTableview.snp.makeConstraints{ (make) in
            make.top.equalToSuperview()
            make.left.equalToSuperview()
            make.width.equalToSuperview()
            make.height.equalToSuperview()
        }
        
    }
    
}



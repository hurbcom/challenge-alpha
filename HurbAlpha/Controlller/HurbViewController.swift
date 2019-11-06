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
        feed.backgroundColor = .white
        feed.register(HurbTableViewCell.self, forCellReuseIdentifier: Identifiers.Hotel.rawValue)
        feed.register(HurbTableViewCell.self, forCellReuseIdentifier: Identifiers.Package.rawValue)
        feed.tableFooterView = UIView()
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
        netManager.getOffers(place: "buzios", page: 2) { [weak self] (offers, error) in
            if let err = error {
                debugPrint("An error has ocurred trying to get info ", err)
                netManager.state = .error
            }
            guard let offers = offers else { return }
            
            /// make parse function to arrange rows
            guard let sections = self?.mapRequestIntoSection(with: offers) else { return }
            self?.currentDataSource = FeedDataSource(with: sections)
            
            if netManager.enableLogs {
                 dump(offers)
            }
            self?.showAllOffers(with: offers)
            netManager.state = .ready
        }
    }
    
    func mapRequestIntoSection(with offers: [HurbOffers]) -> [FeedSection] {
        var packages: [HurbOffers] = []
        var stars: [Int: [HurbOffers]] = [:]
        var sections: [FeedSection] = []
        for item in offers {
            if let _ = item.isPackage {
                packages.append(item)
                continue
            }
            // will be a hotel
            if let star = item.stars {
                if stars[star] == nil {
                    stars[star] = [HurbOffers]()
                }
                stars[star]?.append(item)
            }
        }
        // add packages if they exist
        if !packages.isEmpty {
            sections.append(FeedSection(with: "Packages", cellData: .Package(packages: packages)))
        }
        let starsSorted = stars.sorted {$0.0 > $1.0}
        // add hotels sections by the star
        for (key, value) in starsSorted {
            if key == 1 {
                sections.append(FeedSection(with: "\(key) Stars", cellData: .Hotel(hotels: value)))
            }
            else {
                sections.append(FeedSection(with: "\(key) Stars", cellData: .Hotel(hotels: value)))
            }
        }
        
        return sections
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
            make.top.left.bottom.right.equalTo(view.safeAreaLayoutGuide)
        }
        
    }
    
}



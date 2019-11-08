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
        self.view.backgroundColor = .hurbBlue
        //show loading animation
        loadingAnimation()
        //load all offers
        loadOffers()
        
    }
    
    // MARK: - Views
    /// loading view to represents the wainting for the API
    let loadingView: UIImageView =  {
        let view =  UIImageView(frame: .zero)
        view.contentMode = .scaleAspectFill
        view.animationImages = [UIImage(imageLiteralResourceName: "alpha"),UIImage(imageLiteralResourceName: "alpha1"),UIImage(imageLiteralResourceName: "alpha2"),UIImage(imageLiteralResourceName: "alpha3")]
        view.animationRepeatCount = 30
        view.animationDuration = 1
        return view
    }()
    
    /// Label that will represent fail when trying to make the request
    let errorLabel: UILabel =  {
        let label = UILabel(frame: .zero)
        label.textColor = .white
        label.font = UIFont.boldSystemFont(ofSize: 24)
        label.textAlignment = .left
        label.text = "Desculpe, infelizmente parece que um erro ocorreu :( \nConecte-se a internet e tente novamente..."
        label.numberOfLines = 0
        return label
    }()
    /// tableView that will be our main feed in the application
    let feedTableview: UITableView = {
        let feed = UITableView()
        feed.backgroundColor = .white
        feed.register(HurbTableViewCell.self, forCellReuseIdentifier: Identifiers.Hotel.rawValue)
        feed.register(HurbTableViewCell.self, forCellReuseIdentifier: Identifiers.Package.rawValue)
        feed.separatorStyle = UITableViewCell.SeparatorStyle.none
        feed.tableFooterView = UIView()
        return feed
    }()
    
    var currentDataSource: UITableViewDataSource? {
        didSet {
            self.feedTableview.dataSource = currentDataSource
            self.feedTableview.delegate = currentDataSource as? UITableViewDelegate
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
                self?.setUpError()
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
            sections.append(FeedSection(with: "Pacotes " + "📦", cellData: .Package(packages: packages)))
        }
        let starsSorted = stars.sorted {$0.0 > $1.0}
        // add hotels sections by the star
        for (key, value) in starsSorted {
            if key == 1 {
                let title = "Hotéis " + "⭐️"
                sections.append(FeedSection(with: title, cellData: .Hotel(hotels: value)))
            }
            else {
                var title =  "Hotéis "
                for _ in 0..<key {
                    title += "⭐️"
                }
                sections.append(FeedSection(with: title, cellData: .Hotel(hotels: value)))
            }
        }
        
        return sections
    }
    
    // MARK: - View Methods
    /// makes the loading animation when wainting to the informtion from the API
    func loadingAnimation() {
        self.view.addSubview(loadingView)
        loadingView.snp.makeConstraints{ (make) in
            make.top.equalToSuperview().offset(0)
            make.leading.equalToSuperview().offset(0)
            make.trailing.equalToSuperview().offset(0)
            make.bottom.equalToSuperview()
        }
        loadingView.startAnimating()
    }
    
    /// makes the error image appear when dont have internet conection
    func setUpError() {
        loadingView.stopAnimating()
        self.view.addSubview(errorLabel)
        errorLabel.snp.makeConstraints{ (make) in
            make.top.equalToSuperview().offset(100)
            make.leading.equalToSuperview().offset(30)
            make.trailing.equalToSuperview().offset(-20)
            make.height.equalTo(300)
        }
        
    }
    
    /// display in a tableView all offers
    func showAllOffers(with offers : [HurbOffers] ) {
        self.view.backgroundColor = .white
        //remove loading
        loadingView.removeFromSuperview()
        // add feed
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



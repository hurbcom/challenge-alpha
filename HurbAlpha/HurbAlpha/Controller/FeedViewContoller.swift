//
//  FeedViewContoller.swift
//  HurbAlpha
//
//  Created by Julia Rocha on 04/09/19.
//  Copyright © 2019 Julia Rocha. All rights reserved.
//

import Foundation
import UIKit

// MARK: - Declaration

class FeedViewController: UIViewController, DAORequester {
    
    // MARK: - Outlets
    @IBOutlet weak var feedTableView: UITableView!

    // The cell xib
    var xibCell:TableViewCell?
    
    // The index from the selected cell
    var selectedIndex:Int?
    
    // The refresh control for table view
    private let refreshControl = UIRefreshControl()
    
    // The activity indicator for table view
    private let activityView = UIActivityIndicatorView(style: .gray)
    
    // DAO instance
    var dao = DAO.instance
    
    // The reference for the selected hotel
    var selectedHotel:Result?
    
    override func viewDidLoad() {
        
        self.feedTableView.separatorStyle = .none
        self.feedTableView.estimatedRowHeight = 560
        feedTableView.dataSource = self
        feedTableView.delegate = self
        feedTableView.allowsSelection = true
        feedTableView.refreshControl = refreshControl
        
        refreshControl.tintColor = UIColor.baseBlue
        refreshControl.addTarget(self, action: #selector(refreshData), for: .valueChanged)
        
        let nib = UINib.init(nibName: "TableViewCell", bundle: nil)
        self.feedTableView.register(nib, forCellReuseIdentifier: "TableViewCell")
        
        activityView.color = UIColor.baseBlue
        activityView.frame = CGRect(x: 0, y: 0, width: 300.0, height: 300.0)
        activityView.center = view.center
        activityView.transform = CGAffineTransform(scaleX: 1.5, y: 1.5)
        view.addSubview(activityView)
        activityView.startAnimating()
        
        feedTableView.reloadData()
        dao.jsonDataRequest(page: 1, requester: self)
    }
    
    /**
     Refreshs the table view content.
     */
    @objc func refreshData() {
        dao.jsonDataRequest(page: 1, requester: self)
        self.refreshControl.endRefreshing()
        self.feedTableView.reloadData()
    }
    
    /**
     Refreshs the table view content since it finished loading data from DAO.
     */
    func finishedLoading() {
        debugPrint("finished with", dao.loadedHotels.count)
        DispatchQueue.main.async {
            self.activityView.stopAnimating()
            self.feedTableView.reloadData()
        }
    }
    
    /**
     Handles the error on reading data from JSON.
     */
    func finishedLoading(with Error: HotelReadingError) {
        DispatchQueue.main.async {
            sleep(3)
            debugPrint("Error reading data from JSON, atempting to retry.")
            self.dao.jsonDataRequest(page: 1, requester: self)
        }
        
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "detailsFromFeed" {
            if let destinationVC = segue.destination as? DetailsViewController {
                destinationVC.currentHotel = self.selectedHotel
            }
        }
    }
    
}

// MARK: - Extention for TableViewDelegate and TableViewDataSource

extension FeedViewController: UITableViewDataSource, UITableViewDelegate {
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return dao.hotelsByStars.count
    }
    
    func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        switch dao.hotelsByStars[section].key {
        case 1:
            return String(dao.hotelsByStars[section].key) + " estrela"
        case Int.max:
            return "Pacotes"
        default:
            return String(dao.hotelsByStars[section].key) + " estrelas"
        }
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        self.selectedHotel = dao.hotelsByStars[indexPath.section].value[indexPath.row]
        self.performSegue(withIdentifier: "detailsFromFeed", sender: nil)
    }
    
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return 30
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return dao.hotelsByStars[section].value.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "TableViewCell", for: indexPath) as! TableViewCell
        cell.loadInfo(on: dao.hotelsByStars[indexPath.section].value[indexPath.row])
        return cell
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 572
    }
    
    
}

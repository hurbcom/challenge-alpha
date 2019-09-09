//
//  FeedViewContoller.swift
//  HurbAlpha
//
//  Created by Julia Rocha on 04/09/19.
//  Copyright © 2019 Julia Rocha. All rights reserved.
//

import Foundation
import UIKit

class FeedViewController: UIViewController, DAORequester {
    
    
    
   // var activityView:UIActivityIndicatorView!
    var xibCell:TableViewCell?
    var selectedIndex:Int?
    private let refreshControl = UIRefreshControl()
    private let activityView = UIActivityIndicatorView(style: .gray)
    
    var dao = DAO.instance
    var currentPage = 1
    
    //outlets
    @IBOutlet weak var feedTableView: UITableView!
    


    override func viewDidLoad() {
        
        self.feedTableView.separatorStyle = .none
        
        feedTableView.dataSource = self
        //        feedTableView.delegate = self
        feedTableView.allowsSelection = true
        
        feedTableView.refreshControl = refreshControl
        refreshControl.tintColor = UIColor.baseBlue
        
        refreshControl.addTarget(self, action: #selector(refreshData), for: .valueChanged)
        let nib = UINib.init(nibName: "TableViewCell", bundle: nil)
        self.feedTableView.register(nib, forCellReuseIdentifier: "TableViewCell")
        
        dao.jsonDataRequest(page: currentPage, requester: self)
        
        activityView.color = UIColor.baseBlue
        activityView.frame = CGRect(x: 0, y: 0, width: 300.0, height: 300.0)
        activityView.center = view.center
        activityView.transform = CGAffineTransform(scaleX: 1.5, y: 1.5)
        view.addSubview(activityView)
        activityView.startAnimating()
        
        
        feedTableView.rowHeight = UITableView.automaticDimension
        feedTableView.estimatedRowHeight = UITableView.automaticDimension
        feedTableView.reloadData()
        
    }
    
    @objc func refreshData() {
        dao.jsonDataRequest(page: 1, requester: self)
        self.refreshControl.endRefreshing()
        self.feedTableView.reloadData()
    }
    
    func finishedLoading() {
        debugPrint("finished with", dao.loadedHotels.count)
        DispatchQueue.main.async {
            self.activityView.stopAnimating()
            self.feedTableView.reloadData()
        }
    }
    
    // TODO: Melhorar o feedback erro de leitura
    func finishedLoading(with Error: HotelReadingError) {
        DispatchQueue.main.async {
            sleep(3)
            debugPrint("retrying read data")
            self.dao.jsonDataRequest(page: self.currentPage, requester: self)
        }
        
    }
    
}

extension FeedViewController: UITableViewDataSource, UITabBarDelegate{
    
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
    
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return 100
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
        return 408.0
    
    }
}

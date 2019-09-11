//
//  FavoritesViewController.swift
//  HurbAlpha
//
//  Created by Julia Rocha on 10/09/19.
//  Copyright © 2019 Julia Rocha. All rights reserved.
//

import Foundation
import UIKit

// MARK: - Declaration

class FavoritesViewController: UIViewController {
    
    // MARK: - Outlets
    @IBOutlet weak var favoritesTableView: UITableView!
    @IBOutlet weak var noFavoritesLabel: UILabel!
    
    // The cell xib
    var xibCell:TableViewCell?
    
    // The index from the selected cell on table view
    var selectedIndex:Int?
    
    // The refresh control for table view
    private let refreshControl = UIRefreshControl()
    
    // DAO instance
    var dao = DAO.instance
    
    // The selected hotel reference
    var selectedHotel:Result?
    
    override func viewDidLoad() {
        
        self.favoritesTableView.separatorStyle = .none
        self.favoritesTableView.estimatedRowHeight = 560
        favoritesTableView.dataSource = self
        favoritesTableView.delegate = self
        favoritesTableView.allowsSelection = true
        favoritesTableView.refreshControl = refreshControl
        refreshControl.tintColor = UIColor.baseBlue
        refreshControl.addTarget(self, action: #selector(refreshData), for: .valueChanged)
        
        let nib = UINib.init(nibName: "TableViewCell", bundle: nil)
        self.favoritesTableView.register(nib, forCellReuseIdentifier: "TableViewCell")
        
        favoritesTableView.reloadData()
        
    }
    
    /**
     Refreshs the table view content.
     */
    @objc func refreshData() {
        self.refreshControl.endRefreshing()
        self.favoritesTableView.reloadData()
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        if segue.identifier == "detailsFromFav" {
            if let destinationVC = segue.destination as? DetailsViewController {
                destinationVC.currentHotel = self.selectedHotel
            }
        }
    }
    
    
}


// MARK: - Extention for TableViewDelegate and TableViewDataSource

extension FavoritesViewController: UITableViewDataSource, UITableViewDelegate {
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, titleForHeaderInSection section: Int) -> String? {
        return "Favoritos"
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        self.selectedHotel = dao.favorites[indexPath.row]
        self.performSegue(withIdentifier: "detailsFromFav", sender: nil)
    }
    
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        return 30
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if dao.favorites.count != 0 {
            self.noFavoritesLabel.isHidden = true
        } else {
            self.noFavoritesLabel.isHidden = false
        }
        return dao.favorites.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "TableViewCell", for: indexPath) as! TableViewCell
        cell.loadInfo(on: dao.favorites[indexPath.row])
        return cell
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 572
    }
    
    
}

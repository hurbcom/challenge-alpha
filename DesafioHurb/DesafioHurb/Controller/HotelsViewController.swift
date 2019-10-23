//
//  HotelsViewController.swift
//  DesafioHurb
//
//  Created by Filipo Negrao on 22/10/19.
//  Copyright © 2019 Filipo Negrao. All rights reserved.
//

import Foundation
import UIKit

class HotelsViewController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    
    private var hotels = [Hotel]()
    
    @IBOutlet weak var tableView: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.configView()
        self.loadData()
    }
    
    func configView() {
        self.view.backgroundColor = UIColor.white
        
        self.tableView.delegate = self
        self.tableView.dataSource = self
        self.tableView.register(UITableViewCell.self, forCellReuseIdentifier: "Cell")
    }
    
    func loadData() {
        StaticFunctions.showActivityIndicatorView(onView: self.view)
        ApplicationService.sharedInstance.getHotels(searchText: "Botafogo", pageIndex: 1) { (hotels: [Hotel], error: String?) in
            StaticFunctions.removeActivityIndicatorView()
            if let error = error {
                StaticFunctions.showSimpleAlert(controller: self, title: "Ops!", message: error)
                return
            }
            self.hotels = hotels
            self.tableView.reloadData()
        }
    }
    
    // MARK: Table View Deletage and Data Source
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return self.hotels.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let hotel = self.hotels[indexPath.row]
        let cell = tableView.dequeueReusableCell(withIdentifier: "Cell", for: indexPath)
        cell.textLabel?.text = hotel.name
        return cell
    }

    
}

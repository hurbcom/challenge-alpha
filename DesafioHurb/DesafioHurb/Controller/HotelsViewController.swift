//
//  HotelsViewController.swift
//  DesafioHurb
//
//  Created by Filipo Negrao on 22/10/19.
//  Copyright © 2019 Filipo Negrao. All rights reserved.
//

import Foundation
import UIKit

class HotelsViewController: UIViewController, UITableViewDelegate, UITableViewDataSource, UISearchBarDelegate {
    
    private var hotels = [[Hotel]]()
    private var packages = [Hotel]()
    private var searchTerm: String?
    
    @IBOutlet weak var tableView: UITableView!
    
    let searchController = UISearchController(searchResultsController: nil)
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.title = "Desafio - Hurb: Hoteis"
        
        self.configView()
        self.loadData()
    }
    
    func configView() {
        self.view.backgroundColor = blue
        // Table view
        self.tableView.delegate = self
        self.tableView.dataSource = self
        self.tableView.register(UINib(nibName: "HotelTableViewCell", bundle: nil), forCellReuseIdentifier:"Cell")
        self.tableView.backgroundColor = gray
        
        // Search Bar
        self.searchController.obscuresBackgroundDuringPresentation = false
        self.searchController.hidesNavigationBarDuringPresentation = false
        self.searchController.searchBar.placeholder = "Buscar hóteis"
        self.searchController.searchBar.delegate = self
        self.tableView.tableHeaderView = self.searchController.searchBar
        self.tableView.separatorStyle = .none
        // Visual
        self.navigationController?.navigationBar.barTintColor = blue
        self.navigationController?.navigationBar.titleTextAttributes = [.foregroundColor: UIColor.white]
        self.searchController.searchBar.searchBarStyle = .minimal
        self.searchController.searchBar.backgroundColor = blue
        self.searchController.searchBar.tintColor = UIColor.white
        self.searchController.searchBar.barTintColor = UIColor.white
        let textFieldInsideSearchBar = self.searchController.searchBar.value(forKey: "searchField") as? UITextField
        textFieldInsideSearchBar?.textColor = UIColor.white
        
    }
    
    func loadData() {
        StaticFunctions.showActivityIndicatorView(onView: self.view)
        ApplicationService.sharedInstance.getHotelsAndPackages(searchText: self.searchTerm, pageIndex: 1) { (hotels: [[Hotel]], packages: [Hotel], error: String?) in
            StaticFunctions.removeActivityIndicatorView()
            if let error = error {
                DispatchQueue.main.async {
                    StaticFunctions.showSimpleAlert(controller: self, title: "Ops!", message: error)
                }
                
                return
            }
            self.hotels = hotels
            self.packages = packages
            self.tableView.reloadData()
        }
    }
    
    // MARK: Table View Deletage and Data Source
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return self.hotels.count + 1
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if section >= self.hotels.count {
            return self.packages.count
        } else {
            return self.hotels[section].count
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let section = indexPath.section
        var select : Hotel!
        if section >= self.hotels.count {
            select = self.packages[indexPath.row]
        } else {
            select = self.hotels[section][indexPath.row]
        }
        let cell = tableView.dequeueReusableCell(withIdentifier: "Cell", for: indexPath) as! HotelTableViewCell
        cell.configCell(hotel: select)
        return cell
    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 280
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let section = indexPath.section
        var selected : Hotel!
        if section < self.hotels.count {
            selected = self.hotels[section][indexPath.row]
        } else {
            selected = self.packages[indexPath.row]
        }
        self.tableView.deselectRow(at: indexPath, animated: true)
        
        let controller = HotelViewController(hotel: selected)
        self.navigationController?.pushViewController(controller, animated: true)
    }
    
    
    func tableView(_ tableView: UITableView, viewForHeaderInSection section: Int) -> UIView? {
        if section < self.hotels.count {
            if self.hotels[section].count == 0 {
                return nil
            }
        } else {
            if self.packages.count == 0 {
                return nil
            }
        }
        
        var text = ""
        switch section {
        case 4:
            text = "1 Estrela"
        case 3:
            text =  "2 Estrelas"
        case 2:
            text =  "3 Estrelas"
        case 1:
            text = "4 Estrelas"
        case 0:
            text = "5 Estrelas"
        default:
            text = "Pacotes"
        }
        let label = UILabel(frame: CGRect(x: 0, y: 0, width: screenWidth, height: 44))
        label.text = text
        label.backgroundColor = red
        label.textColor = UIColor.white
        label.font = UIFont(name: "Avenir-Black", size: 20)
        label.padding = UIEdgeInsets(top: 10, left: 20, bottom: 10, right: 0)
        return label
    }
    
    func tableView(_ tableView: UITableView, heightForHeaderInSection section: Int) -> CGFloat {
        if section < self.hotels.count {
            if self.hotels[section].count == 0 {
                return 0
            }
        } else {
            if self.packages.count == 0 {
                return 0
            }
        }
        return 44
    }
    
    // MARK: Search bar delegate
    
    func searchBarCancelButtonClicked(_ searchBar: UISearchBar) {
        self.searchTerm = nil
        self.loadData()
    }
    
    func searchBarSearchButtonClicked(_ searchBar: UISearchBar) {
        if (searchBar.text != "") {
            self.searchTerm = searchBar.text
        }
        self.loadData()
    }
    
    
}


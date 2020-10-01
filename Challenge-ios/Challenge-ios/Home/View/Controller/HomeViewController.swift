//
//  HomeViewController.swift
//  Challenge-ios
//
//  Created by Andre Dias on 28/09/20.
//  Copyright © 2020 Andre Dias. All rights reserved.
//

import Foundation
import UIKit

class HomeViewController: UIViewController {
    
    @IBOutlet weak var backgroundViewWithLogo: UIView!
    @IBOutlet weak var tableView: UITableView!
    var hotels: [HotelsResults] = [HotelsResults]()
    var indexPathRow: Int?
    var homeViewModel: HomeViewModel!
    var page: Int = Constants.page
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.homeViewModel = HomeViewModel()
        self.homeViewModel.homeViewModelDelegate = self
        
        self.homeViewModel.getBaseHotels(page: self.page)
        
        self.setupTableView()
        self.setupView()
        
    
    }
    
    func setupTableView() {
        self.tableView.delegate = self
        self.tableView.dataSource = self
    }
    
    func setupView() {
        self.tableView.backgroundColor = Colors.backgroundGray
     
        self.tableView.sectionHeaderHeight = 40
        tableView.contentInset = UIEdgeInsets(top: 0, left: 0, bottom: 330, right: 0)
        self.tableView.isHidden = true
        self.view.backgroundColor = Colors.hurbBackgroundBlue
         self.backgroundViewWithLogo.backgroundColor = Colors.hurbBackgroundBlue
       self.tableView.reloadData()
       self.tableView.layoutIfNeeded()
   }
    
}




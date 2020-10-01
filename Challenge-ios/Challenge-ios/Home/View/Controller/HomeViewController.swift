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
    
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var activityIndicator: UIActivityIndicatorView!
    @IBOutlet weak var bottomActivityIndicator: UIActivityIndicatorView!
    var hotels: [HotelsResults] = [HotelsResults]()
    var indexPathRow: Int?
    var homeViewModel: HomeViewModel!
    var page: Int = 1
    
    
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
       self.activityIndicator.startAnimating()
        self.bottomActivityIndicator.isHidden = true
 
        self.tableView.backgroundColor = UIColor(red: 245.0/255.0, green: 245.0/255.0, blue: 245.0/255.0, alpha: 1.0)
     
        self.tableView.sectionHeaderHeight = 40
        tableView.contentInset = UIEdgeInsets(top: 0, left: 0, bottom: 250, right: 0)
        self.tableView.isHidden = true
       self.tableView.reloadData()
       self.tableView.layoutIfNeeded()
   }
    
    func getMoreHotels() {
        self.page = self.page + 1
        self.bottomActivityIndicator.isHidden = false
        self.bottomActivityIndicator.startAnimating()
        self.homeViewModel.getBaseHotels(page: self.page)
        self.tableView.reloadData()
    }
    
    
}


extension HomeViewController:  UITableViewDataSource {
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return self.homeViewModel.numberOfSections
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        
        switch section {
            case 0:
                return self.homeViewModel.numberOfFiveStarsHotel()
            case 1:
                return self.homeViewModel.numberOfFourStarsHotel()
            case 2:
                return self.homeViewModel.numberOfThreeStarsHotel()
            default:
                return self.homeViewModel.numberOfCells()
        }
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        var hotels = [HotelsResults]()
        switch indexPath.section {
            case 0:
                hotels = self.homeViewModel.getFiveStarsHotel()
            case 1:
                hotels = self.homeViewModel.getFourStarsHotel()
            case 2:
                hotels = self.homeViewModel.getThreeStarsHotel()
            default:
                hotels = self.homeViewModel.returnAllHotels()
        }
 
        let cell = tableView.dequeueReusableCell(withIdentifier: "HomeTableViewCell", for: indexPath) as! HomeTableViewCell
        cell.homeTableViewCellViewModel = HomeTableCellViewModel(hotels[indexPath.row])
        return cell
            
           
//            if indexPath.row == (hotels.count - 1) {
//                self.getMoreHotels()
//            }
    }
    
    func tableView(_ tableView: UITableView, viewForHeaderInSection section: Int) -> UIView? {
        
        let headerView = UIView.init(frame: CGRect.init(x: 0, y: 0, width: tableView.frame.width, height: 40))
        headerView.backgroundColor = .white

        let label = UILabel()
        label.frame = CGRect.init(x: 5, y: 5, width: headerView.frame.width-10, height: headerView.frame.height-10)
       if section == 0 {
            label.text = "Hoteis 5 estrelas"
        } else if section == 1 {
            label.text = "Hoteis 4 estrelas"
        } else if section == 2 {
            label.text = "Hoteis 3 estrelas"
        }

        headerView.addSubview(label)

        return headerView

    }
    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return 280.0
    }
}


extension HomeViewController: UITableViewDelegate {
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
//        let storyboard = UIStoryboard(name: "Main", bundle: nil)
//        let gameDetail = storyboard.instantiateViewController(withIdentifier: "GameDetailViewController") as!
//        GameDetailViewController
//        let cellGames = self.topGamesViewModel?.returnGames() ?? [Game]()
//        gameDetail.setupVC(game: cellGames[indexPath.row])
//        self.navigationController?.show(gameDetail, sender: self)
//
//        print("clicou")
    }
    
}


extension HomeViewController: HotelsViewModelDelegate {
    
    func getCompletionHotels(_ hotels: [HotelsResults]) {
        self.hotels = hotels
    }
    
    func didFinishFetchingHotels() {
        self.tableView.reloadData()
        self.activityIndicator.stopAnimating()
        self.tableView.isHidden = false
        self.bottomActivityIndicator.stopAnimating()
        self.bottomActivityIndicator.isHidden = true
    }
    
    
    
}

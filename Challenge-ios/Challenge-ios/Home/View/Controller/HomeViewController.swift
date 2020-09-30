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
    
    @IBOutlet weak var collectionView: UICollectionView!
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
        
        self.setupCollectionView()
        self.setupView()
        
    
    }
    
    func setupCollectionView() {
        self.collectionView.delegate = self
        self.collectionView.dataSource = self
    }
    
    func setupView() {
       self.activityIndicator.startAnimating()
    self.bottomActivityIndicator.isHidden = true
       self.collectionView.isHidden = true
       self.collectionView.reloadData()
       self.collectionView.layoutIfNeeded()
   }
    
    func getMoreHotels() {
        self.page = self.page + 1
        self.bottomActivityIndicator.isHidden = false
        self.bottomActivityIndicator.startAnimating()
        self.homeViewModel.getBaseHotels(page: self.page)
        self.collectionView.reloadData()
    }
    
    
}


extension HomeViewController:  UICollectionViewDataSource {
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        return self.homeViewModel.numberOfCells
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: "HomeCollectionViewCell", for: indexPath) as! HomeCollectionViewCell
        if let hotels = self.homeViewModel?.returnHotels() {
            cell.homeCollectionViewCellViewModel = HomeCollectionCellViewModel(hotels[indexPath.row])
            
           
            if indexPath.row == (hotels.count - 1) {
                self.getMoreHotels()
            }
            
        }
        
        return cell
    }
    
    func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
//        let storyboard = UIStoryboard(name: "Main", bundle: nil)
//        let gameDetail = storyboard.instantiateViewController(withIdentifier: "GameDetailViewController") as!
//        GameDetailViewController
//        let cellGames = self.topGamesViewModel?.returnGames() ?? [Game]()
//        gameDetail.setupVC(game: cellGames[indexPath.row])
//        self.navigationController?.show(gameDetail, sender: self)
        
        print("clicou")
    }
}


extension HomeViewController: UICollectionViewDelegate, UICollectionViewDelegateFlowLayout {
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        return CGSize(width:(collectionView.frame.width/2), height: 250)
    }
}


extension HomeViewController: HotelsViewModelDelegate {
    
    func getCompletionHotels(_ hotels: [HotelsResults]) {
        self.hotels = hotels
    }
    
    func didFinishFetchingHotels() {
        self.collectionView.reloadData()
        self.activityIndicator.stopAnimating()
        self.collectionView.isHidden = false
        self.bottomActivityIndicator.stopAnimating()
        self.bottomActivityIndicator.isHidden = true
    }
    
    
    
}

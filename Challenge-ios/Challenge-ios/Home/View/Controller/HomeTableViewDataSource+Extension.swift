//
//  HomeTableViewDataSource+Extension.swift
//  Challenge-ios
//
//  Created by Andre Dias on 01/10/20.
//  Copyright © 2020 Andre Dias. All rights reserved.
//

import Foundation
import UIKit


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
 
        let cell = tableView.dequeueReusableCell(withIdentifier: Constants.cellIdentifier, for: indexPath) as! HomeTableViewCell
        cell.homeTableViewCellViewModel = HomeTableCellViewModel(hotels[indexPath.row])
        return cell
        
    }

    
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        return CGFloat(Constants.cellHeight)
    }
    
    func tableView(_ tableView: UITableView, willDisplay cell: UITableViewCell, forRowAt indexPath: IndexPath) {
        let rotationTransform = CATransform3DTranslate(CATransform3DIdentity, 0, -10, 0)
        cell.layer.transform = rotationTransform
        cell.alpha = 0
        UIView.animate(withDuration: 0.75) {
            cell.layer.transform = CATransform3DIdentity
            cell.alpha = 1.0
        }
           
    }
}

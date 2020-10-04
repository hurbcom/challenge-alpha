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
    
    // Configura sections de acordo com primeiro numero de estrelas fixado do maior para o menor, e por ultimo os pacotes.
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        
        switch section {
            case 0:
                return self.homeViewModel.numberOfFiveStarsHotel()
            case 1:
                return self.homeViewModel.numberOfFourStarsHotel()
            case 2:
                return self.homeViewModel.numberOfThreeStarsHotel()
            case 3:
                return self.homeViewModel.numberOfPackages()
            default:
                return self.homeViewModel.numberOfHotelsCells()
        }
    }
    
    //Seguindo a lógica anterior configura o tipo de ceula de acordo com a section a terceira setada para os pacotes
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let hotelCell = tableView.dequeueReusableCell(withIdentifier: Constants.cellHotelIdentifier) as! HomeHotelsTableViewCell
        let packageCell = tableView.dequeueReusableCell(withIdentifier: Constants.cellPackageIdentifier) as! HomePackageTableViewCell
        hotelCell.isAccessibilityElement = true
        hotelCell.accessibilityIdentifier = String(format: "HotelTableViewCellAtIndexPath:_%d_%d",
        indexPath.section, indexPath.row)
        packageCell.isAccessibilityElement = true
        packageCell.accessibilityIdentifier = String(format: "PackageTableViewCellAtIndexPath:_%d_%d",
        indexPath.section, indexPath.row)
        
        var hotels = [HotelsResults]()
        var packages = [PackageResults]()
        switch indexPath.section {
            case 0:
                hotels = self.homeViewModel.getFiveStarsHotel()
                hotelCell.homeHotelTableCellViewModel = HomeHotelTableCellViewModel(hotels[indexPath.row])
                return hotelCell
            case 1:
                hotels = self.homeViewModel.getFourStarsHotel()
                hotelCell.homeHotelTableCellViewModel = HomeHotelTableCellViewModel(hotels[indexPath.row])
                return hotelCell
            case 2:
                hotels = self.homeViewModel.getThreeStarsHotel()
                hotelCell.homeHotelTableCellViewModel = HomeHotelTableCellViewModel(hotels[indexPath.row])
                return hotelCell
            case 3:
                packages = self.homeViewModel.getPackages()
                packageCell.homePackagesTableCellViewModel = HomePackagesTableCellViewModel(packages[indexPath.row])
                return packageCell
            default:
                hotels = self.homeViewModel.returnAllHotels()
                hotelCell.homeHotelTableCellViewModel = HomeHotelTableCellViewModel(hotels[indexPath.row])
                return hotelCell
        }
    }

    //Celula de pacote, precisa ser de um tamanho maior por ter mais informacoes na celula
    func tableView(_ tableView: UITableView, heightForRowAt indexPath: IndexPath) -> CGFloat {
        switch indexPath.section {
            case 3:
                return Constants.cellPackageHeight
            default:
                return Constants.cellHotelHeight
        }
    }
    
    // Animacao na celula fazendo ela surgir de cima de maneira suave.
    func tableView(_ tableView: UITableView, willDisplay cell: UITableViewCell, forRowAt indexPath: IndexPath) {
        let rotationTransform = CATransform3DTranslate(CATransform3DIdentity, 0, -10, 0)
        cell.layer.transform = rotationTransform
        cell.alpha = 0
        UIView.animate(withDuration: 0.50) {
            cell.layer.transform = CATransform3DIdentity
            cell.alpha = 1.0
        }
           
    }
}

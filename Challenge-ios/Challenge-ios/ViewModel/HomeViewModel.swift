//
//  HomeViewModel.swift
//  Challenge-ios
//
//  Created by Andre Dias on 28/09/20.
//  Copyright © 2020 Andre Dias. All rights reserved.
//

import Foundation

// MARK: Protocolo de delegate
protocol HotelsViewModelDelegate {
    func didFinishFetchingHotels()
}


class HomeViewModel {
    
    
    // MARK: Váriaveis
    private var hotelsArray = [HotelsResults]()
    private var packagesArray = [PackageResults]()
    let numberOfSections = Constants.numberOfSections
    var homeViewModelDelegate: HotelsViewModelDelegate?
    
    
    // MARK: Chamada API de hoteis
    func getBaseHotels(page: Int) {
        DispatchQueue.main.async {
            APIRequest.shared.getBaseHotels(atPage: page) { (hotelsResult, packagesResult) in
                
                if hotelsResult.count > 0 {
                    for hotel in hotelsResult {
                        self.hotelsArray.append(hotel)
                    }
                    
                }
                
                if packagesResult.count > 0 {
                    for package in packagesResult {
                        self.packagesArray.append(package)
                    }
                }
                
                self.homeViewModelDelegate?.didFinishFetchingHotels()
            }
        }
    }
    
    //MARK: Métodos com a logica para retornar Hoteis de acordo com o numero de estrelas
    
    func getFiveStarsHotel() -> [HotelsResults] {
        var fiveStarsArray = [HotelsResults]()
        for hotel in self.hotelsArray {
            if hotel.stars == 5 {
                fiveStarsArray.append(hotel)
            }
            
        }
        return fiveStarsArray
    }
    
    func getFourStarsHotel() -> [HotelsResults] {
        var fourStarsArray = [HotelsResults]()
        for hotel in self.hotelsArray {
            if hotel.stars == 4 {
                fourStarsArray.append(hotel)
            }
        }
        return fourStarsArray
    }
    
    func getThreeStarsHotel() -> [HotelsResults] {
        var threeStarsArray = [HotelsResults]()
        for hotel in self.hotelsArray {
            if hotel.stars == 3 {
                threeStarsArray.append(hotel)
            }
        }
        return threeStarsArray
    }
    
    //MARK: Métodos com que retornar os Pacotes
     
     func getPackages() -> [PackageResults] {
        if self.packagesArray.count > 0 {
             return self.packagesArray
        } else {
            return [PackageResults]()
        }
        
     }
    
    // MARK: Retorna a quantidade de pacotes
    
    func numberOfPackages() -> Int {
         return self.packagesArray.count
    }
    
    //MARK: Retorna a quantidade de hoteis de acordo com o numero de estrelas
    
    func numberOfFiveStarsHotel() -> Int {
        return self.getFiveStarsHotel().count
    }
     
    func numberOfFourStarsHotel() -> Int {
        return self.getFourStarsHotel().count
    }
     
    func numberOfThreeStarsHotel() -> Int {
        return self.getThreeStarsHotel().count
    }
    
    func numberOfHotelsCells() -> Int {
        return self.hotelsArray.count
    }
    
    //MARK: Retorna todos os hoteis de acordo com o numero de estrelas
    
    func returnAllHotels() -> [HotelsResults] {
        return self.hotelsArray
    }
    
              
}

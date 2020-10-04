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
    func errorMessage(error: String)
}


class HomeViewModel {
    
    // MARK: Váriaveis
    private var hotelsArray = [HotelsResults]()
    private var packagesArray = [PackageResults]()
    let numberOfSections = Constants.numberOfSections
    var homeViewModelDelegate: HotelsViewModelDelegate?
    var apiRequest: APIServiceProtocol?
    
    // MARK: Métodos
    // Chamada API de hoteis
    func getHotelsAndPackages(request: APIServiceProtocol) {
        request.getHotelsAndPackages { (hotelsResult, packagesResult, error) in
            
            if error == nil {
                if let hotels = hotelsResult, let packages = packagesResult {
                    if hotels.count > 0 {
                        for hotel in hotels {
                            self.hotelsArray.append(hotel)
                        }
                    }
                    if packages.count > 0 {
                        for package in packages {
                            self.packagesArray.append(package)
                        }
                    }
                }
                self.homeViewModelDelegate?.didFinishFetchingHotels()
            } else {
                if let error = error {
                    self.homeViewModelDelegate?.errorMessage(error: error.description)
                }
            }
        }
    }
    
    // Métodos com a logica para retornar Hoteis de acordo com o numero de estrelas
    
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
    
    // Métodos com que retornar os Pacotes
     
     func getPackages() -> [PackageResults] {
        if self.packagesArray.count > 0 {
             return self.packagesArray
        } else {
            return [PackageResults]()
        }
        
     }
    
    // Retorna a quantidade de pacotes
    
    func numberOfPackages() -> Int {
         return self.packagesArray.count
    }
    
    //MRetorna a quantidade de hoteis de acordo com o numero de estrelas
    
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

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
    func getCompletionHotels(_ hotels: [HotelsResults])
    func didFinishFetchingHotels()
}


class HomeViewModel {
    
    
    // MARK: Váriaveis
    private var hotelsArray = [HotelsResults]()
    let numberOfSections = 3
    var homeViewModelDelegate: HotelsViewModelDelegate?
    
    
    // MARK: Chamada API de hoteis
    func getBaseHotels(page: Int) {
        DispatchQueue.main.async {
            APIRequest.shared.getBaseHotels(atPage: page) { (hotelsResult) in
                
                if hotelsResult.count > 0 {
                    for hotel in hotelsResult {
                        self.hotelsArray.append(hotel)
                    }
                    self.homeViewModelDelegate?.getCompletionHotels(self.hotelsArray)
                }
                self.homeViewModelDelegate?.didFinishFetchingHotels()
            }
        }
    }
    
    //MARK: Métodos com a logica para retornar Hoteis de acordo com o numero de estrelas
    
    private func numberOfFiveStarsHotelLogic() -> [HotelsResults] {
        var fiveStarsArray = [HotelsResults]()
        for hotel in self.hotelsArray {
            if let star = hotel.stars {
                if star == 5 {
                    fiveStarsArray.append(hotel)
                }
            }
        }
           
        return fiveStarsArray
    }
    
    private func numberOfFourStarsHotelLogic() -> [HotelsResults] {
        var fourStarsArray = [HotelsResults]()
        for hotel in self.hotelsArray {
            if let star = hotel.stars {
                if star == 4 {
                    fourStarsArray.append(hotel)
                }
            }
        }
           
        return fourStarsArray
    }
    
    private func numberOfThreeStarsHotelLogic() -> [HotelsResults] {
        var threeStarsArray = [HotelsResults]()
        for hotel in self.hotelsArray {
            if let star = hotel.stars {
                if star == 3 {
                    threeStarsArray.append(hotel)
                }
            }
        }
           
        return threeStarsArray
    }
    
    //MARK: Retorna Hoteis de acordo com o numero de estrelas
    
    func getFiveStarsHotel() -> [HotelsResults] {
        return self.numberOfFiveStarsHotelLogic()
    }
    
    func getFourStarsHotel() -> [HotelsResults] {
       return self.numberOfFourStarsHotelLogic()
    }
    
    func getThreeStarsHotel() -> [HotelsResults] {
         return self.numberOfThreeStarsHotelLogic()
    }
    
    //MARK: Retorna a quantidade de hoteis de acordo com o numero de estrelas
    
    func numberOfFiveStarsHotel() -> Int {
        return self.numberOfFiveStarsHotelLogic().count
     }
     
     func numberOfFourStarsHotel() -> Int {
        return self.numberOfFourStarsHotelLogic().count
     }
     
     func numberOfThreeStarsHotel() -> Int {
          return self.numberOfThreeStarsHotelLogic().count
     }
    
    func numberOfCells() -> Int {
        return self.hotelsArray.count
    }
    
    //MARK: Retorna todos os hoteis de acordo com o numero de estrelas
    
    func returnAllHotels() -> [HotelsResults] {
        return self.hotelsArray
    }
    
    
}


//    private func getNumberOfStars(hotels: [HotelsResults]) -> [Int] {
//        var starsArray = [Int]()
//        for hotel in hotels {
//            if let stars = hotel.stars {
//                if starsArray.contains(stars) == false {
//                    starsArray.append(stars)
//                }
//            }
//        }
//        let sortedArray = Array(starsArray.sorted().reversed())
//        return sortedArray
//    }

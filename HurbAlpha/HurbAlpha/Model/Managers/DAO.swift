//
//  DAO.swift
//  HurbAlpha
//
//  Created by Julia Rocha on 04/09/19.
//  Copyright © 2019 Julia Rocha. All rights reserved.
//

import Foundation
import UIKit

protocol DAORequester {
    func finishedLoading()
    func finishedLoading(with Error:HotelReadingError)
}

enum HotelReadingError:Error {
    case badURL
    case badData
    case DataIsNotHotelModel
}


class DAO {
    
    
    
    static let instance = DAO()
    private init() {}
    
    
    var loadedHotels:[Result] = []
    var favorites:[Result] = []
    var hotelsByStars:[(key: Int, value: [Result])] {
        var hotelsByStars:[Int:[Result]] = [:]
        
        for hotel in loadedHotels {
            let stars = hotel.stars ?? Int.max
            hotelsByStars[stars] = hotelsByStars[stars] ?? []
            
            hotelsByStars[stars]?.append(hotel)
        }
        return hotelsByStars.sorted { $0.key < $1.key }

    }
    
    func jsonDataRequest (page:Int, requester: DAORequester) {
        DispatchQueue.main.async {
            let urlString = "https://www.hurb.com/search/api?q=buzios&page=\(page)"
            guard let url = URL(string: urlString) else {
                debugPrint("error in url", urlString)
                requester.finishedLoading(with: .badURL)
                return
            }
            guard let data = try? Data(contentsOf: url) else {
                debugPrint("error reading data from", urlString)
                requester.finishedLoading(with: .badData)
                return
            }
            guard let hotel = try? JSONDecoder().decode(Hotel.self, from: data) else {
                debugPrint("error converting data from", urlString, "to Hotel model")
                requester.finishedLoading(with: .DataIsNotHotelModel)
                return
            }
            self.loadedHotels = hotel.results
            requester.finishedLoading()
        }
    }
    
    func manageFavorite(at item:Result) {
        if favorites.contains(where: { $0.id == item.id }) {
            favorites.removeAll(where: { $0.id == item.id })
        } else {
            favorites.append(item)
        }
    }
    
}


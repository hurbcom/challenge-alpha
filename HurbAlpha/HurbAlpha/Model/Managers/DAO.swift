//
//  DAO.swift
//  HurbAlpha
//
//  Created by Julia Rocha on 04/09/19.
//  Copyright © 2019 Julia Rocha. All rights reserved.
//

import Foundation
import UIKit

// MARK: - Protocol Requester Declaration

protocol DAORequester {
    
    
    func finishedLoading()
    func finishedLoading(with Error:HotelReadingError)
}


// MARK: - Enum Error Handler Declaration

enum HotelReadingError:Error {
    case badURL
    case badData
    case DataIsNotHotelModel
}

// MARK: - Declaration

class DAO {
    
    // MARK: - Instance Initialization
    
    static let instance = DAO()
    private init() {}
    
    // Result of the JSON retriever
    var hotel: Hotel?
    
    // The array containing only the hotels from the URL query
    var loadedHotels:[Result] {
        return hotel?.results ?? []
    }
    
    // The array containing the hotels that were favorited
    var favorites:[Result] = []
    
    // The dictionary sorted by hotel stars
    var hotelsByStars:[(key: Int, value: [Result])] {
        var hotelsByStars:[Int:[Result]] = [:]
        
        for hotel in loadedHotels {
            let stars = hotel.stars ?? Int.max
            hotelsByStars[stars] = hotelsByStars[stars] ?? []
            
            hotelsByStars[stars]?.append(hotel)
        }
        return hotelsByStars.sorted { $0.key < $1.key }

    }
    
    /**
     Convert JSON URL into data and tries to convert data into Hotel model.
     - Parameters:
        - page: The API page to convert.
        - requester: The class that is requesting the convertion.
     */
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
            self.hotel = hotel
            requester.finishedLoading()
        }
    }
    
    /**
     Manages item to Favorites array: if the array already contains the item, it removes, if it doesen't, it appends.
     - Parameters:
        - item: The item to be removed or added.
     */
    func manageFavorite(at item:Result) {
        if favorites.contains(where: { $0.id == item.id }) {
            favorites.removeAll(where: { $0.id == item.id })
        } else {
            favorites.append(item)
        }
    }
    
}


//
//  HotelsSorter.swift
//  desafio-hurb-ios
//
//  Created by Vinícius Barcelos on 22/10/19.
//  Copyright © 2019 Vinícius Barcelos. All rights reserved.
//

import Foundation

class HotelSorter {
    
    static func sortedHotels(from hotels: [Hotel]) -> [[Hotel]] {
        
        var starSortedHotels: [[Hotel]] = []
        
        let fiveStarsHotels = hotels.filter {$0.stars == 5}
        let fourStarsHotels = hotels.filter {$0.stars == 4}
        let threeStarsHotels = hotels.filter {$0.stars == 3}
        let twoStarsHotels = hotels.filter {$0.stars == 2}
        let oneStarHotels = hotels.filter {$0.stars == 1}
        let packages = hotels.filter {$0.stars == nil}
        
        starSortedHotels.append(fiveStarsHotels)
        starSortedHotels.append(fourStarsHotels)
        starSortedHotels.append(threeStarsHotels)
        starSortedHotels.append(twoStarsHotels)
        starSortedHotels.append(oneStarHotels)
        starSortedHotels.append(packages)
        
        return starSortedHotels
    }
    
}

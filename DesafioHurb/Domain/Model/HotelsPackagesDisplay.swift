//
//  HotelsPackagesDisplay.swift
//  DesafioHurb
//
//  Created by Edson Aparecido Guido on 13/09/20.
//  Copyright © 2020 Edson Aparecido Guido. All rights reserved.
//

import Foundation

struct HotelsPackagesDisplay: Equatable {
    let count: Int
    let packages: [HotelDisplay]
    let hotelsOneStar: [HotelDisplay]
    let hotelsTwoStar: [HotelDisplay]
    let hotelsThreeStar: [HotelDisplay]
    let hotelsFourStar: [HotelDisplay]
    let hotelsFiveStar: [HotelDisplay]
    
    init(data: [Hotel]) {
        self.count = data.count
        self.packages = data.filter { ($0.isPackage ?? false) }.map(HotelDisplay.init)
        let hotels = data.filter { !($0.isPackage ?? false) }
        self.hotelsOneStar = hotels.filter { ($0.stars ?? 1 == 1) }.map(HotelDisplay.init)
        self.hotelsTwoStar = hotels.filter { ($0.stars ?? 1 == 2) }.map(HotelDisplay.init)
        self.hotelsThreeStar = hotels.filter { ($0.stars ?? 1 == 3) }.map(HotelDisplay.init)
        self.hotelsFourStar = hotels.filter { ($0.stars ?? 1 == 4) }.map(HotelDisplay.init)
        self.hotelsFiveStar = hotels.filter { ($0.stars ?? 1 == 5) }.map(HotelDisplay.init)
    }
}

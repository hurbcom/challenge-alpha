//
//  ServiceURL.swift
//  HotelUrbanoChallenge
//
//  Created by maciosdev on 22/07/2018.
//  Copyright © 2018 Ronilson. All rights reserved.
//

import Foundation

enum ServiceURL {
    case hotels
    
    var value: String {
        switch self {
        case .hotels: return "https://search.hotelurbano.com/api?q=Rio%20de%20Janeiro"
        }
    }
}

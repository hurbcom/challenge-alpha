//
//  FeedSectionType.swift
//  HurbAlpha
//
//  Created by Rodrigo Bukowitz on 06/11/19.
//  Copyright © 2019 Rodrigo Bukowitz. All rights reserved.
//

import Foundation

/// Enum to indentify all the cellTypes
enum Identifiers: String {
    case Hotel = "hotelCell"
    case Package = "packageCell"
}

/// Enum to indentify all the section Types
enum FeedSectionType {
    case Hotel(hotels: [HurbOffers])
    case Package(packages: [HurbOffers])
    
    var identifier: String {
        switch self {
        case .Hotel: return Identifiers.Hotel.rawValue
        case .Package: return Identifiers.Package.rawValue
        }
    }
}

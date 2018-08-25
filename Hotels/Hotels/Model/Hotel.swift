//
//  Hotel.swift
//  Hotels
//
//  Created by Adolfho Athyla on 24/08/2018.
//  Copyright © 2018 a7hyla. All rights reserved.
//

import UIKit
import EVReflection

class Hotel: EVObject {
    var id: String?
    var isHotel = false
    var isPackage = false
    var name: String?
    var image: String?
    var stars: NSNumber?
    var address: Address?
    var price: Price?
    var amenities = [Amenity]()
    
    var adType: AdType {
        get {
            return isHotel ? .Hotel : (isPackage ? .Package : .Hotel)
        }
    }
}

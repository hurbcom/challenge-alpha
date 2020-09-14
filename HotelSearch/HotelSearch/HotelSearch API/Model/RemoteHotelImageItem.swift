//
//  RemoteHotelImageItem.swift
//  HotelSearch
//
//  Created by Tulio Parreiras on 14/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import Foundation

struct RemoteHotelImageItem: Decodable {
    let description: String
    let url: URL
    
    var item: HotelImage {
        return HotelImage(description: description, url: url)
    }
}

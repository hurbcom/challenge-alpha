//
//  RemoteHotelItem.swift
//  HotelSearch
//
//  Created by Tulio Parreiras on 14/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import Foundation

struct RemoteHotelItem: Decodable {
    let amenities: [RemoteAmenityItem]
    let category: String
    let description: String
    let gallery: [RemoteHotelImageItem]
    let id: Int
    let image: URL
    let isHotel: Bool
    let name: String
    let price: RemoteHotelPriceItem
    let quantityDescriptors: RemoteQuantityDescriptorItem
    let smallDescription: String
    let star: Int
    let tags: [String]
    let url: URL
    
    var item: Hotel {
        return Hotel(amenities: amenities.map { $0.item },
                     category: category,
                     description: description,
                     gallery: gallery.compactMap { $0.item },
                     id: id,
                     image: image,
                     isHotel: isHotel,
                     name: name,
                     price: price.item,
                     quantityDescriptors: quantityDescriptors.item,
                     smallDescription: smallDescription,
                     star: star,
                     tags: tags,
                     url: url)
    }
}

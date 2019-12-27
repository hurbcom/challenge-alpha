//
//  DataContants.swift
//  hurb-challenge-alpha
//
//  Created by Hannah  on 26/12/2019.
//  Copyright © 2019 Hannah . All rights reserved.
//

import Foundation

///class responsible for providing static data for use in preview
class DataContants {
    static let sharedInstance = DataContants()
    
    let urlImage = "https://novo-hu.s3.amazonaws.com/reservas/ota/prod/hotel/463/hotel-ville-la-plage-001_24_20170328174038.jpg"
    
    let hotelModelTest = Accommodation(id: "123",
       isHotel: true, isPackage: false, smallDescription: "asklsf as s fsa saffsa asfsaf fasasf ",
       description: "dfdf asa ffa fsafs asf ",
       image: "https://novo-hu.s3.amazonaws.com/reservas/ota/prod/hotel/463/hotel-ville-la-plage-001_24_20170328174038.jpg",
       name: "kskskd", stars: 2, amenities: [Amenities(name: "Sala de tv",
       category: "Áreas comuns")], huFreeCancellation: true,
       price: Price(amount: 393.83, oldPrice: 393.83, currency: "BRL"), address: Addres(
            zipcode: "28950000",
            street: "Rua: 01,Quadra: B, Lote: 39.  Joao Fernandes - Búzios - RJ",
            city: "Armação dos Búzios",
            state: "Rio de Janeiro"),
       gallery: [Gallery(url: "https://novo-hu.s3.amazonaws.com/reservas/ota/prod/hotel/463/hotel-ville-la-plage-001_24_20170328174038.jpg")])
}

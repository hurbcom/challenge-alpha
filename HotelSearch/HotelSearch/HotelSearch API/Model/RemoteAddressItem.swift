//
//  RemoteAddressItem.swift
//  HotelSearch
//
//  Created by Tulio Parreiras on 15/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import Foundation

public struct RemoteAddressItem: Decodable {
    public let city: String?
    public let country: String?
    public let state: String?
    public let street: String?
    public let zipcode: String?
    
    var item: Address {
        return Address(city: city, country: country, state: state, street: street, zipcode: zipcode)
    }
}


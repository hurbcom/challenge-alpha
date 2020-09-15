//
//  Address.swift
//  HotelSearch
//
//  Created by Tulio Parreiras on 15/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import Foundation

public struct Address: Hashable {
    public let city: String?
    public let country: String?
    public let state: String?
    public let street: String?
    public let zipcode: String?
    
    public init(city: String?, country: String?, state: String?, street: String?, zipcode: String?) {
        self.city = city
        self.country = country
        self.state = state
        self.street = street
        self.zipcode = zipcode
    }
}

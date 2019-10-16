//
//  AddressModel.swift
//  GPSurb
//
//  Created by Gilson Santos on 16/10/19.
//  Copyright © 2019 Gilson Santos. All rights reserved.
//

import Foundation

struct AddressModel: Codable {
    let city: String?
    let country: String?
    let id_atlas_city: String?
    let id_atlas_country: String?
    let id_atlas_neighborhood: String?
    let id_atlas_state: String?
    let id_city: Int?
    let id_country: Int?
    let id_state: Int?
    let state: String?
    let street: String?
    let zipcode: String?
    let geoLocation: GeoLocationModel?
}

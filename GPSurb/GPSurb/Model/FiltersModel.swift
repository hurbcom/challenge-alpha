//
//  FiltersModel.swift
//  GPSurb
//
//  Created by Gilson Santos on 16/10/19.
//  Copyright © 2019 Gilson Santos. All rights reserved.
//

import Foundation

struct FiltersModel: Codable {
    let amenities: [AmenitiesModel]?
    let countries: [CountriesModel]?
    let cities: [CitiesModel]?
    let prices: [PricesModel]?
    let priceInterval: PriceIntervalModel?
    let productType: [ProductTypeModel]?
    let rooms: [RoomsModel]?
    let stars: [StarsModel]?
    let states: [StatesModel]?
}

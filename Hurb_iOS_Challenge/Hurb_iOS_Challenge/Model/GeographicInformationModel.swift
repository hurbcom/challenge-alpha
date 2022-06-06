//
//  GeographicInformationModel.swift
//  Hurb_iOS_Challenge
//
//  Created by Gáudio Ney on 22/05/22.
//

import Foundation

// MARK: - CityElement
struct CityElement: Codable {
    let term, filter: String?
    let count: Int?
}

// MARK: - GeoLocation
struct GeoLocation: Codable {
    let lat, lon: Double?
}



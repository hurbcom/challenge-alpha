//
//  Hotel.swift
//  Hurb_iOS_Challenge
//
//  Created by Gáudio Ney on 22/05/22.
//

import Foundation

// MARK: - HotelResponse
struct HotelReponse: Codable {
    let filters: Filters?
    let results: [HotelResult]?
}

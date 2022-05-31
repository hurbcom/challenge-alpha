//
//  Price.swift
//  Hurb_iOS_Challenge
//
//  Created by Gáudio Ney on 22/05/22.
//

import Foundation

// MARK: - PriceInterval
struct PriceInterval: Codable {
    let min, max: Int?
    let filterPattern: String?
}

// MARK: - PriceElement
struct PriceElement: Codable {
    let min, maxExclusive: Int?
    let filter: String?
    let count: Int?
}

//
//  Hotel.swift
//  HurbAlpha
//
//  Created by Julia Rocha on 04/09/19.
//  Copyright © 2019 Julia Rocha. All rights reserved.
//

import Foundation

// MARK: - Hotel Typealias

typealias Hotels = [Hotel]

// MARK: - Hotel Declaration

class Hotel: Codable {
    let filters: Filters
    let results: [Result]
    let pagination: Pagination
    
    // MARK: - Initialization
    
    init(filters: Filters, results: [Result], pagination: Pagination) {
        self.filters = filters
        self.results = results
        self.pagination = pagination
    }
}

//
//  Pagination.swift
//  HurbAlpha
//
//  Created by Julia Rocha on 04/09/19.
//  Copyright © 2019 Julia Rocha. All rights reserved.
//

import Foundation
// MARK: - Pagination
class Pagination: Codable {
    let count: Int
    let firstPage, nextPage: String
    let previousPage: String?
    let lastPage: String
    
    init(count: Int, firstPage: String, nextPage: String, previousPage: String?, lastPage: String) {
        self.count = count
        self.firstPage = firstPage
        self.nextPage = nextPage
        self.previousPage = previousPage
        self.lastPage = lastPage
    }
}

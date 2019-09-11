//
//  Pagination.swift
//  HurbAlpha
//
//  Created by Julia Rocha on 04/09/19.
//  Copyright © 2019 Julia Rocha. All rights reserved.
//

import Foundation

// MARK: - Declaration

struct Pagination: Codable {
    let count: Int
    let firstPage, nextPage: String
    let previousPage: String?
    let lastPage: String
}

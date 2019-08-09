//
//  Pagination.swift
//  Hurb
//
//  Created by Alexandre Papanis on 08/08/19.
//  Copyright © 2019 Papanis. All rights reserved.
//

import Foundation

struct Pagination: Codable {
    let count: Int?
    let firstPage: String?
    let nextPage: String?
    let previousPage: String?
    let lastPage: String?
    
}

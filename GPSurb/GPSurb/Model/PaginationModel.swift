//
//  PaginationModel.swift
//  GPSurb
//
//  Created by Gilson Santos on 16/10/19.
//  Copyright © 2019 Gilson Santos. All rights reserved.
//

import Foundation

struct PaginationModel: Codable {
    let count: Int?
    let firstPage: String?
    let nextPage: String?
    let previousPage: String?
    let lastPage: String?
}

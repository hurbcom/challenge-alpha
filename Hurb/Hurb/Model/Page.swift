//
//  Page.swift
//  Hurb
//
//  Created by Alexandre Papanis on 08/08/19.
//  Copyright © 2019 Papanis. All rights reserved.
//

import Foundation

struct Page: Codable {
    let meta: Meta?
    let filters: Filters?
    let results: [Hotel]?
    let pagination: Pagination?
}

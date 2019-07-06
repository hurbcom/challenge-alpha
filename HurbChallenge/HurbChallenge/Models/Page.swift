//
//  Page.swift
//  HurbChallenge
//
//  Created by Felipe Alves on 05/07/19.
//  Copyright © 2019 Bolzaniapps. All rights reserved.
//

import Foundation

struct Page: Codable {
    let meta: Meta
    let filters: Filters
    let results: [Hotel]
    let pagination: Pagination
}

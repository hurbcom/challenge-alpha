//
//  Pagination.swift
//  HurbChallenge
//
//  Created by Felipe Alves on 05/07/19.
//  Copyright © 2019 Bolzaniapps. All rights reserved.
//

import Foundation

struct Pagination: Codable {
    let count: Int
    let firstPage: String
    let nextPage: String
    let previousPage: String?
    let lastPage: String
}

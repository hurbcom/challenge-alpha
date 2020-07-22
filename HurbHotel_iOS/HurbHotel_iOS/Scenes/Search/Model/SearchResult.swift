//
//  SearchResult.swift
//  HurbHotel_iOS
//
//  Created by Helio Junior on 22/07/20.
//  Copyright © 2020 HelioTecnologia. All rights reserved.
//

import Foundation

struct SearchResult: Decodable {
    let filters: String?
    let pagination: String?
    let results: String?
}

//
//  FetchHotelsResponse.swift
//  DesafioHurb
//
//  Created by Edson Aparecido Guido on 13/09/20.
//  Copyright © 2020 Edson Aparecido Guido. All rights reserved.
//

import Foundation

struct FetchHotelsResponse: Codable {
    let meta: Meta
    let filters: Filters
    let results: [Hotel]
    let pagination: Pagination
}

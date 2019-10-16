//
//  HURBListModel.swift
//  GPSurb
//
//  Created by Gilson Santos on 16/10/19.
//  Copyright © 2019 Gilson Santos. All rights reserved.
//

import Foundation

public struct HURBListModel: Codable {
    let meta: MetaModel?
    let filters: FiltersModel?
    let results: [ResultsModel]?
    let pagination: PaginationModel?
}

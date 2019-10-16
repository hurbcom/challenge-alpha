//
//  MetaModel.swift
//  GPSurb
//
//  Created by Gilson Santos on 16/10/19.
//  Copyright © 2019 Gilson Santos. All rights reserved.
//

import Foundation

struct MetaModel: Codable {
    let count: Int?
    let offset: Int?
    let query: String?
    let warning: String?
    let countWithAvailabilityInPage: Int?
    let countHotel: Int?
    let countPackage: Int?
    let countTicket: Int?
    let countBustrip: Int?
    let countDisney: Int?
}

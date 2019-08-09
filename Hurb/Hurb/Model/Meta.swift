//
//  Meta.swift
//  Hurb
//
//  Created by Alexandre Papanis on 08/08/19.
//  Copyright © 2019 Papanis. All rights reserved.
//

import Foundation

struct Meta: Codable {
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

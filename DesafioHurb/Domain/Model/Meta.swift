//
//  Meta.swift
//  DesafioHurb
//
//  Created by Edson Aparecido Guido on 13/09/20.
//  Copyright © 2020 Edson Aparecido Guido. All rights reserved.
//

import Foundation

struct Meta: Codable {
    let count: Int
    let offset: Int
    let query: String
    let warning: String
    let countWithAvailability: Int
    let countWithAvailabilityInPage: Int
    let countHotel: Int
    let countPackage: Int
    let countTicket: Int
    let countBustrip: Int
    let countDisney: Int
}

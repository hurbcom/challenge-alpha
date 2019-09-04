//
//  Meta.swift
//  HurbAlpha
//
//  Created by Julia Rocha on 04/09/19.
//  Copyright © 2019 Julia Rocha. All rights reserved.
//

import Foundation
// MARK: - Meta
class Meta: Codable {
    let count, offset: Int
    let query, warning: String
    let countWithAvailabilityInPage, countHotel, countPackage, countTicket: Int
    let countBustrip, countDisney: Int
    
    init(count: Int, offset: Int, query: String, warning: String, countWithAvailabilityInPage: Int, countHotel: Int, countPackage: Int, countTicket: Int, countBustrip: Int, countDisney: Int) {
        self.count = count
        self.offset = offset
        self.query = query
        self.warning = warning
        self.countWithAvailabilityInPage = countWithAvailabilityInPage
        self.countHotel = countHotel
        self.countPackage = countPackage
        self.countTicket = countTicket
        self.countBustrip = countBustrip
        self.countDisney = countDisney
    }
}

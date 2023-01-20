//
//  HotelQueryParams.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 20/01/23.
//

import Foundation

struct HotelQueryParams {
    var query: String = Constants.DEFAULT_DESTINATION
    var adults: Int?
    var startDate: Date?
    var endDate: Date?
}

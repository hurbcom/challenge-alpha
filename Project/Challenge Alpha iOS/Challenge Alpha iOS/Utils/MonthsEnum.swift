//
//  MonthsEnum.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 17/01/23.
//

import Foundation

enum Months: String {
    case january = "jan"
    case february = "fev"
    case march = "mar"
    case april = "abr"
    case may = "maio"
    case june = "jun"
    case july = "jul"
    case agoust = "ago"
    case september = "set"
    case october = "out"
    case november = "nov"
    case december = "dec"
    case unknown = ""
    
    init?(monthNumber: Int) {
        switch monthNumber {
        case 1: self = .january
        case 2: self = .february
        case 3: self = .march
        case 4: self = .april
        case 5: self = .may
        case 6: self = .june
        case 7: self = .july
        case 8: self = .agoust
        case 9: self = .september
        case 10: self = .october
        case 11: self = .november
        case 12: self = .december
        default: self = .unknown
        }
    }
}

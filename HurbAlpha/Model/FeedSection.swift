//
//  FeedSection.swift
//  HurbAlpha
//
//  Created by Rodrigo Bukowitz on 06/11/19.
//  Copyright © 2019 Rodrigo Bukowitz. All rights reserved.
//

import Foundation

enum SectionType {
    case package
    case hotel
}

struct FeedSection {
    var title: String
    var type: SectionType
    var items: [HurbOffers]
}

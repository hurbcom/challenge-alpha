//
//  FeedSection.swift
//  HurbAlpha
//
//  Created by Rodrigo Bukowitz on 06/11/19.
//  Copyright © 2019 Rodrigo Bukowitz. All rights reserved.
//

import Foundation

///Class that represents which section in the tableView 
class FeedSection {
    var title: String
    var cellData: FeedSectionType
    
    init(with title: String, cellData: FeedSectionType) {
        self.title = title
        self.cellData = cellData
    }
}

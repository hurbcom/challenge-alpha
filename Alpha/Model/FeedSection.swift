//
//  FeedSection.swift
//  Alpha
//
//  Created by Theo Mendes on 16/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

class FeedSection {
    var title: String
    var subTitle: String?
    var cellData: FeedSectionType

    init(title: String, subTitle: String?, cellData: FeedSectionType) {
        self.title = title
        self.subTitle = subTitle
        self.cellData = cellData
    }
}

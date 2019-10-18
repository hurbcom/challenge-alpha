//
//  FeedSection.swift
//  Alpha
//
//  Created by Theo Mendes on 16/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//
import RxDataSources

struct FeedSection {
    var header: String
    var subTitle: String?
    var items: [FeedSectionType]
}

extension FeedSection: SectionModelType {
    typealias Item = FeedSectionType

    init(original: FeedSection, items: [Item]) {
        self = original
        self.items = items
    }
}

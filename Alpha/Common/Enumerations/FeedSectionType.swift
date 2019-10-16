//
//  FeedSectionType.swift
//  Alpha
//
//  Created by Theo Mendes on 16/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

enum FeedSectionType {
    case Star(hotels: [APIResult])
    case Package(packages: [APIResult])

    var identifier: String {
        switch self {
        case .Star: return Identifiers.Star.rawValue
        case .Package: return Identifiers.Package.rawValue
        }
    }
}

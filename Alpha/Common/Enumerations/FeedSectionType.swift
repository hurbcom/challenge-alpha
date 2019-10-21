//
//  FeedSectionType.swift
//  Alpha
//
//  Created by Theo Mendes on 16/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

/// A enum thats help to divide the deals into Star or Package section
enum FeedSectionType {
    /// Star feed section
    case Star(hotels: [Deal])
    /// Package feed section
    case Package(packages: [Deal])
    /// The cell's Identifier
    var identifier: String {
        switch self {
        case .Star: return Identifiers.Star.rawValue
        case .Package: return Identifiers.Package.rawValue
        }
    }
}

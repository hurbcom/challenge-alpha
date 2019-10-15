//
//  Images.swift
//  Alpha
//
//  Created by Theo Mendes on 15/10/19.
//  Copyright © 2019 Hurb. All rights reserved.
//

import Foundation

struct Image: Codable {
    var url: URL
    var description: String?

    enum CodingKeys: String, CodingKey {
        case url
        case description
    }
}

//
//  Gallery.swift
//  DesafioHurb
//
//  Created by Edson Aparecido Guido on 13/09/20.
//  Copyright © 2020 Edson Aparecido Guido. All rights reserved.
//

import Foundation

struct Gallery: Codable, Equatable {
    let galleryDescription: String?
    let url: String
    let roomID: String?

    enum CodingKeys: String, CodingKey {
        case galleryDescription = "description"
        case url
        case roomID = "room_id"
    }
}

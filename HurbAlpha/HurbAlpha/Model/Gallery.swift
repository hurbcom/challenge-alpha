//
//  Gallery.swift
//  HurbAlpha
//
//  Created by Julia Rocha on 04/09/19.
//  Copyright © 2019 Julia Rocha. All rights reserved.
//

import Foundation

// MARK: - Declaration

struct Gallery: Codable {
    let galleryDescription: String?
    let url: String
    
    // MARK: - Enum Coding Keys Declaration

    enum CodingKeys: String, CodingKey {
        case galleryDescription
        case url
    }
}

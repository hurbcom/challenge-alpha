//
//  Gallery.swift
//  HurbAlpha
//
//  Created by Julia Rocha on 04/09/19.
//  Copyright © 2019 Julia Rocha. All rights reserved.
//

import Foundation

// MARK: - Gallery
class Gallery: Codable {
    let galleryDescription: String?
    let url: String
    
    enum CodingKeys: String, CodingKey {
        case galleryDescription
        case url
    }
    
    init(galleryDescription: String?, url: String) {
        self.galleryDescription = galleryDescription
        self.url = url
    }
}

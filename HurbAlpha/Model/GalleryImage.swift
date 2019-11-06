//
//  GalleryImage.swift
//  HurbAlpha
//
//  Created by Rodrigo Bukowitz on 06/11/19.
//  Copyright © 2019 Rodrigo Bukowitz. All rights reserved.
//
import Foundation
/// struct to obtain data from the gallery
struct GalleryImage: Codable{
    var description: String?
    var url: URL
}

/// extension to represent all coding keys
extension GalleryImage {
    enum CodingKeys: String, CodingKey {
        case description
        case url
    }
}

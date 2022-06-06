//
//  GalleryModel.swift
//  Hurb_iOS_Challenge
//
//  Created by Gáudio Ney on 22/05/22.
//

import Foundation

// MARK: - Gallery
struct Gallery: Codable {
    let galleryDescription: String?
    let url: String?
    
    enum CodingKeys: String, CodingKey {
        case galleryDescription = "description"
        case url
    }
}

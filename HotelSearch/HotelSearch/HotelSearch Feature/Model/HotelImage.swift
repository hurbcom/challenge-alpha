//
//  HotelImage.swift
//  HotelSearch
//
//  Created by Tulio Parreiras on 14/09/20.
//  Copyright © 2020 Tulio Parreiras. All rights reserved.
//

import Foundation

public struct HotelImage: Hashable {
    public let description: String?
    public let url: URL?
    
    public init(description: String?, url: URL?) {
        self.description = description
        self.url = url
    }
}


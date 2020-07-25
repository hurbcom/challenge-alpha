//
//  Suggestions.swift
//  HurbHotel_iOS
//
//  Created by Helio Junior on 25/07/20.
//  Copyright © 2020 HelioTecnologia. All rights reserved.
//

import Foundation

struct Suggestions: Decodable {
    let suggestions: [Suggestion]
    
    struct Suggestion: Decodable {
        let text: String?
        let country: String?
        let state: String?
        let city: String?
    }
}

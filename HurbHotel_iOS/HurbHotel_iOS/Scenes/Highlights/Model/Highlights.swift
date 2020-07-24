//
//  Highlights.swift
//  HurbHotel_iOS
//
//  Created by Helio Junior on 24/07/20.
//  Copyright © 2020 HelioTecnologia. All rights reserved.
//

import Foundation
struct Highlights: Decodable {
    let opportunity: Section?
    let vacation: Section?
    let marriage: Section?
    let luxury: Section?
    
    struct Section: Decodable {
        let title: String?
        let subtitle: String?
        let cards: [Card]?
        
        struct Card: Decodable {
            let imageUrl: String?
            let title: String?
        }
    }
}

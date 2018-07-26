//
//  HotelListSetup.swift
//  HotelUrbanoChallenge
//
//  Created by maciosdev on 26/07/2018.
//  Copyright © 2018 Ronilson. All rights reserved.
//

import Foundation

struct HotelListSetup {
    
    let groupParameter: Group.GroupType
    
    init(group: Int) {
        
        guard let groupOption = Group.GroupType.init(rawValue: group) else {
            fatalError("Couldn't find this group option to configure list view")
        }

        self.groupParameter = groupOption
    }
}

enum Group {
    
    enum GroupType: Int {
        case stars = 0
        case travel = 1
        
        var groupTypeShortDescription: String {
            switch self {
            case .stars: return "Estrelas"
            case .travel: return "Pacotes"
            }
        }
    }
}

//
//  HotelStarsSections+Enum.swift
//  Hurb_iOS_Challenge
//
//  Created by Gáudio Ney on 31/05/22.
//

import Foundation

/// Enum used to personalize the CollectionView Sections and better treat them.
enum StarsForSection: Int {
    typealias rawValue = String
    
    case fiveStars, fourStars, threeStars, twoStars, oneStar
    
    init?(rawValue: Int){
        switch rawValue {
        case 0 : self = .fiveStars
        case 1 : self = .fourStars
        case 2 : self = .threeStars
        case 3 : self = .twoStars
        case 4 : self = .oneStar
        default : return nil
        }
    }
    
    static let identifiers = [
        fiveStars:     "5 Estrelas",
        fourStars:     "4 Estrelas",
        threeStars:    "3 Estrelas",
        twoStars:      "2 Estrelas",
        oneStar:       "1 Estrela"
    ]
    
    static let sectionHeaders = [
        fiveStars:     NSLocalizedString("🏨 Hotéis 5 Estrelas", comment: "Primeira Sessão"),
        fourStars:     NSLocalizedString("🏨 Hotéis 4 Estrelas", comment: "Segunda Sessão"),
        threeStars:    NSLocalizedString("🏨 Hotéis 3 Estrelas", comment: "Terceira Sessão"),
        twoStars:      NSLocalizedString("🏨 Hotéis 2 Estrelas", comment: "Quarta Sessão"),
        oneStar:       NSLocalizedString("🏨 Hotéis 1 Estrela", comment: "Quinta Sesssão")
    ]
    
    static var count:Int { return 5 }
    
    func identifier() -> String {
        if let id = StarsForSection.identifiers[self] {
            return id
        } else {
            return "undefined"
        }
    }
    
    func headerTitle() -> String? {
        if let string = StarsForSection.sectionHeaders[self] {
            return string
        } else {
            return nil
        }
    }
}

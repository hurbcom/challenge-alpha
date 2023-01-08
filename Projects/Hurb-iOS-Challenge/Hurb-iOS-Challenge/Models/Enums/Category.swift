//
//  Category.swift
//  Hurb-iOS-Challenge
//
//  Created by Rômulo Monteiro on 08/01/23.
//

import UIKit

enum Category: Codable, Hashable {
    
    case hotel
    
    //case pacote
    case unknown(value: String)
    
    init(from decoder: Decoder) throws {
        
        let container = try decoder.singleValueContainer()
        let status = try? container.decode(String.self)
        
        switch status {
                
            case "hotel": self = .hotel
//            case "pacote": self = .pacote
                
            default:
                self = .unknown(value: status ?? "unknown")
        }
    }
}

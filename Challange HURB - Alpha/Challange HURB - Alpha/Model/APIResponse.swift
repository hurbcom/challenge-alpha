//
//  APIResponse.swift
//  Challange HURB - Alpha
//
//  Created by Luiz Fernando Cunha Duarte on 28/10/19.
//  Copyright © 2019 Luiz Fernando Cunha Duarte. All rights reserved.
//

import Foundation

/// The interface of the of the API`s  response
struct APIResponse: Codable {
    
    // MARK: - Properties
    var results: [Experience]
    
    // MARK: - Coding Keys
    
    enum CodingKeys: String, CodingKey {
        case results
    }
}

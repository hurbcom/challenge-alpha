//
//  HurbAPIExtension.swift
//  hurb-challenge-alphaTests
//
//  Created by Hannah  on 27/12/2019.
//  Copyright © 2019 Hannah . All rights reserved.
//

import Foundation
@testable import hurb_challenge_alpha

extension HurbAPI {

    var testSampleData: Data {
        var dataUrl: URL?

        switch self {
        case .getHotels:
            dataUrl = Bundle(for: APITests.self).url(forResource: "Accommodations", withExtension: "json")
            if let url = dataUrl, let data = try? Data(contentsOf: url) {
                 debugPrint(data)
                 return data
             }
        case .getSuggestion:
            dataUrl = Bundle(for: APITests.self).url(forResource: "Suggestions", withExtension: "json")
            if let url = dataUrl, let data = try? Data(contentsOf: url) {
                debugPrint(data)
                return data
            }
            
        }
          return Data()
    }
}

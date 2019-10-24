//
//  BestDestinationService.swift
//  GPSurb
//
//  Created by Gilson Santos on 16/10/19.
//  Copyright © 2019 Gilson Santos. All rights reserved.
//

import Foundation

open class HomeService: BaseService {
    open func getBestDestination() -> [BestDestinationModel]? {
        var formModel: [BestDestinationModel]?
        guard let file = Bundle.main.path(forResource: "best_destinations", ofType: ".json"),
              let jsonData = try? Data(contentsOf: URL(fileURLWithPath: file)) else { return nil }
        do {
            formModel = try JSONDecoder().decode([BestDestinationModel].self, from: jsonData)
        } catch let jsonParseError {
            print("Parse error = \(jsonParseError)")
        }
        return formModel
    }
}

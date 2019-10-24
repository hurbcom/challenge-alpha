//
//  BestDestinationServiceMock.swift
//  GPSurbTests
//
//  Created by Gilson Santos on 24/10/19.
//  Copyright © 2019 Gilson Santos. All rights reserved.
//

import Foundation
import GPSurb

class HomeServiceMock: HomeService {
    
    private let fileName: String
    
    init(fileName: String) {
        self.fileName = fileName
    }
    
    override func getBestDestination() -> [BestDestinationModel]? {
        guard let url = Bundle.main.url(forResource: self.fileName, withExtension: "json") else {
            return nil
        }
        do {
            let data = try Data(contentsOf: url)
            let result = try JSONDecoder().decode([BestDestinationModel].self, from: data)
            return result
        } catch {
            return nil
        }
    }
}

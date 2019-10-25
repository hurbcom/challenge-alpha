//
//  HotelAndPackageServiceMock.swift
//  GPSurbTests
//
//  Created by Gilson Santos on 25/10/19.
//  Copyright © 2019 Gilson Santos. All rights reserved.
//

import Foundation

class HotelAndPackageServiceMock: HotelPackageService {
    
    private let fileName: String
    
    init(fileName: String) {
        self.fileName = fileName
    }
    
    override func getHotelAndPackage(query: String, filter: TypeFilter, page: Int, completion: @escaping (Result<HURBListModel, ErrorType>) -> Void) {
        
        guard let url = Bundle.main.url(forResource: self.fileName, withExtension: "json") else {
            completion(.failure(.error404))
            return
        }
        do {
            let data = try Data(contentsOf: url)
            let result = try JSONDecoder().decode(HURBListModel.self, from: data)
            try self.validation(model: result)
            completion(.success(result))
        } catch {
            completion(.failure(.errorParsingJSON))
        }
        
    }
    
}

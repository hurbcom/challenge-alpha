//
//  MockAPIService.swift
//  Hoteis Buzios Tests
//
//  Created by Andre Dias on 03/10/20.
//  Copyright © 2020 Andre Dias. All rights reserved.
//
@testable import Hotéis_Búzios

class MockApiService: APIServiceProtocol {
    var isFetchHotelsCalled = false
    
    var completeHotels: [HotelsResults] = StubGenerator().stubHotelsResults()
    var completePackages: [PackageResults] = StubGenerator().stubPackageResults()
    
    func getHotelsAndPackages(completion: @escaping ([HotelsResults]?, [PackageResults]?, APIError?) -> ()) {
        isFetchHotelsCalled = true
        completion(completeHotels, completePackages, nil)
    }

    
}

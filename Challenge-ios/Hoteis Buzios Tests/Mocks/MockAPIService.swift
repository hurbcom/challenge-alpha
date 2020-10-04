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
    
    var completeHotels: [HotelsResults] = [HotelsResults]()
    var completePackages: [PackageResults] = [PackageResults]()
    var completionClosure: (([HotelsResults]?, [PackageResults]?, String?) ->())!
    
     func getHotelsAndPackages(completion: @escaping (_ hotels: [HotelsResults]?, _ packages: [PackageResults]?, _ error: String?) ->()) {
        isFetchHotelsCalled = true
        completionClosure = completion
        
    }
    
    func fetchSuccess() {
        completionClosure(completeHotels, completePackages, nil)
    }
    
    func fetchFail(error: String?) {
        completionClosure(nil, nil, error)
    }
    
}

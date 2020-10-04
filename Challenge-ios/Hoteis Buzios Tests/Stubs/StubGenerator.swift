//
//  StubGenerator.swift
//  Hoteis Buzios Tests
//
//  Created by Andre Dias on 03/10/20.
//  Copyright © 2020 Andre Dias. All rights reserved.
//
@testable import Hotéis_Búzios
import Foundation

class StubGenerator {
    
    func stubPackageResults() -> PackageResults {
        guard let url = Bundle(for: type(of: self)).url(forResource: "PackageResultsJsonResponse", withExtension: "json")
            else { fatalError("Can't find search.json file") }
         do {
             let data = try Data(contentsOf: url)
             let decoder = JSONDecoder()
             return try decoder.decode(PackageResults.self, from: data)
         } catch {
             fatalError("error:\(error)")
         }
    }
    
    func stubHotelsResults() -> [HotelsResults] {
        guard let url = Bundle(for: type(of: self)).url(forResource: "HotelsResultsJsonResponse", withExtension: "json")
            else { fatalError("Can't find search.json file") }
        do {
            let data = try Data(contentsOf: url)
            let decoder = JSONDecoder()
            return try decoder.decode([HotelsResults].self, from: data)
        } catch {
              fatalError("error:\(error)")
        }
    }
}



//
//  Service.swift
//  Challenge Alpha iOS (iOS)
//
//  Created by Kleiton Mendes on 18/01/23.
//

import UIKit

protocol ResultServiceProtocol {
    func success(result : [Result])
    func error(error : Error)
}

struct Service {
    
    var result: [Result] = []
    
    mutating func loadHotels() {
        let fileUrl = Bundle.main.url(forResource: "hotel", withExtension: "json")!
        let jsonData = try! Data(contentsOf: fileUrl)
        
        do {
            let data = try JSONDecoder().decode([Result].self, from: jsonData)
            
            result = data[0].results
        } catch {
            print("error ==> \(error.localizedDescription)")
        }
    }
}

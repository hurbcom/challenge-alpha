//
//  HighlightsService.swift
//  HurbHotel_iOS
//
//  Created by Helio Junior on 24/07/20.
//  Copyright © 2020 HelioTecnologia. All rights reserved.
//

import Foundation

final class HighlightsService {
    
    func fetchHighlights(success: @escaping (Highlights) -> (), failure: @escaping (String) -> ()) {
        
        guard let url = URL(string: "https://demo9346999.mockable.io/hurbHotel/hightlights") else {
            failure("URL inválida!")
            return
        }
        Network.get(url: url) { (data, error) in
            
            if let error = error {
                failure(error.localizedDescription)
            }

            do {
                guard let data = data else { return }
                let result = try JSONDecoder().decode(Highlights.self, from: data)
                success(result)
            } catch let err {
                failure(err.localizedDescription)
            }
        }
    }
}

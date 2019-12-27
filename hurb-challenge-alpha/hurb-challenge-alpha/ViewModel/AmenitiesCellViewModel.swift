//
//  AmenitiesCellViewModel.swift
//  hurb-challenge-alpha
//
//  Created by Hannah  on 26/12/2019.
//  Copyright © 2019 Hannah . All rights reserved.
//

import Foundation

class AmenitiesCellViewModel: ObservableObject {
  
    @Published var name = String()
    
    init(name: String?) {
        
        if let name = name {
                  self.name = name
        }
      
    }

}

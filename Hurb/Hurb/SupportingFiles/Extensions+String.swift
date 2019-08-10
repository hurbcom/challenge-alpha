//
//  Extensions+String.swift
//  Hurb
//
//  Created by Alexandre Papanis on 10/08/19.
//  Copyright © 2019 Papanis. All rights reserved.
//

import Foundation

extension String {
    
    var convertStringToUrlString: String{
        
        return self.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed)!
    }
}

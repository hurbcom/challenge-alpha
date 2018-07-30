//
//  ServiceManagerProtocol.swift
//  HotelUrbanoChallenge
//
//  Created by maciosdev on 22/07/2018.
//  Copyright © 2018 Ronilson. All rights reserved.
//

import Foundation

protocol ServiceManagerProtocol {
    
    func GetData(url: String, parameters: [String : Any]?, success: @escaping (Data) -> Void, failure: @escaping (ServiceError) -> Void)
    
}

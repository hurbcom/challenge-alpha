//
//  HurbAlphaAPI.swift
//  HurbAlpha
//
//  Created by Rodrigo Bukowitz on 05/11/19.
//  Copyright © 2019 Rodrigo Bukowitz. All rights reserved.
//

import Moya

/// this protocol list all possibilities that our HurbAlphaAPI have.
protocol HurbAlphaAPI {
    /// Moya provider that handles all the hard work in Http requests
    var provider: MoyaProvider<HurbOffersAPI> { get }
    
    /**
    Get the offers from the Hurb API

    - Parameter place : The string of the place you want to get information
    - Parameter page : The integer representing the page you want to get information
    - Parameter completion : A completion with all the HurbOffers in a array and a response of error
    */
    func getOffers(place: String, page: Int, completion: @escaping ([HurbOffers]?, Error?) -> ())
}

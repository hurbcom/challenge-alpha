//
//  HomeTableCellViewModel.swift
//  Challenge-ios
//
//  Created by Andre Dias on 30/09/20.
//  Copyright © 2020 Andre Dias. All rights reserved.
//

import Foundation


class HomeTableCellViewModel {
    
    var hotelImageURL: URL?
    var hotelName: String?
    var hotelAddress: String?
   
   
    init(_ hotelModel: HotelsResults) {
       
        if let hotelImage = hotelModel.image {
            if let url = URL(string: hotelImage) {
                self.hotelImageURL = url
            }
        }
    
        self.hotelName = hotelModel.name

        if let state = hotelModel.address.state {
            self.hotelAddress = "\(hotelModel.address.city) / \(state)"
        } else {
            self.hotelAddress = hotelModel.address.city
        }
    }

}



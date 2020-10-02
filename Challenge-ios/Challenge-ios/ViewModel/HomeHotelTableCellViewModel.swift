//
//  HomeTableCellViewModel.swift
//  Challenge-ios
//
//  Created by Andre Dias on 30/09/20.
//  Copyright © 2020 Andre Dias. All rights reserved.
//

import Foundation


class HomeHotelTableCellViewModel {
    
    var hotelImageURL: URL?
    var hotelName: String = ""
    var hotelAddress: String = ""
    var hotelPrice: String = ""
    var amenities: String = ""
    var stars: Int = Constants.numberOfStars
   
    init(_ hotelModel: HotelsResults) {
        self.configHotelImage(model: hotelModel)
        self.configHotelName(model: hotelModel)
        self.configHotelAddress(model: hotelModel)
        self.configHotelPrice(model: hotelModel)
        self.configHotelStars(model: hotelModel)
        self.configHotelAmenities(model: hotelModel)

    }
    
    private func configHotelImage(model: HotelsResults) {
        let hotelImage = model.image
        if !hotelImage.isEmpty {
            if let url = URL(string: hotelImage) {
                self.hotelImageURL = url
            }
        }
    }
    
    private func configHotelName(model: HotelsResults) {
        self.hotelName = model.name
    }
    
    private func configHotelAddress(model: HotelsResults) {
        if let address = model.address {
            self.hotelAddress = "\(address.city) / \( address.state)"
        } 
    }
    
    private func configHotelPrice(model: HotelsResults) {
        if let price = model.price?.currentPrice {
            if let formattedValue = GenericSingleton.shared.currencyFormatter.string(from: price as NSNumber) {
                self.hotelPrice = formattedValue
            }
        }
    }
    
    private func configHotelStars(model: HotelsResults) {
        self.stars = model.stars
    }
    
    private func configHotelAmenities(model: HotelsResults) {
        var ametiesString = ""
        for (index, element) in model.amenities.enumerated() {
            if index == 3 {
                break
            } else {
                ametiesString.append("\(element.name)\n")
            }
        }
        self.amenities = ametiesString
    }
    
    

}



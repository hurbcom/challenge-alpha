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
    var hotelName: String = ""
    var hotelAddress: String = ""
    var stars: Int = 3
    var hotelPrice: String = ""
    var amenities: String = ""
    
    lazy var currencyFormatter: NumberFormatter = {
        let currencyFormatter = NumberFormatter()
        currencyFormatter.locale = Locale.init(identifier: "pt_BR")
        currencyFormatter.numberStyle = .currency
        return currencyFormatter
    }()
   
   
    init(_ hotelModel: HotelsResults) {
        self.configHotelImage(model: hotelModel)
        self.configHotelName(model: hotelModel)
        self.configHotelAddress(model: hotelModel)
        self.configHotelPrice(model: hotelModel)
        self.configHotelStars(model: hotelModel)
        self.configHotelAmenities(model: hotelModel)

    }
    
    private func configHotelImage(model: HotelsResults) {
        if let hotelImage = model.image {
            if let url = URL(string: hotelImage) {
                self.hotelImageURL = url
            }
        }
    }
    
    private func configHotelName(model: HotelsResults) {
        self.hotelName = model.name
    }
    
    private func configHotelAddress(model: HotelsResults) {
        if let state = model.address.state {
            self.hotelAddress = "\(model.address.city) / \(state)"
        } else {
            self.hotelAddress = model.address.city
        }
    }
    
    private func configHotelPrice(model: HotelsResults) {
        if let price = model.price.currentPrice {
            if let formattedValue = currencyFormatter.string(from: price as NSNumber) {
                self.hotelPrice = formattedValue
            }
        }
    }
    
    private func configHotelStars(model: HotelsResults) {
        if let stars = model.stars {
            self.stars = stars
        }
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



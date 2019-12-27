//
//  HotelCellViewModel.swift
//  hurb-challenge-alpha
//
//  Created by Hannah  on 26/12/2019.
//  Copyright © 2019 Hannah . All rights reserved.
//

import SwiftUI

class HotelCellViewModel: ObservableObject {
    
    @Published var hotel: Accommodation
    @Published var discount = String()
    @Published var price = String()
    @Published var urlImage = String()
    @Published var name = String()
    @Published var city = String()
    @Published var huFreeCancellation = false

    init(hotel: Accommodation) {
        self.hotel = hotel
        self.calculateDiscount()
        self.loadPrice()
        self.getUrlImage()
        self.getCity()
        self.getName()
        self.getFreeCancelation()
    }
    
    // MARK: - Methods
    /// init huFreeCancellation value
    fileprivate func getFreeCancelation() {
           
        if let huFreeCancellation = self.hotel.huFreeCancellation {
               self.huFreeCancellation = huFreeCancellation
           }
       }
    
    // MARK: - Methods
    /// init name value
    fileprivate func getName() {
        
        if let name = self.hotel.name {
            self.name = name
        }
    }
    
    // MARK: - Methods
    /// init city value
    fileprivate func getCity() {
        if let city = self.hotel.address?.city {
            self.city = city
        }
    }
    
    // MARK: - Methods
    ///load value url to Image
    fileprivate func getUrlImage() {
        if let url = self.hotel.image {
            self.urlImage = url
        } else {
            if let gallery = self.hotel.gallery {
                if !gallery.isEmpty {
                    let image = gallery.first
                    if let url = image?.url {
                        self.urlImage = url
                    }
                }
            }
            
        }
    }
    
    // MARK: - Methods
    /// load string price and currency
    fileprivate func loadPrice() {
        
        if let price = self.hotel.price {
            let currentPrice = price.amount
            if let currentPrice = currentPrice {
                let priceString = "R$" + String(Int(currentPrice))
                self.price = priceString
            }
        }
    }
    
    // MARK: - Methods
    ///calculate discount in accomodation
    fileprivate func calculateDiscount() {
        
        if let price = self.hotel.price {
            
            let oldPrice = price.oldPrice
            let currentPrice = price.amount
            if let oldPrice = oldPrice {
                if let currentPrice = currentPrice {
                    if oldPrice > currentPrice {
                        let fractionalProgress = ((oldPrice - currentPrice) / oldPrice) * 100
                        let formatString = "- " + String(Int(fractionalProgress)) + "%"
                        self.discount = formatString
                    }
                }
            }
        }
        
    }
    
}

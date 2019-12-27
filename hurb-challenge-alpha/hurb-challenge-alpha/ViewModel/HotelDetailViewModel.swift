//
//  HotelDetailViewModel.swift
//  hurb-challenge-alpha
//
//  Created by Hannah  on 26/12/2019.
//  Copyright © 2019 Hannah . All rights reserved.
//

import Foundation
class HotelDetailViewModel: ObservableObject {
    
    @Published var hotel: Accommodation
    @Published var smallDescription = String()
    @Published var urlImage = String()
    @Published var name = String()
    @Published var adress = String()
    @Published var amenities = [Amenities]()
    @Published var latitude = Double()
    @Published var longitude = Double()
    
    init(hotel: Accommodation) {
        self.hotel = hotel
        self.getName()
        self.getDescription()
        self.getAdress()
        self.getUrlImage()
        self.getAmenities()
        self.getGeolocation()
    }
    
    // MARK: - Methods
    /// init name value
    fileprivate func getName() {
        
        if let name = self.hotel.name {
            self.name = name
        }
    }
    // MARK: - Methods
    /// init amenities value
    fileprivate func getAmenities() {
        
        if let amenities = self.hotel.amenities {
            self.amenities = amenities
        }
    }
    // MARK: - Methods
    /// init Geolocation value
    fileprivate func getGeolocation() {
        
        if let geolocation = self.hotel.address?.geoLocation {
            if let latitude = geolocation.latitude {
                if let longitude = geolocation.longitude {
                    self.latitude = latitude
                    self.longitude = longitude
                }
            }
            
        }
    }
    // MARK: - Methods
    /// init adress value
    fileprivate func getAdress() {
        var adress = String()
        if let street = self.hotel.address?.street {
            adress += " " + street
        }
        if let city = self.hotel.address?.city {
            adress += " - " + city
        }
        if let state = self.hotel.address?.state {
            adress += ", " + state
        }
        self.adress = adress
    }
    // MARK: - Methods
    /// init description value
    fileprivate func getDescription() {
        let smallDescription = hotel.smallDescription
        if let smallDescription = smallDescription {
            self.smallDescription = smallDescription
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
    
}

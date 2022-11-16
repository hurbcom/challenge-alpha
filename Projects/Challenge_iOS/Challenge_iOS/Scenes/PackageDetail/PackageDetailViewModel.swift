//
//  PackageDetailViewModel.swift
//  Challenge_iOS
//
//  Created by Helio Junior on 16/11/22.
//

import Foundation

final class PackageDetailViewModel {
    // MARK: Propoerties
    private let model: SearchResultModel
    
    init(model: SearchResultModel) {
        self.model = model
    }
    
    // MARK: Methods
    func getName() -> String? {
        model.name
    }
    
    func getAddress() -> String? {
        model.getAddressShort()
    }
    
    func getValue() -> String? {
        model.getAmountShort()
    }
    
    func getPeriod() -> String? {
        model.getPeriod()
    }
    
    func getCoordinate() -> (lat: Double, lon: Double)? {
        guard let lat = model.address?.geoLocation?.lat,
                let lon = model.address?.geoLocation?.lon
        else { return nil }
        
        return (lat, lon)
    }
    
    func getImagesURL() -> [String] {
        model.gallery.map({ $0.url })
    }
    
    func getAmenities() -> [SearchResultModel.Amenities] {
        model.amenities
    }
}

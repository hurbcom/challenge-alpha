//
//  HotelResult.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 19/01/23.
//

import Foundation

// MARK: - Hotel Result
struct HotelResult: Codable {
    var id: String?
    var sku: String?
    var name: String?
    var url: String?
    var stars: Int?
    var isAvailable: Bool?
    var huFreeCancellation: Bool?
    var description: String?
    var price: Price?
    var address: Address?
    var tags: [Tag]?
    var gallery: [GalleryItem]?
    var amenities: [Amenity]?
    
    // MARK: - Getters
    func getCity() -> String {
        return self.address?.city ?? ""
    }
    
    func getCountry() -> String {
        return self.address?.country ?? ""
    }
    
    func getName() -> String {
        return self.name ?? ""
    }
    
    func getDescription() -> String {
        return self.description ?? ""
    }
    
    func getAmount() -> Double {
        return self.price?.amount ?? 0.0
    }
    
    func getFormattedAmount() -> String {
        guard let currency = self.price?.currency else {
            return ""
        }
        return self.getAmount().formatCurrency(currency)
    }
    
    func getOriginalAmount() -> Double {
        return self.price?.originalAmount ?? 0.0
    }
    
    func getFormattedOriginalAmount() -> String {
        guard let currency = self.price?.currency else {
            return ""
        }
        return self.getOriginalAmount().formatCurrency(currency)
    }
    
    func getImagesURL() -> [String] {
        guard let gallery = self.gallery else {
            return []
        }
        
        let urls = gallery.map { item -> String in
            return item.url ?? ""
        }
        
        return urls
    }
    
    func getAmenitiesName() -> [String] {
        guard let amenities = self.amenities else {
            return []
        }
        
        let amenitiesName = amenities.map { item -> String in
            return item.name ?? ""
        }
        
        return amenitiesName
    }
    
    func getLatitude() -> Double {
        return self.address?.geolocation?.latitude ?? 0.0
    }
    
    func getLongitude() -> Double {
        return self.address?.geolocation?.longitude ?? 0.0
    }
}

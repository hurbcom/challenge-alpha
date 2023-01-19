//
//  PackageResult.swift
//  Challenge Alpha iOS
//
//  Created by Yuri Strack on 16/01/23.
//

import Foundation

// MARK: - Package Result
struct PackageResult: Codable {
    var id: String?
    var sku: String?
    var name: String?
    var description: String?
    var category: String?
    var url: String?
    var startDate: String?
    var endDate: String?
    var isAvailable: Bool?
    var quantityDescriptors: QuantityDescriptors?
    var price: Price?
    var address: Address?
    var gallery: [GalleryItem]?
    var amenities: [Amenity]?
    var tags: [Tag]?
    
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
    
    func getDuration() -> Int {
        return self.quantityDescriptors?.duration ?? 0
    }
    
    func getMaxPeople() -> Int {
        return self.quantityDescriptors?.maxPeople ?? 0
    }
    
    func getAmount() -> Double {
        return self.price?.amount ?? 0.0
    }
    
    func getFixedAmount() -> Double {
        return self.getAmount() / 100
    }
    
    func getFormattedAmount() -> String {
        guard let currency = self.price?.currency else {
            // TODO: Add log
            return ""
        }
        return self.getFixedAmount().formatCurrency(currency)
    }
    
    func getOriginalAmount() -> Double {
        return self.price?.originalAmount ?? 0.0
    }
    
    func getFixedOriginalAmount() -> Double {
        return self.getOriginalAmount() / 100
    }
    
    func getFormattedOriginalAmount() -> String {
        guard let currency = self.price?.currency else {
            // TODO: Add log
            return ""
        }
        return self.getFixedOriginalAmount().formatCurrency(currency)
    }
    
    func getImagesURL() -> [String] {
        guard let gallery = self.gallery else {
            // TODO: Add log
            return []
        }
        
        let urls = gallery.map { item -> String in
            return item.url ?? ""
        }
        
        return urls
    }
    
    func getTags() -> [String] {
        guard let tags = self.tags else {
            // TODO: Add log
            return []
        }
        
        let tagStrings = tags.map { item -> String in
            return item.label ?? ""
        }
        
        return tagStrings
    }
    
    func getTagsWithDiscount() -> [String] {
        var tagStrings = self.getTags()
        
        let discountLabel = self.getDiscountString()
        
        if !discountLabel.isEmpty {
            tagStrings.insert(discountLabel, at: 0)
        }
        
        return tagStrings
    }
    
    func getDiscountString() -> String {
        let amount = self.getFixedAmount()
        let originalAmount = self.getFixedOriginalAmount()
        
        if amount != originalAmount {
            let discount = 100 - ((originalAmount / amount) * 100)
            let discountLabel = String(format: "-%.0f", discount) + "%"
            return discountLabel
        }
        
        return ""
    }
    
    /// Format startDate string to month and year only.
    /// - Returns: monthName/year
    func getStartDateMonthAndYear() -> String {
        guard let startDate = self.startDate else {
            return ""
        }
        
        return startDate.monthAndYearString()
    }
    
    /// Format endDate string to month and year only.
    /// - Returns: monthName/year
    func getEndDateMonthAndYear() -> String {
        guard let endDate = self.endDate else {
            return ""
        }
        
        return endDate.monthAndYearString()
    }
    
    func getAmenitiesName() -> [String] {
        guard let amenities = self.amenities else {
            // TODO: Add log
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

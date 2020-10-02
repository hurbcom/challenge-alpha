//
//  PackageResults.swift
//  Challenge-ios
//
//  Created by Andre Dias on 02/10/20.
//  Copyright © 2020 Andre Dias. All rights reserved.
//

// This file was generated from JSON Schema using quicktype, do not modify it directly.
// To parse the JSON, add this file to your project and do:
//
//   let hotels = try? newJSONDecoder().decode(Hotels.self, from: jsonData)

import Foundation

// MARK: - Hotels
struct PackageResults: Codable {
    var sku: String = ""
    var isPackage: Bool = true
    var name: String = ""
    var url: String = ""
    var smallDescription: String = ""
    var description: String = ""
    var gallery: [PackageGallery]?
    var address: PackageAddress?
    var tags: [String] = [String]()
    var price: PackagePrice?
    var featuredItem: PackageFeaturedItem?
    var category: String = ""
    var quantityDescriptors: PackageQuantityDescriptors?
    var id: String = ""
    var startDate: String = ""
    var endDate: String = ""
    var amenities: [PackageAmenities]?


    enum CodingKeys: String, CodingKey {
        case sku, isPackage, name, url, smallDescription, description
        case gallery, address, tags, price, featuredItem, category, quantityDescriptors, id, startDate, endDate, amenities
    }
       
    init(from decoder: Decoder) throws {
        if let codingValue = try? decoder.container(keyedBy: CodingKeys.self) {
            if let skuDecoded = try? codingValue.decode(String.self, forKey: .sku) {
                self.sku = skuDecoded
            }
            if let isPackageDecoded = try? codingValue.decode(Bool.self, forKey: .isPackage) {
                self.isPackage = isPackageDecoded
            }
            if let nameDecoded = try? codingValue.decode(String.self, forKey: .name) {
                self.name = nameDecoded
            }
            if let urlDecoded = try? codingValue.decode(String.self, forKey: .url) {
                self.url = urlDecoded
            }
            if let smallDescriptionDecoded = try? codingValue.decode(String.self, forKey: .smallDescription) {
                self.smallDescription = smallDescriptionDecoded
            }
            if let descriptionDecoded = try? codingValue.decode(String.self, forKey: .description) {
                self.description = descriptionDecoded
            }
            if let galleryDecoded = try? codingValue.decode([PackageGallery].self, forKey: .gallery) {
                self.gallery = galleryDecoded
            }
            if let addressDecoded = try? codingValue.decode(PackageAddress.self, forKey: .address) {
                self.address = addressDecoded
            }
            if let tagsDecoded = try? codingValue.decode([String].self, forKey: .tags) {
                self.tags = tagsDecoded
            }
            if let priceDecoded = try? codingValue.decode(PackagePrice.self, forKey: .price) {
                self.price = priceDecoded
            }
            if let featuredItemDecoded = try? codingValue.decode(PackageFeaturedItem.self, forKey: .featuredItem) {
                self.featuredItem = featuredItemDecoded
            }
            if let categoryDecoded = try? codingValue.decode(String.self, forKey: .category) {
                self.category = categoryDecoded
            }
            if let quantityDescriptorsDecoded = try? codingValue.decode(PackageQuantityDescriptors.self, forKey: .quantityDescriptors) {
                self.quantityDescriptors = quantityDescriptorsDecoded
            }
            if let idDecoded = try? codingValue.decode(String.self, forKey: .id) {
                self.id = idDecoded
            }
            if let startDateDecoded = try? codingValue.decode(String.self, forKey: .startDate) {
                self.startDate = startDateDecoded
            }
            if let endDateDecoded = try? codingValue.decode(String.self, forKey: .endDate) {
                self.endDate = endDateDecoded
            }
            if let amenitiesDecoded = try? codingValue.decode([PackageAmenities].self, forKey: .amenities) {
                self.amenities = amenitiesDecoded
            }
       }
   }
}

// MARK: - Amenity
struct PackageAmenities: Codable {
    var name: String = ""
    var category: String = ""
    
    init(from decoder: Decoder) throws {
        if let codingValue = try? decoder.container(keyedBy: CodingKeys.self) {
            if let nameDecoded = try? codingValue.decode(String.self, forKey: .name) {
                self.name = nameDecoded
            }
            if let categoryDecoded = try? codingValue.decode(String.self, forKey: .category) {
                self.category = categoryDecoded
            }
        }
    }
}

// MARK: - FeaturedItem
struct PackageFeaturedItem: Codable {
    var featuredItemDescription: String = ""
    var hasInternet: Bool = false
    var hasParking: Bool = false

    enum CodingKeys: String, CodingKey {
        case featuredItemDescription = "description"
        case hasInternet, hasParking
    }
    
    init(from decoder: Decoder) throws {
        if let codingValue = try? decoder.container(keyedBy: CodingKeys.self) {
            if let featuredItemDescriptionDecoded = try? codingValue.decode(String.self, forKey: .featuredItemDescription) {
                self.featuredItemDescription = featuredItemDescriptionDecoded
            }
            if let hasInternetDecoded = try? codingValue.decode(Bool.self, forKey: .hasInternet) {
                self.hasInternet = hasInternetDecoded
            }
            if let hasParkingDecoded = try? codingValue.decode(Bool.self, forKey: .hasParking) {
                self.hasParking = hasParkingDecoded
            }
        }
    }
}

// MARK: - Gallery
struct PackageGallery: Codable {
    var url: String = ""
    
    enum CodingKeys: String, CodingKey {
        case url
    }
    
    init(from decoder: Decoder) throws {
        if let codingValue = try? decoder.container(keyedBy: CodingKeys.self) {
            if let urlDecoded = try? codingValue.decode(String.self, forKey: .url) {
                self.url = urlDecoded
            }
        }
    }
}

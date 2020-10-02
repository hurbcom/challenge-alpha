//
//  HotelsResults.swift
//  Challenge-ios
//
//  Created by Andre Dias on 28/09/20.
//  Copyright © 2020 Andre Dias. All rights reserved.
//

import Foundation

// MARK: - Result
struct HotelsResults: Codable {
    var sku: String = ""
    var isHotel: Bool = true
    var category: Category?
    var smallDescription: String = ""
    var amenities: [ResultAmenity] = [ResultAmenity]()
    var id: String = ""
    var price: ResultPrice?
    var huFreeCancellation: Bool = true
    var image: String = ""
    var name: String = ""
    var url: String = ""
    var resultDescription: String = ""
    var stars: Int = 0
    var gallery: [Gallery] = [Gallery]()
    var address: Address?
    var tags: [String] = [String]()
    var featuredItem: FeaturedItem?

    enum CodingKeys: String, CodingKey {
        case sku, isHotel, category, smallDescription, amenities, id, price
        case huFreeCancellation = "hu_free_cancellation"
        case image, name, url
        case resultDescription = "description"
        case stars, gallery, address, tags, featuredItem
    }
    
    init(from decoder: Decoder) throws {
        if let codingValue = try? decoder.container(keyedBy: CodingKeys.self) {
            if let skuDecoded = try? codingValue.decode(String.self, forKey: .sku) {
                self.sku = skuDecoded
            }
            if let isHotelDecoded = try? codingValue.decode(Bool.self, forKey: .isHotel) {
                self.isHotel = isHotelDecoded
            }
            if let categoryDecoded = try? codingValue.decode(Category.self, forKey: .category) {
                self.category = categoryDecoded
            }
            if let smallDescriptionDecoded = try? codingValue.decode(String.self, forKey: .smallDescription) {
                self.smallDescription = smallDescriptionDecoded
            }
            if let amenitiesDecoded = try? codingValue.decode([ResultAmenity].self, forKey: .amenities) {
                self.amenities = amenitiesDecoded
            }
            if let idDecoded = try? codingValue.decode(String.self, forKey: .id) {
                self.id = idDecoded
            }
            if let priceDecoded = try? codingValue.decode(ResultPrice.self, forKey: .price) {
                self.price = priceDecoded
            }
            if let huFreeCancellationDecoded = try? codingValue.decode(Bool.self, forKey: .huFreeCancellation) {
                self.huFreeCancellation = huFreeCancellationDecoded
            }
            if let imageDecoded = try? codingValue.decode(String.self, forKey: .image) {
                self.image = imageDecoded
            }
            if let urlDecoded = try? codingValue.decode(String.self, forKey: .url) {
                self.url = urlDecoded
            }
            if let resultDescriptionDecoded = try? codingValue.decode(String.self, forKey: .resultDescription) {
                self.resultDescription = resultDescriptionDecoded
            }
            if let galleryDecoded = try? codingValue.decode([Gallery].self, forKey: .gallery) {
                self.gallery = galleryDecoded
            }
            if let nameDecoded = try? codingValue.decode(String.self, forKey: .name) {
                self.name = nameDecoded
            }
            if let starsDecoded = try? codingValue.decode(Int.self, forKey: .stars) {
                self.stars = starsDecoded
            }
            if let addressDecoded = try? codingValue.decode(Address.self, forKey: .address) {
                self.address = addressDecoded
            }
            if let tagsDecoded = try? codingValue.decode([String].self, forKey: .tags) {
                self.tags = tagsDecoded
            }
            if let featuredItemDecoded = try? codingValue.decode(FeaturedItem.self, forKey: .featuredItem) {
                self.featuredItem = featuredItemDecoded
            }
        }
    }
}

// MARK: - ResultAmenity
struct ResultAmenity: Codable {
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

enum Category: String, Codable {
    case hospedagem = "hospedagem"
    case hotel = "hotel"
}

// MARK: - FeaturedItem
struct FeaturedItem: Codable {
    var amenities: [String] = [String]()
    var name: String = ""
    var image: String = ""
    var featuredItemDescription: String = ""

    enum CodingKeys: String, CodingKey {
        case amenities, name, image
        case featuredItemDescription = "description"
    }
    
    init(from decoder: Decoder) throws {
        if let codingValue = try? decoder.container(keyedBy: CodingKeys.self) {
            if let amenitiesDecoded = try? codingValue.decode([String].self, forKey: .amenities) {
                self.amenities = amenitiesDecoded
            }
            if let nameDecoded = try? codingValue.decode(String.self, forKey: .name) {
                self.name = nameDecoded
            }
            if let imageDecoded = try? codingValue.decode(String.self, forKey: .image) {
                self.image = imageDecoded
            }
            if let featuredItemDescriptionDecoded = try? codingValue.decode(String.self, forKey: .featuredItemDescription) {
                self.featuredItemDescription = featuredItemDescriptionDecoded
            }
        }
    }
}

// MARK: - Gallery
struct Gallery: Codable {
    var galleryDescription: String = ""
    var url: String = ""

    enum CodingKeys: String, CodingKey {
        case galleryDescription = "description"
        case url
    }
    
    init(from decoder: Decoder) throws {
        if let codingValue = try? decoder.container(keyedBy: CodingKeys.self) {
            if let galleryDescriptionDecoded = try? codingValue.decode(String.self, forKey: .galleryDescription) {
                self.galleryDescription = galleryDescriptionDecoded
            }
            if let urlDecoded = try? codingValue.decode(String.self, forKey: .url) {
                self.url = urlDecoded
            }
        }
    }
}

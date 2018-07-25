//
//  Results.swift
//  HotelUrbanoChallenge
//
//  Created by maciosdev on 22/07/2018.
//  Copyright © 2018 Ronilson. All rights reserved.
//

import Foundation

struct Results : Codable {
	let sku : String?
	let isHotel : Bool?
	let category : String?
	let smallDescription : String?
	let amenities : [String]?
	let id : String?
	let price : Price?
	let image : String?
	let name : String?
	let url : String?
	let description : String?
	let stars : Int?
	let gallery : [Gallery]?
	let address : Address?
	let tags : [String]?
	let quantityDescriptors : QuantityDescriptors?
	let featuredItem : FeaturedItem?

	enum CodingKeys: String, CodingKey {

		case sku 
		case isHotel 
		case category 
		case smallDescription
		case amenities 
		case id 
		case price 
		case image 
		case name 
		case url 
		case description 
		case stars 
		case gallery 
		case address 
		case tags 
		case quantityDescriptors 
		case featuredItem 
	}

	init(from decoder: Decoder) throws {
		let values = try decoder.container(keyedBy: CodingKeys.self)
		sku = try values.decodeIfPresent(String.self, forKey: .sku)
		isHotel = try values.decodeIfPresent(Bool.self, forKey: .isHotel)
		category = try values.decodeIfPresent(String.self, forKey: .category)
		smallDescription = try values.decodeIfPresent(String.self, forKey: .smallDescription)
		amenities = try values.decodeIfPresent([String].self, forKey: .amenities)
		id = try values.decodeIfPresent(String.self, forKey: .id)
		price = try values.decodeIfPresent(Price.self, forKey: .price)
		image = try values.decodeIfPresent(String.self, forKey: .image)
		name = try values.decodeIfPresent(String.self, forKey: .name)
		url = try values.decodeIfPresent(String.self, forKey: .url)
		description = try values.decodeIfPresent(String.self, forKey: .description)
		stars = try values.decodeIfPresent(Int.self, forKey: .stars)
		gallery = try values.decodeIfPresent([Gallery].self, forKey: .gallery)
		address = try values.decodeIfPresent(Address.self, forKey: .address)
		tags = try values.decodeIfPresent([String].self, forKey: .tags)
		quantityDescriptors = try values.decodeIfPresent(QuantityDescriptors.self, forKey: .quantityDescriptors)
		featuredItem = try values.decodeIfPresent(FeaturedItem.self, forKey: .featuredItem)
	}

}
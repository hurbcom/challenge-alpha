//
//  FeaturedItem.swift
//  HotelUrbanoChallenge
//
//  Created by maciosdev on 22/07/2018.
//  Copyright © 2018 Ronilson. All rights reserved.
//

import Foundation

struct FeaturedItem : Codable {
	let amenities : [String]?
	let name : String?
	let image : String?
	let description : String?

	enum CodingKeys: String, CodingKey {

		case amenities 
		case name 
		case image 
		case description 
	}

	init(from decoder: Decoder) throws {
		let values = try decoder.container(keyedBy: CodingKeys.self)
		amenities = try values.decodeIfPresent([String].self, forKey: .amenities)
		name = try values.decodeIfPresent(String.self, forKey: .name)
		image = try values.decodeIfPresent(String.self, forKey: .image)
		description = try values.decodeIfPresent(String.self, forKey: .description)
	}

}
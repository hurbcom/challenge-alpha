//
//  Gallery.swift
//  HotelUrbanoChallenge
//
//  Created by maciosdev on 22/07/2018.
//  Copyright © 2018 Ronilson. All rights reserved.
//

import Foundation

struct Gallery : Codable {
	let description : String?
	let url : String?

	enum CodingKeys: String, CodingKey {

		case description 
		case url 
	}

	init(from decoder: Decoder) throws {
		let values = try decoder.container(keyedBy: CodingKeys.self)
		description = try values.decodeIfPresent(String.self, forKey: .description)
		url = try values.decodeIfPresent(String.self, forKey: .url)
	}

}
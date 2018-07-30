//
//  PriceInterval.swift
//  HotelUrbanoChallenge
//
//  Created by maciosdev on 22/07/2018.
//  Copyright © 2018 Ronilson. All rights reserved.
//

import Foundation

struct PriceInterval : Codable {
	let min : Int?
	let max : Int?
	let filterPattern : String?

	enum CodingKeys: String, CodingKey {

		case min 
		case max 
		case filterPattern 
	}

	init(from decoder: Decoder) throws {
		let values = try decoder.container(keyedBy: CodingKeys.self)
		min = try values.decodeIfPresent(Int.self, forKey: .min)
		max = try values.decodeIfPresent(Int.self, forKey: .max)
		filterPattern = try values.decodeIfPresent(String.self, forKey: .filterPattern)
	}

}
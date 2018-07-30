//
//  Prices.swift
//  HotelUrbanoChallenge
//
//  Created by maciosdev on 22/07/2018.
//  Copyright © 2018 Ronilson. All rights reserved.
//

import Foundation

struct Prices : Codable {
	let min : Int?
	let maxExclusive : Int?
	let filter : String?
	let count : Int?

	enum CodingKeys: String, CodingKey {

		case min 
		case maxExclusive 
		case filter 
		case count 
	}

	init(from decoder: Decoder) throws {
		let values = try decoder.container(keyedBy: CodingKeys.self)
		min = try values.decodeIfPresent(Int.self, forKey: .min)
		maxExclusive = try values.decodeIfPresent(Int.self, forKey: .maxExclusive)
		filter = try values.decodeIfPresent(String.self, forKey: .filter)
		count = try values.decodeIfPresent(Int.self, forKey: .count)
	}

}